package my.sample.serve;

import java.io.PrintStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

public class JAXBPayloadLoggingLogicalHandler extends
		BaseHandler<LogicalMessageContext> implements
		LogicalHandler<LogicalMessageContext> {
	private static PrintStream out = System.out;

	private static final String HANDLER_NAME = "JAXBPayloadLoggingLogicalHandler";

	public JAXBPayloadLoggingLogicalHandler() {
		super();
		super.setHandlerName(HANDLER_NAME);
	}

	public boolean handleMessage(LogicalMessageContext smc) {
		out.println("------------------------------------");
		out.println("In LogicalHandler " + HandlerName + ":handleMessage()");

		logToSystemOut(smc);

		out.println("Exiting LogicalHandler " + HandlerName
				+ ":handleMessage()");
		out.println("------------------------------------");

		return true;
	}

	private void logToSystemOut(LogicalMessageContext smc) {
		Boolean outboundProperty = (Boolean) smc
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		LogicalMessage message = smc.getMessage();

		JAXBContext jc = null;
		Object obj = null;
		try {
			jc = JAXBContext
					.newInstance("com.company.jaxws.handler.cardservice.creditcard:com.company.jaxws.handler.cardservice.exception");
			obj = message.getPayload(jc);
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("obj ---> " + obj);
		System.out.println("obj ---> " + obj.getClass());

		if (outboundProperty.booleanValue()) {
			out.println("\ndirection = outbound ");
		} else {
			out.println("\ndirection = inbound ");
		}

		/*if (obj instanceof AuthorizationStatus) {
			out.println("***Authrization Status***");
			out.println("Authoorization Token: "
					+ ((AuthorizationStatus) obj).getAuthorizationToken());
			out.println("Error Code: "
					+ ((AuthorizationStatus) obj).getErrorCode());
			out.println("Is Authorized: "
					+ ((AuthorizationStatus) obj).isAuthorized());
		} else if (obj instanceof AuthorizationRequest) {
			out.println("***Authrization Request***");
			out.println("Card Number: "
					+ ((AuthorizationRequest) obj).getCreditCard()
							.getCardNumber());
			out.println("User Name: "
					+ ((AuthorizationRequest) obj).getCardUser().getFirstName()
					+ " "
					+ ((AuthorizationRequest) obj).getCardUser().getLastName());
		} else {
			out.println("***Authrization Failed***");
			out.println("The reason is: "
							+ ((FaultInfo) obj).getFaultMessage());
		}*/
	}
}