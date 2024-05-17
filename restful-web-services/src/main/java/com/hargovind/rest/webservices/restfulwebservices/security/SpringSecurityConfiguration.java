package com.hargovind.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//We are overriding the default security behaviour
        //Default Steps
        //Step01: all requests will be authenticated.
        //Step02: If not authenticated, A login UI will be generated.
        //Step03: CSRF is implemented.
 
        //We need to override this behaviour
        //Whenever w need to change the security filter, entire implementation will be removed.
        //So need to implement everything from start.
 
        //To-DO [ Done ]
 
        //Step01: Check if all incoming requests are authentictaed.
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
 
        //Step02: If not, instead of showing a UI page, show a prompt to login
        http.httpBasic(Customizer.withDefaults());
 
        //disable CSRF
        http.csrf(csrf->csrf.disable());
 
        return http.build();
	}
}
