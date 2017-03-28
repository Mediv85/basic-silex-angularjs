package it.mediv.controller.rest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.mediv.controller.adapter.AuthReq;
import it.mediv.controller.adapter.AuthResp;
import it.mediv.controller.adapter.MessageResp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControlTest {

	@Autowired
	private TestRestTemplate restTemplate;

	

	@Test
	public void authYo() {
	
		AuthReq ar= new AuthReq("admin","admin");
		ResponseEntity<AuthResp> responseEntity
			=restTemplate.postForEntity(
					"/auth/authenticate",
					ar,
					AuthResp.class);
		
		AuthResp client = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("admin", client.getUsername());
		assertEquals(true, client.getSuccess());		
	}
}
