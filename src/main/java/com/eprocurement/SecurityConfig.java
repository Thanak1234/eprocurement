package com.eprocurement;

import com.eprocurement.user.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AdminProperties adminProperties;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
				.antMatchers("/js/**", "/bootstrap/**", "/css/**", "/jquery-3.2.1.min.js").permitAll()
				.antMatchers("/", "/#", "/item/new", "/item/all", "/api/items", "/item/save", "/pr/new",
						"/pr/{pr}/details", "/pr/save", "/pr/all", "/api/pr/{department}/all", "/api/pritems/**",
						"/api/pr/{pr}","/login")
				.hasAnyRole("ADMIN", "USER").anyRequest().hasRole("ADMIN").and().exceptionHandling()
				.accessDeniedPage("/403");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(adminProperties.getUsername()).password(passwordEncoder().encode(adminProperties.getPassword()))
				.roles("ADMIN").disabled(adminProperties.isDisabled()).and().and().userDetailsService(userDetailsService);
	}
}
