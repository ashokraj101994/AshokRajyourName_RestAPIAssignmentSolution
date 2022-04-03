package com.greatlearning.employee.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new com.greatlearning.employee.service.UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasAnyAuthority("ADMIN,GUEST")
				.antMatchers(HttpMethod.GET, "/Employee/list", "/Employee/find", "/Employee/search", "/Employee/sort")
				.hasAnyAuthority("ADMIN", "GUEST").antMatchers(HttpMethod.POST, "/Employee/addEmployee")
				.hasAuthority("ADMIN").antMatchers(HttpMethod.DELETE, "/Employee/delete").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/Employee/update").hasAuthority("ADMIN")
				.antMatchers("/Employee/addUser", "/Employee/addRole").permitAll().anyRequest().authenticated().and()
				.httpBasic().and().cors().and().csrf().disable();
	}

}
