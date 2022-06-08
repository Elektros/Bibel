package de.bibel.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final Logger LOGGER = LogManager.getLogger(WebSecurityConfiguration.class);
  @Override
  public void configure(HttpSecurity http) {
    try {
      http.csrf().disable().authorizeRequests()
              .antMatchers("/**").permitAll()
              .antMatchers(HttpMethod.DELETE,"/**").permitAll()
              .antMatchers(HttpMethod.POST, "/**").permitAll()
              .antMatchers(HttpMethod.GET,"/**").permitAll()
              .anyRequest().authenticated();
    } catch (Exception exception) {
      LOGGER.error(exception.getMessage());
    }
  }
}