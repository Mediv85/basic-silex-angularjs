package it.mediv.controller.rest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import it.mediv.controller.adapter.MessageResp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnonymousControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void createClient() {
		ResponseEntity<MessageResp> responseEntity = restTemplate.getForEntity("/anonymous/greetings",
				MessageResp.class);
		MessageResp client = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Hey!!! How is going???", client.getResponce());
		
	}
}
