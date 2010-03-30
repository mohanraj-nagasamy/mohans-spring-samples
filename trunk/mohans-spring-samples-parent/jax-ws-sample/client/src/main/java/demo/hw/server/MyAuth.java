package demo.hw.server;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class MyAuth extends Authenticator {
	String rctUserName;
	String rctPassword;

	public MyAuth(String username, String password) {
		super();
		rctUserName = username;
		rctPassword = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		char[] pwdChar = rctPassword.toCharArray();
		return new PasswordAuthentication(rctUserName, pwdChar);
	}
}