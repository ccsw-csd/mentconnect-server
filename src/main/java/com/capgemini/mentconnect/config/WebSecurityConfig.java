package com.capgemini.mentconnect.config;

import com.capgemini.mentconnect.config.security.JsonWebTokenAuthenticationFilter;
import com.capgemini.mentconnect.config.security.JsonWebTokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This type serves as a base class for extensions of the {@code WebSecurityConfigurerAdapter} and provides a default
 * configuration. <br/>
 * Security configuration is based on {@link WebSecurityConfigurerAdapter}. This configuration is by purpose designed
 * most simple for two channels of authentication: simple login form and rest-url.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JsonWebTokenAuthenticationProvider jwtAuthProvider;

  @Autowired
  private JsonWebTokenAuthenticationFilter jwtAuthFilter;

  /**
   * {@inheritDoc}
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {

    // This method is here with the @Bean annotation so that Spring can autowire it
    return super.authenticationManagerBean();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.authenticationProvider(this.jwtAuthProvider);
  }

  /**
   * Configure spring security.
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {

    http
        // Disable unnecessary default configurations
        .csrf().disable().cors().disable().httpBasic().disable().formLogin().disable()

        // No session will be created by Spring Security
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Return 403 when not authenticated
    // .and().exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint(""));

    setupAuthorization(http);

    http.addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
  }

  /**
   * Forces child classes to set up the authorization paths
   *
   * @param http an {@link HttpSecurity}
   * @throws Exception an {@link Exception}
   */
  protected void setupAuthorization(HttpSecurity http) throws Exception {

    String[] unsecuredResources = new String[] { "/", "/security/login", "/public/**" };

    http.authorizeRequests()
        // Allow Options request
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        // Allow unsecured resources
        .antMatchers(unsecuredResources).permitAll()
        // Authenticate all other requests
        .anyRequest().authenticated();

    // TODO Disable security
    //.anyRequest().permitAll();
  }

}
