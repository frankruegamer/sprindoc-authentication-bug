package com.frankruegamer.springdocauthenticationbug;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@PostMapping
	public void Login(Authentication authentication, ServerHttpResponse response) {
	}

}
