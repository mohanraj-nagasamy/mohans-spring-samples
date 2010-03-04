import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ClientMain {

	@Autowired
	private GreetingService greetingService;

	public void setGreetingService(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	public void testEnglishWelcome() {
		Locale.setDefault(Locale.ENGLISH);
		String name = "Spring Community";
		String greeting = greetingService.greet(name);
		System.out.println("greeting --> " + greeting);
	}

	public void testGermanWelcome() {
		Locale.setDefault(Locale.GERMAN);
		String name = "Spring Community";
		String greeting = greetingService.greet(name);
		System.out.println("greeting --> " + greeting);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ClientMain clientMain = new ClientMain();
		clientMain.createContext();

	}

	private void createContext() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/blog/applicationContext.xml");
		ClientMain clientMain = (ClientMain) applicationContext.getBean("clientMain");
		System.out.println(clientMain);

		clientMain.testEnglishWelcome();
		clientMain.testGermanWelcome();
	}

}
