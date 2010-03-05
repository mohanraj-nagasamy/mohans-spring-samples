package com.test.integraion.package1.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.PollableChannel;
import org.springframework.integration.core.Message;
import org.springframework.integration.core.MessageChannel;
import org.springframework.integration.message.StringMessage;

import com.test.integraion.package1.MyGateway;

public class PollableChannelClientMain {

	private ApplicationContext context;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PollableChannelClientMain pollableChannelClientMain = new PollableChannelClientMain();
		pollableChannelClientMain.loadApplicationContext();

		pollableChannelClientMain.pollableChannelTest();
		pollableChannelClientMain.testServiceActivator();
		pollableChannelClientMain.testGateway();

		/*
		 MessageChannel inputMessageChannel = (MessageChannel) context.getBean("input");
		inputMessageChannel.send(new StringMessage("Spring Integration rocks asdf"));
		*/

		/*		PollableChannel output = (PollableChannel) context.getBean("output");

				Message<?> reply = output.receive();

				System.out.println("received: " + reply);
				System.out.println("reply.getPayload: " + reply.getPayload());
		*/

		/*		MyGateway myGateway = (MyGateway) context.getBean("gateway");
				myGateway.send("Spring Integration rocks 1");
				myGateway.send1("Spring Integration rocks 2");
				myGateway.send2("Spring Integration rocks 3");
		*/
	}

	private void testGateway() {
		System.out.println("PollableChannelClientMain.testGateway() :: Enter");

		MyGateway myGateway = (MyGateway) context.getBean("myGateway");
		//myGateway.send("Spring Integration rocks 1");
		myGateway.send1("Spring Integration rocks 2");
		myGateway.send2("Spring Integration rocks 3");

		System.out.println("serviceActivatorOutput CLASS --> " + context.getBean("serviceActivatorOutput").getClass());
		PollableChannel pollableChannel = (PollableChannel) context.getBean("myGatewayOutput");

		messageReceiver(pollableChannel);

		System.out.println("PollableChannelClientMain.testGateway() :: Exit");
	}

	private void testServiceActivator() {
		System.out.println("PollableChannelClientMain.testServiceActivator() :: Enter");
		System.out.println("serviceActivatorInput CLASS --> " + context.getBean("serviceActivatorInput").getClass());

		MessageChannel directChannel = (MessageChannel) context.getBean("serviceActivatorInput");

		System.out.println("serviceActivatorOutput CLASS --> " + context.getBean("serviceActivatorOutput").getClass());
		PollableChannel pollableChannel = (PollableChannel) context.getBean("serviceActivatorOutput");

		messageSender(directChannel, "Spring rocks!");
		messageSender(directChannel, "Spring rocks!!!!");
		messageReceiver(pollableChannel);

		System.out.println("PollableChannelClientMain.testServiceActivator() :: Exit");
	}

	private void messageSender(MessageChannel messageChannel, String message) {
		messageChannel.send(new StringMessage(message));
	}

	private void loadApplicationContext() {
		context = new ClassPathXmlApplicationContext("package1/config.xml");

	}

	private void pollableChannelTest() {
		System.out.println("PollableChannelClientMain.pollableChannelTest() :: Enter");

		PollableChannel pollableChannel = (PollableChannel) context.getBean("pollableChannelInput");
		messageSender(pollableChannel);

		messageReceiver(pollableChannel);
		messageSender(pollableChannel);
		messageReceiver(pollableChannel);

		System.out.println("PollableChannelClientMain.pollableChannelTest() :: Exit");
	}

	private void messageSender(PollableChannel pollableChannel) {
		System.out.println("PollableChannelClientMain.messageSender()");
		pollableChannel.send(new StringMessage("Spring Integration rocks : " + System.currentTimeMillis()));
	}

	private void messageReceiver(PollableChannel pollableChannel) {
		Message<?> reply = pollableChannel.receive();
		System.out.println("Received:" + reply);
	}

}
