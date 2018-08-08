package com.iheart.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.iheart.challenge.auth.AuthFilter;
import com.iheart.challenge.auth.AuthService;

@SpringBootApplication
@EnableAutoConfiguration
public class ChallengeApplication {
	
	@Bean
	public FilterRegistrationBean<AuthFilter> authFilter(){
	    FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
	         
	    registrationBean.setFilter(new AuthFilter());
	    registrationBean.addUrlPatterns("/advertisers/*");
	         
	    return registrationBean;    
	}
	
	@Bean
	public AuthService authService() {
		return new AuthService();
	}

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}
}
