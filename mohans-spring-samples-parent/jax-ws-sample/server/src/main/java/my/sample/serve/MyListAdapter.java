package my.sample.serve;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MyListAdapter extends XmlAdapter<List<Test>, List<Test>> {

	@Override
	public List<Test> marshal(List<Test> v) throws Exception {

		return null;
	}

	@Override
	public List<Test> unmarshal(List<Test> v) throws Exception {

		return null;
	}

}
