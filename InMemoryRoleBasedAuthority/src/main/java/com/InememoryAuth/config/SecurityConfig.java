package com.InememoryAuth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



	protected void configure1(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		    .withUser("maya").password("1234").roles("Admin")
		    .and()
		    .withUser("raghu").password("3333").roles("User")
		    .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
		
		
	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		InMemoryUserDetailsManager userDetailService=new InMemoryUserDetailsManager();
//		UserDetails user1=User.withUsername("Raghu").password("1234").authorities("admin").build();
//		UserDetails user2=User.withUsername("Bala").password("3333").authorities("User").build();
//		userDetailService.createUser(user1);
//		userDetailService.createUser(user2);
//		auth.userDetailsService(userDetailService).passwordEncoder(encoder());
//		
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		    .antMatchers("/register")
		    .permitAll().anyRequest()
		    .authenticated()
		    .and()
		    .httpBasic();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
