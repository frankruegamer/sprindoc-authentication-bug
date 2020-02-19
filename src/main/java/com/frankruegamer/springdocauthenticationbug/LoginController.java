package com.frankruegamer.springdocauthenticationbug;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static java.util.stream.Collectors.joining;

@RestController
public class LoginController {

	@PostMapping
	public void Login(Authentication authentication, ServerHttpResponse response) {
		ResponseCookie userCookie = ResponseCookie.from("account", authentication.getName())
		                                      .domain("localhost")
		                                      .maxAge(Duration.ofDays(30))
		                                      .build();
		String roles = authentication.getAuthorities().stream().map(Object::toString).collect(joining("|"));
		ResponseCookie rolesCookie = ResponseCookie.from("roles", roles)
		                                           .domain("localhost")
		                                           .maxAge(Duration.ofDays(30))
		                                           .build();
		response.addCookie(userCookie);
		response.addCookie(rolesCookie);
	}

}
