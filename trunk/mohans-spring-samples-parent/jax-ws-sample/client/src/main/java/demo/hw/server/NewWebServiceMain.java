package demo.hw.server;

import java.net.Authenticator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import my.sample.serve.INewWebService;
import my.sample.serve.NewWebServiceService;

import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;

public class NewWebServiceMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		final INewWebService port = new NewWebServiceService().getNewWebServicePort();

		((BindingProvider) port).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

		// THIS works for basic authentication
		//((BindingProvider) port).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "userfoo");
		//((BindingProvider) port).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "passbar");

		// THIS also works for basic authentication
		Authenticator.setDefault(new MyAuth("userfoo", "passbar"));
		
		String result = port.hello(Thread.currentThread().getName());

		System.out.println("result --> " + result);
		System.out.println("result 2 --> " + port.addNumbers(10, 20));

		WSBindingProvider bp = (WSBindingProvider) port;

		//bp.getInboundHeaders().

		bp.setOutboundHeaders(Headers.create(new QName("mohanHeader1"), "mohanValue1"), Headers.create(new QName(
				"mohanHeader-2"), "mohanValue-2"));

		List<Long> longs = port.calculateTaxForCustCodeWithLongList("arg0", 121f, 2222, 111, "arg4", "arg5", "arg6");

		System.out.println("-------longs--------");

		System.out.println(longs);

		List<String> ss = port.calculateTaxForCustCodeWithStringList("arg0", 121f, 2222, 111, "arg4", "arg5", "arg6");

		System.out.println("-------ss--------");

		System.out.println(ss);
		ss.add("FROM CLIENT");

		ss = port.calculateTaxForCustCodeWithStringListParam(ss);

		System.out.println("-------ss--------");

		System.out.println(ss);

		port.addValueInSession(101);

		System.out.println(port.getValueFromSession());

	}

}
