package com.cagnosolutions.cei.houseontherock.fantheflamedates.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Environment env;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser(env.getProperty("admin.username"))
				.password(env.getProperty("admin.password")).roles("ADMIN");
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passEncoder())
		.usersByUsernameQuery(
				"SELECT username, password, active FROM user WHERE username=?")
				.authoritiesByUsernameQuery(
						"SELECT username, role FROM user WHERE username=?");
	}	

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasAnyRole("ADMIN")
				.antMatchers("/user/**").hasAnyRole("ADMIN","USER")
			.and()
				.formLogin().loginPage("/login").failureUrl("/login?error=true")
			.and()
				.logout().logoutSuccessUrl("/home")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.and();
	}
	
	@Bean
	PasswordEncoder passEncoder() {
	    return new BCryptPasswordEncoder();
	}
}