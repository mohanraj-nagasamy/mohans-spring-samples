/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.sample.serve;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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

}
