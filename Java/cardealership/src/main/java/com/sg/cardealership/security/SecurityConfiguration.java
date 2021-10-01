/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author kavin
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService databaseUserDetailsService;

    public SecurityConfiguration(UserDetailsService databaseUserDetailsService) {
        this.databaseUserDetailsService = databaseUserDetailsService;
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(this.databaseUserDetailsService);
        return provider;
    }

    // Using a simple SHA-256 for hashing for demonstration purposes.
    // Should use different hashing algorithms.

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("SHA-256");
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new LoginAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf()
            .disable()
            .authorizeRequests()

            // Set access authorization.

            .antMatchers("/", "/home/**", "/inventory/**", "/img/**", "/js/**", "/api/**").permitAll() // Permit anyone viewing these pages
            .antMatchers("/admin/**").access("hasAuthority('Admin')") // Only admin can view these pages
            .antMatchers("/sales/**").access("hasAuthority('Sales')") // Only sales can view these pages
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
			.loginPage("/account/login") // Allow anyone to login. The login in link will not be accessible through UI on main pages.
            .successHandler(customAuthenticationSuccessHandler()) // Allow for different redirects for different authorization.
			.permitAll()
            .and()
            .logout() // Logout. Default page is /logout
            .logoutSuccessUrl("/home");
    }
}