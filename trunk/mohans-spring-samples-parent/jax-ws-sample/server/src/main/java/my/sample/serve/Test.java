package my.sample.serve;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
/*@XmlRootElement(name = "test_AddNumbers", namespace = "http://server.fromjava/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addNumbers", namespace = "http://server.fromjava/", propOrder = { "testArg0", "testArg1" })
*/public class Test {

	//@XmlElement(name = "testArg0", namespace = "")
	private int testArg0;
	//@XmlElement(name = "testArg1", namespace = "")
	private int testArg1;

	@XmlElement(nillable = true)
	private List<String> testArgString;

	public void setTestArgString(List<String> testArgString) {
		this.testArgString = testArgString;
	}


	public int getTestArg0() {
		return testArg0;
	}

	public void setTestArg0(int testArg0) {
		this.testArg0 = testArg0;
	}

	public int getTestArg1() {
		return testArg1;
	}

	public void setTestArg1(int testArg1) {
		this.testArg1 = testArg1;
	}

}
