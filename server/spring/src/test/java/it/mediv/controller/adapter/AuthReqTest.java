package it.mediv.controller.adapter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

import it.mediv.controller.adapter.AuthReq;

@TestComponent
public class AuthReqTest {
	private final String USERNAME = "username";
	private final String PASSWORD = "password";

	@Test
	public void constructorWithoutParametersCreatesExpectedObject() {
		AuthReq authenticationRequest = new AuthReq();

		assertNull(authenticationRequest.getUsername());
		assertNull(authenticationRequest.getPassword());
	}

	@Test
	public void constructorWithParametersCreatesExpectedObject() {
		AuthReq authenticationRequest = new AuthReq(USERNAME, PASSWORD);

		assertThat(authenticationRequest.getUsername(), is(USERNAME));
		assertThat(authenticationRequest.getPassword(), is(PASSWORD));
	}

	@Test
	public void gettersAndSettersReturnsExpectedObjects() {
		AuthReq authenticationRequest = new AuthReq();

		authenticationRequest.setUsername(USERNAME);
		authenticationRequest.setPassword(PASSWORD);

		assertThat(authenticationRequest.getUsername(), is(USERNAME));
		assertThat(authenticationRequest.getPassword(), is(PASSWORD));
	}

}
