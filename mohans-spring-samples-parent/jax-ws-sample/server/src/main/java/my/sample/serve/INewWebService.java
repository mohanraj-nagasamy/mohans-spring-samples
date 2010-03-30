package my.sample.serve;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService
@HandlerChain(file="handlers.xml")
public interface INewWebService {
	String hello(String string);

	int addNumbers(int i, int j);

	TestListClassMohan findTestList();

	Test findTestListWithArrays();

	public List<Long> calculateTaxForCustCodeWithLongList(String custCode, float charge, int taxType, int serviceType,
			String businessUnit, String source, String user);

	public List<String> calculateTaxForCustCodeWithStringList(String custCode, float charge, int taxType,
			int serviceType, String businessUnit, String source, String user);

	public List<String> calculateTaxForCustCodeWithStringListParam(List<String>  strings);

	public void addValueInSession(Integer integer);
	
	public Integer getValueFromSession();
}
