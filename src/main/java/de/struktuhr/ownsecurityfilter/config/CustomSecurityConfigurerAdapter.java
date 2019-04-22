package de.struktuhr.ownsecurityfilter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import de.struktuhr.ownsecurityfilter.security.CustomUserHeaderSecurityFilter;
import de.struktuhr.ownsecurityfilter.security.UserAuthenticationReader;

/**
 * CustomSecurityConfig
 */

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class CustomSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(customUserHeaderSecurityFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private UserAuthenticationReader userAuthenticationReader() {
        return new UserAuthenticationReader();
    }

    private CustomUserHeaderSecurityFilter customUserHeaderSecurityFilter() {
        return new CustomUserHeaderSecurityFilter(userAuthenticationReader());
    }
}