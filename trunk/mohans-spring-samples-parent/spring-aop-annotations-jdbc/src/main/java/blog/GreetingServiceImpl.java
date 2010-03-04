import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingServiceImpl implements GreetingService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public String greet(String name) {
		Locale locale = Locale.getDefault();
		String message = messageRepository.getMessage(locale.getDisplayLanguage());
		return message + " " + name;
	}
}
