package my.sample.serve;

import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@WebService
public interface INewWebService {
	String hello(String string);

	int addNumbers(int i, int j);

	TestListClassMohan findTestList();

	Test findTestListWithArrays();

	/*	@XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
		Map<Integer, User> getUsers();
	*/
	//@XmlJavaTypeAdapter(value = MyHashMapAdapter.class, type = Map.class)
	//Map getUsers123();

}
