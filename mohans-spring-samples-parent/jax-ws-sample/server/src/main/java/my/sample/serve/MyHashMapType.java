package my.sample.serve;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class MyHashMapType {
	@XmlAttribute
	public Integer key;

	@XmlValue
	public String value;

}
