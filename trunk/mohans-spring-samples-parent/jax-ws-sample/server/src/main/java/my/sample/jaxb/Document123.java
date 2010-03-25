package my.sample.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "doc")
public class Document123 {
	@XmlElement
	protected Foo foo;

	public Document123() {

	}

	public Document123(Foo foo) {
		super();
		this.foo = foo;
	}

}
