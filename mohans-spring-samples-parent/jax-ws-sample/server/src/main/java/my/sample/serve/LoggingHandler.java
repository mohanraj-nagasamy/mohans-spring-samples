/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://jwsdp.dev.java.net/CDDLv1.0.html
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * https://jwsdp.dev.java.net/CDDLv1.0.html  If applicable,
 * add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your
 * own identifying information: Portions Copyright [yyyy]
 * [name of copyright owner]
 */
package my.sample.serve;

import java.io.PrintStream;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.transport.Headers;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String HANDLER_NAME = "LoggingHandler";
	// change this to redirect output if desired

	public Set<QName> getHeaders() {
		return null;
	}

	public boolean handleMessage(SOAPMessageContext smc) {
		logToSystemOut(smc);
		return true;
	}

	public boolean handleFault(SOAPMessageContext smc) {
		logToSystemOut(smc);
		return true;
	}

	// nothing to clean up
	public void close(MessageContext messageContext) {
	}

	/*
	 * Check the MESSAGE_OUTBOUND_PROPERTY in the context
	 * to see if this is an outgoing or incoming message.
	 * Write a brief message to the print stream and
	 * output the message. The writeTo() method can throw
	 * SOAPException or IOException
	 */
	private void logToSystemOut(SOAPMessageContext smc) {
		System.out.println("LoggingHandler.logToSystemOut()");
		System.out.println("smc.values() --> " + smc.values());

		Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		System.out.println("smc.getRoles() --> " + smc.getRoles());
		String USERID = (String) smc.get(BindingProvider.USERNAME_PROPERTY);
		String PASSWORD = (String) smc.get(BindingProvider.PASSWORD_PROPERTY);

		System.out.println("MessageContext.HTTP_REQUEST_HEADERS -> " + smc.get(MessageContext.HTTP_REQUEST_HEADERS));
		System.out.println("MessageContext.HTTP_RESPONSE_HEADERS -> " + smc.get(MessageContext.HTTP_RESPONSE_HEADERS));

		System.out.println("USERID --> " + USERID);
		System.out.println("PASSWORD --> " + PASSWORD);

		try {
			System.out.println("smc.getMessage().getSOAPHeader() --> " + smc.getMessage().getSOAPPart().getEnvelope());
		} catch (SOAPException e1) {

			e1.printStackTrace();
		}

		System.out.println();

		if (outboundProperty.booleanValue()) {
			System.out.println("-------------------------");
			System.out.println("Outbound message:");

			SOAPMessage message = smc.getMessage();
			try {
				message.writeTo(System.out);
				System.out.println(""); // just to add a newline
			} catch (Exception e) {
				System.out.println("Exception in handler: " + e);
			}

			System.out.println("-------------------------");

		} else {
			System.out.println("**********************************");
			System.out.println("Inbound message:");
			SOAPMessage message = smc.getMessage();
			try {
				message.writeTo(System.out);
				System.out.println(""); // just to add a newline
			} catch (Exception e) {
				System.out.println("Exception in handler: " + e);
			}

			try {
				System.out.println("message.getSOAPBody().getElementName() --> " + message.getSOAPBody().getElementName());
				System.out.println("message.getSOAPBody().getLocalName() --> " + message.getSOAPBody().getLocalName());
				System.out.println("message.getSOAPBody().getValue() --> " + message.getSOAPBody().getValue());
				
			} catch (SOAPException e) {
				
				e.printStackTrace();
			}

			printWebServiceContext(smc);
			System.out.println("**********************************");
		}

	}

	private void printWebServiceContext(SOAPMessageContext messageContext) {
		System.out.println("LoggingHandler.printWebServiceContext() :: Enter");

		for (String key : messageContext.keySet()) {
			System.out.println("key --> " + key);
			Object obj = messageContext.get(key);

			if (obj != null) {
				System.out.println("val object type --> " + obj.getClass());
			} else {
				System.out.println("obj is null");
			}
		}

		com.sun.xml.ws.api.message.HeaderList headerList = (HeaderList) messageContext
				.get("com.sun.xml.ws.api.message.HeaderList");

		for (Header header : headerList) {
			System.out.println(header.getLocalPart() + " <--LOG-LOG--> " + header.getStringContent());
		}

		com.sun.xml.ws.transport.Headers headers = (Headers) messageContext.get("javax.xml.ws.http.request.headers");

		System.out.println("--------------- REQUEST headers ---------------");

		for (String key : headers.keySet()) {
			System.out.println("key --> " + key);
			Object obj = messageContext.get(key);

			if (obj != null) {
				System.out.println("val object type --> " + obj.getClass());
				System.out.println("val object value--> " + obj);
			} else {
				System.out.println("obj is null");
			}
		}

/*		com.sun.xml.ws.transport.Headers inboundHeaders = (Headers) messageContext
				.get("com.sun.xml.ws.api.message.packet.inbound.transport.headers");
*/
		
		// THIS IS NOT WORKING 
		boolean exit = false;
		try {
			// get SOAP envelope from SOAP message
			SOAPEnvelope se = messageContext.getMessage().getSOAPPart().getEnvelope();

			// get the headers from envelope
			SOAPHeader sh = se.getHeader();
			if (sh == null) {
				System.out.println("--- No headers found in the input SOAP request");
				exit = false;
			} else {
				// call method to process header
				exit = processSOAPHeader(sh);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			exit = false;
		}

		System.out.println("exit --> " + exit);

		System.out.println("LoggingHandler.printWebServiceContext() :: Exit");
	}

	private boolean processSOAPHeader(SOAPHeader sh) {
		System.out.println("LoggingHandler.processSOAPHeader()");

		boolean authenticated = false;

		//java.util.Iterator childElems = sh.getChildElements(new QName("mohanHeader1", "mohanValue1"));
		java.util.Iterator childElems = sh.getChildElements();

		SOAPElement child;
		// iterate through child elements
		while (childElems.hasNext()) {

			child = (SOAPElement) childElems.next();
			authenticated = processSOAPHeaderInfo(child);
		}

		System.out.println("authenticated --> " + authenticated);

		return authenticated;
	}

	private boolean processSOAPHeaderInfo(SOAPElement e) {
		System.out.println("LoggingHandler.processSOAPHeaderInfo()");

		SOAPElement child;
		QName name;
		String _id = "";
		String _password = "";

		// get an iterator on child elements of SOAP element
		java.util.Iterator childElems = e.getChildElements();

		// loop through child elements
		while (childElems.hasNext()) {
			// get next child element
			Object elem = childElems.next();
			if (elem instanceof SOAPElement) {

				child = (SOAPElement) elem;
				name = (QName) child.getElementQName();

				System.out.println("name.getLocalPart() --> " + name.getLocalPart());

				// get the value of id element
				if (name.getLocalPart().equals("mohanHeader1")) {
					System.out.println("\t\tid =" + child.getValue());
					_id = child.getValue();
				}

				// get the value of password element
				if (name.getLocalPart().equals("mohanHeader-2")) {
					System.out.println("\t\tpassword =" + child.getValue());
					_password = child.getValue();
				}
			}
		}

		return authenticate(_id, _password);
	}

	/**
	 * In reality, we may login to a credential store.
	 */
	private boolean authenticate(String _id, String _password) {

		System.out.println("LoggingHandler.authenticate() --> _id -> " + _id + " _password -> " + _password);
		
		// client authentication information
		final String USERID = "yyang";
		final String PASSWORD = "mypassword";

		if (_id.equals(USERID) && _password.equals(PASSWORD)) {
			System.out.println("Authenticated by " + HANDLER_NAME + "!");
			return true;
		} else {
			System.out.println("Could not authenticate ,WRONG id or password");
		}
		return false;
	}
}
