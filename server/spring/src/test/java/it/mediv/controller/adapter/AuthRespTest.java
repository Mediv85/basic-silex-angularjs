package it.mediv.controller.adapter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;



@TestComponent
public class AuthRespTest {
	
	private final boolean SUCCESS = true;
	private final String TOKEN = "token";
	private final String USERNAME = "admin";

	

	@Test
	public void constructorWithoutParametersCreatesExpectedObject() {
		AuthResp authenticationResponce = new AuthResp();

		assertNull(authenticationResponce.getSuccess());
		assertNull(authenticationResponce.getToken());
		assertNull(authenticationResponce.getUsername());
	}

	@Test
	public void constructorWithParametersCreatesExpectedObject() {
		AuthResp authenticationResponce = new AuthResp(SUCCESS,TOKEN, USERNAME);

		assertThat(authenticationResponce.getSuccess(), is(SUCCESS));
		assertThat(authenticationResponce.getToken(), is(TOKEN));
		assertThat(authenticationResponce.getUsername(), is(USERNAME));
	}

	@Test
	public void gettersAndSettersReturnsExpectedObjects() {
		AuthResp authenticationResponce = new AuthResp();

		authenticationResponce.setSuccess(SUCCESS);
		authenticationResponce.setToken(TOKEN);
		authenticationResponce.setUsername(USERNAME);

		assertThat(authenticationResponce.getSuccess(), is(SUCCESS));
		assertThat(authenticationResponce.getToken(), is(TOKEN));
		assertThat(authenticationResponce.getUsername(), is(USERNAME));

	}

}
