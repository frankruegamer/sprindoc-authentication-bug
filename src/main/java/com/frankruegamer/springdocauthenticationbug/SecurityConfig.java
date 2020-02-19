package com.frankruegamer.springdocauthenticationbug;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
		return http.csrf().disable()
		           .authorizeExchange()
		           .pathMatchers("/v3/api-docs*")
		           .permitAll()
		           .anyExchange().authenticated()
		           .and()
		           .httpBasic()
		           .and().build();
	}

	@Bean
	public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user =
				User.withUsername("user")
				    .password(passwordEncoder.encode("user"))
				    .authorities("USER")
				    .build();
		UserDetails admin =
				User.withUsername("admin")
				    .password(passwordEncoder.encode("admin"))
				    .authorities("USER", "ADMIN")
				    .build();

		return new MapReactiveUserDetailsService(user, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
