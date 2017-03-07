package it.mediv.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.mediv.controller.adapter.MessageResp;

@RestController
@RequestMapping("/anonymous")
public class AnonymousController {

	@RequestMapping(value = "/greetings", method = RequestMethod.GET)
	public MessageResp greetings() {

		return new MessageResp("Hey!!! How is going???");
	}

}
