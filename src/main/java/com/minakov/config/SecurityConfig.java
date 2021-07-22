package com.minakov.config;

import com.minakov.filters.AccessFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * This class configures security of the application and
 * adds filters.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .addFilterAfter(new HiddenHttpMethodFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AccessFilter(), HiddenHttpMethodFilter.class);
    }
}