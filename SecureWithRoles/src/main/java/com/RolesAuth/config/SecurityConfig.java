package com.RolesAuth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.RolesAuth.service.CustomUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserService service;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(encode());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	   http.csrf().disable()
	       .authorizeRequests()
	       .antMatchers("/registers").permitAll()
	       .antMatchers("/profile","/edit").authenticated().anyRequest().hasAnyRole("ADMIN","USER")
	       .and()
	       .httpBasic();
	}
	
	@Bean
	public PasswordEncoder encode() {
		return NoOpPasswordEncoder.getInstance();
	}
}
