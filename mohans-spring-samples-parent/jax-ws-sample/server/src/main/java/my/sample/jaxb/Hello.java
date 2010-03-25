package my.sample.jaxb;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;

import my.sample.serve.User;

import org.w3c.dom.Document;

public class Hello {

	public Hello() {
	}

	public static void main(String... arg) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Document123.class);
		Marshaller m = jc.createMarshaller();

		Foo foo = new Foo();
		foo.setId(11);
		foo.setName("asdfdsaf ");

		Map<String, User> map = new HashMap<String, User>();
		map.put("111111", new User("111val"));
		map.put("222222", new User("222val"));

		foo.setMap(map);

		Document123 document = new Document123(foo);

		m.marshal(document, System.out);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc = dbf.newDocumentBuilder().newDocument();

		m.marshal(document, doc);
		//System.out.println(doc);

	}

	public void marshal() {

	}

}
