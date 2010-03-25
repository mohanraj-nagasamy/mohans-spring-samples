package my.sample.jaxb;

import java.util.Map;

import my.sample.serve.User;

public class Foo {

	private Integer id;
	private String name;
	private Map<String, User> map;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, User> getMap() {
		return map;
	}

	public void setMap(Map<String, User> map) {
		this.map = map;
	}
}
