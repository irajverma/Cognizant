package com.cognizant.springlearn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * Configure in-memory authentication with two users:
     * - admin/pwd with role ADMIN
     * - user/pwd with role USER
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("START - Configuring authentication");
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("pwd")).roles("USER");
        LOGGER.info("END");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        LOGGER.info("START");
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure HTTP security:
     * - Disable CSRF for REST API
     * - Enable HTTP Basic authentication
     * - /authenticate accessible by both USER and ADMIN roles
     * - All other requests require authentication
     * - Add JWT authorization filter
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        LOGGER.info("START - Configuring HTTP security");
        httpSecurity.csrf().disable().httpBasic().and()
                .authorizeRequests()
                //.antMatchers("/countries").hasRole("USER")
                .antMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager()));
        LOGGER.info("END");
    }
}
