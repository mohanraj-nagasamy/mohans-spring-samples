package my.sample.serve;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "User")
public class User {
	String name;

	public User() {
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}