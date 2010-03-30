/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.sample.serve;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;

/**
 *
 * @author MNagasamy
 */
@WebService(endpointInterface = "my.sample.serve.INewWebService")
@SOAPBinding()
public class NewWebService extends SpringBeanAutowiringSupport implements INewWebService {

	@Autowired
	private ApplicationContext applicationContext;

	@Resource
	WebServiceContext webServiceContext;

	@WebMethod()
	public synchronized String hello(@WebParam(name = "name123") String s) {
		System.out.println("NewWebService.hello() WITH synchronized");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("webServiceContext --> " + webServiceContext);

		System.out.println("Service received: " + s);

		System.out.println("NewWebService.hello() ---> " + applicationContext);

		System.out.println("testSpring --> " + applicationContext.getBean("testSpring"));

		WebApplicationContext cc = ContextLoader.getCurrentWebApplicationContext();

		System.out.println("WebApplicationContext --> " + cc);
		System.out.println(cc.hashCode());

		System.out.println("cccc testccSpring --> " + cc.getBean("testSpring"));
		System.out.println("cccc testccSpring --> " + (cc.getBean("testSpring")).hashCode());

		System.out.println("test test --> " + (cc.getBean("test")));

		MessageContext mc = webServiceContext.getMessageContext();

		Object hl = webServiceContext.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);

		com.sun.xml.ws.api.message.HeaderList headerList = (HeaderList) hl;

		System.out.println("headerList -> " + headerList);
		System.out.println(headerList.size());

		HttpSession session = ((javax.servlet.http.HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST))
				.getSession();
		// Get a session property "counter" from context
		if (session == null)
			throw new WebServiceException("No session in WebServiceContext");
		Integer counter = (Integer) session.getAttribute("counter");
		if (counter == null) {
			counter = new Integer(0);
			System.out.println("Starting the Session");
		}
		counter = new Integer(counter.intValue() + 1);
		session.setAttribute("counter", counter);

		System.out.println("counter -> " + counter);

		return s;
	}

	@WebMethod
	public int addNumbers(int a, int b) {
		if (a < 0 || b < 0) {
			throw new RuntimeException("Negative number cant be added! Numbers: " + a + ", " + b);
		}
		return a + b;
	}

	@WebMethod
	public TestListClassMohan findTestList() {

		return null;
	}

	@WebMethod
	public Test findTestListWithArrays() {

		return null;
	}

	/*public Map<Integer, User> getUsers() {
		return null;
	}*/

	public Map getUsers123() {
		return null;
	}

	public List<Long> calculateTaxForCustCodeWithLongList(String custCode, float charge, int taxType, int serviceType,
			String businessUnit, String source, String user) {

		System.out.println("NewWebService.calculateTaxForCustCodeWithLongList()");
		System.out.println("calculateTaxForCustCodeWithLongList()");

		List<Long> longs = new ArrayList<Long>();
		longs.add(111l);
		longs.add(222l);
		longs.add(333l);

		return longs;

	}

	public List<String> calculateTaxForCustCodeWithStringList(String custCode, float charge, int taxType,
			int serviceType, String businessUnit, String source, String user) {

		System.out.println("NewWebService.calculateTaxForCustCodeWithStringList()");
		System.out.println("calculateTaxForCustCodeWithStringList()");

		List<String> longs = new ArrayList<String>();
		longs.add("111l");
		longs.add("222l");
		longs.add("333l");

		return longs;

	}

	public List<String> calculateTaxForCustCodeWithStringListParam(List<String> strings) {

		System.out.println("NewWebService.calculateTaxForCustCodeWithStringListParam()");

		List<String> longs = new ArrayList<String>();
		longs.add("111l");
		longs.add("222l");
		longs.add("333l");

		longs.addAll(strings);

		return longs;

	}

	public void addValueInSession(Integer integer) {
		System.out.println("NewWebService.addValueInSession()");

		MessageContext mc = webServiceContext.getMessageContext();

		HttpSession session = ((javax.servlet.http.HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST))
				.getSession();

		if (session == null)
			throw new WebServiceException("No session in WebServiceContext");

		Integer counter = (Integer) session.getAttribute("counter");

		if (counter == null) {
			counter = new Integer(0);

			System.out.println("Starting the Session");
		}

		counter = new Integer(counter.intValue() + integer);
		session.setAttribute("counter", counter);

		System.out.println("counter -> " + counter);

	}

	public Integer getValueFromSession() {
		System.out.println("NewWebService.getValueFromSession()");

		MessageContext mc = webServiceContext.getMessageContext();

		HttpSession session = ((javax.servlet.http.HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST))
				.getSession();

		if (session == null)
			throw new WebServiceException("No session in WebServiceContext");

		Integer counter = (Integer) session.getAttribute("counter");

		System.out.println("counter -> " + counter);

		printWebServiceContext();
		return counter;

	}

	private void printWebServiceContext() {
		System.out.println("NewWebService.printWebServiceContext() :: Enter");

		for (String key : webServiceContext.getMessageContext().keySet()) {
			System.out.println("key --> " + key);
			Object obj = webServiceContext.getMessageContext().get(key);

			if (obj != null) {
				System.out.println("val object type --> " + obj.getClass());
			} else {
				System.out.println("obj is null");
			}
		}

		com.sun.xml.ws.api.message.HeaderList headerList = (HeaderList) webServiceContext.getMessageContext().get(
				"com.sun.xml.ws.api.message.HeaderList");

		for (Header header : headerList) {
			System.out.println(header.getNamespaceURI() + " <----> " + header.getStringContent());
		}

		System.out.println("soapMessageContext.getUserPrincipal() --> " + webServiceContext.getUserPrincipal());
		System.out.println("soapMessageContext.getUserPrincipal().getName() --> "
				+ webServiceContext.getUserPrincipal().getName());
		System.out.println("isSecure() -> "
				+ ((javax.servlet.http.HttpServletRequest) webServiceContext.getMessageContext().get(
						MessageContext.SERVLET_REQUEST)).isSecure());

		System.out.println("isUserInRole('CalculatorUser')() -> "
				+ ((javax.servlet.http.HttpServletRequest) webServiceContext.getMessageContext().get(
						MessageContext.SERVLET_REQUEST)).isUserInRole("CalculatorUser"));

		System.out.println("isUserInRole('!CalculatorUser')() -> "
				+ ((javax.servlet.http.HttpServletRequest) webServiceContext.getMessageContext().get(
						MessageContext.SERVLET_REQUEST)).isUserInRole("!CalculatorUser"));

		System.out.println("NewWebService.printWebServiceContext() :: Exit");
	}

}
