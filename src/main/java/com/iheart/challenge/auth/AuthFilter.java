package com.iheart.challenge.auth;

import static com.iheart.challenge.logging.LoggingSteps.AUTH;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iheart.challenge.logging.StepLogger;

@Component
public class AuthFilter implements Filter, StepLogger {

  public static final String X_CLACKS_OVERHEAD = "X-Clacks-Overhead";
  
  private static Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
  
  @Autowired
  private AuthService authService;

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	  logStep(AUTH);
	  HttpServletRequest request = (HttpServletRequest)req;
	  final String token = request.getHeader("Authorization");
	  if(authService==null){
          ServletContext servletContext = request.getServletContext();
          WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
          authService = webApplicationContext.getBean(AuthService.class);
      }
	  if(!authService.authorize(token)) {
		  info("Authorization failed.");
		  HttpServletResponse response = (HttpServletResponse)res;
		  response.sendError(HttpServletResponse.SC_NOT_FOUND);//return 404 instead of 401. 401 lets the attacker know that there exists resource.
	  }
	  logStep("Done: "+AUTH);
	  chain.doFilter(req, res);
  }

  @Override
  public void destroy() {}

  @Override
  public void init(FilterConfig arg0) throws ServletException {}
  
  public Logger getLogger() {
	  return LOGGER;
  }

}
