package it.mediv.controller.rest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import it.mediv.controller.adapter.AuthResp;
import it.mediv.controller.adapter.MessageResp;
import it.mediv.security.TokenUtils;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecretControlTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Test
	public void unauthorizedResponce() {
		ResponseEntity<MessageResp> responseEntity = restTemplate.getForEntity("/secret/tellmeasecret",
				MessageResp.class);
		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());	
	}
	
	@Test
	public void authenticatedResponce() {

		TokenUtils tu= new TokenUtils();
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername("admin");
		String token = tu.generateToken(userDetails);

		assertEquals("admin", userDetails.getUsername());

		//create headers for json param and authentication token
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("x-auth-token", token);
		requestHeaders.add("Content-Type", "application/json"); 
		
		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);

		
		ResponseEntity<MessageResp> responseEntity = restTemplate.exchange("/secret/tellmeasecret", HttpMethod.GET, httpEntity, MessageResp.class);
		MessageResp client = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("I am your father!!!", client.getResponce());

		
	}

}

