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
package annotations.client.test;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import my.sample.serve.INewWebService;
import my.sample.serve.NewWebServiceService;

public class AddNumbersClientAppTest extends TestCase {

	public AddNumbersClientAppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new JUnit4TestAdapter(AddNumbersClientAppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	public void testWS() {
		System.out.println("AddNumbersClientAppTest.testWS()");

		final INewWebService port = new NewWebServiceService().getNewWebServicePort();

		String result = port.hello(Thread.currentThread().getName());
		System.out.println("result --> " + result);
		System.out.println("result 2 --> " + port.addNumbers(10, 20));

		assertEquals(30, port.addNumbers(10, 20));
	}

	public void testNumberWithNegative() {
		System.out.println("AddNumbersClientAppTest.addNumberWithNegative()");

		final INewWebService port = new NewWebServiceService().getNewWebServicePort();

		try {
			port.addNumbers(10, -20);
		} catch (Exception e) {

		}
	}
}
