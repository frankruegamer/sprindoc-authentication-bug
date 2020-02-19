package com.frankruegamer.springdocauthenticationbug;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
		return http.authorizeExchange()
		           .pathMatchers("/v3/api-docs*")
		           .permitAll()
		           .and().build();
	}

}
