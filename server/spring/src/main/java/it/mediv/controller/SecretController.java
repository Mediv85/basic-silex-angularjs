package it.mediv.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.mediv.controller.adapter.MessageResp;

@RestController
@RequestMapping("/secret")
public class SecretController {

	@RequestMapping(value = "/tellmeasecret", method = RequestMethod.GET)
	public MessageResp tellMeASecret() {

		return new MessageResp("I am your father!!!");
	}
}
