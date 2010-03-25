package my.sample.serve;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class TestListClassMohan {

	@XmlElement(nillable = true)
	private List<Test> testArgList;

	public List<Test> findTestList() {

		return testArgList;
	}

}
