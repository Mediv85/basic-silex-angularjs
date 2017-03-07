package it.mediv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.mediv.controller.adapter.AuthReq;
import it.mediv.controller.adapter.AuthResp;
import it.mediv.persistence.Users;
import it.mediv.repository.UsersRepository;
import it.mediv.security.TokenUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UsersRepository ur;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public AuthResp authenticate(@RequestBody AuthReq ar) throws AuthenticationException {

		// Perform the authentication
		Authentication authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(ar.getUsername(), ar.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-authentication so we can generate token
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(ar.getUsername());
		String token = this.tokenUtils.generateToken(userDetails);

		// Return the token
		return new AuthResp(true, token, ar.getUsername());
	}


}
