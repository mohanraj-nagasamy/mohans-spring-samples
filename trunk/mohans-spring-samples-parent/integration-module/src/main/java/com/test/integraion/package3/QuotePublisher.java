package com.test.integraion.package3;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestContext;

@MessageEndpoint(value = "quotes")
@Component
public class QuotePublisher {

	//TestContext
	
	public BigDecimal getQuote() {
		BigDecimal price = new BigDecimal(new Random().nextDouble() * 100);

		return price;
	}

}