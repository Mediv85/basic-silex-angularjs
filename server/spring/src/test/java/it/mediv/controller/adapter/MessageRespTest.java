package it.mediv.controller.adapter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class MessageRespTest {

	private final String RESPONCE = "token";


	@Test
	public void constructorWithoutParametersCreatesExpectedObject() {
		MessageResp messageResponce = new MessageResp();

		assertNull(messageResponce.getResponce());
	}

	@Test
	public void constructorWithParametersCreatesExpectedObject() {
		MessageResp messageResponce = new MessageResp(RESPONCE);

		assertThat(messageResponce.getResponce(), is(RESPONCE));
	}

	@Test
	public void gettersAndSettersReturnsExpectedObjects() {
		MessageResp messageResponce = new MessageResp();

		messageResponce.setResponce(RESPONCE);

		assertThat(messageResponce.getResponce(), is(RESPONCE));
	}

}
