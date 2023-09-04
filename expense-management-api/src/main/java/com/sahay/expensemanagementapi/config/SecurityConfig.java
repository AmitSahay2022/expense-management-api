package com.sahay.expensemanagementapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
	
	
	//--------------injecting CustomUserDetailsService in configuration class---------------
	private UserDetailsService userDetailsService;
	
	
	
	//----------PasswordEncoder is used by many UserDetailsService providers--------------------
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	//---AuthenticationManager is must----------
	//---------------It automatically takes CustomUserDetailsService and PasswordEncoder --------
	//-------And provides DB Authentication
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	

	//------First of all, all the requests comes to SecurityFilterChain --------
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeHttpRequests((auth)->{
			auth.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
			auth.requestMatchers(HttpMethod.POST, "/api/users").permitAll();
			auth.requestMatchers(HttpMethod.POST, "/api/users/**").permitAll();
			auth.anyRequest().authenticated();
		});
		httpSecurity.csrf(csrf->csrf.disable());
		httpSecurity.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	
	

	//-----------IT IS FOR IN MEMORY AUTHENTICATION--------------
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		UserDetails user1 = User.builder().username("amit2022").password(passwordEncoder().encode("test1234"))
//				.authorities("ADMIN").build();
//		UserDetails user2 = User.builder().username("sumit2012").password(passwordEncoder().encode("test1234"))
//				.authorities("USER").build();
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
	
}
