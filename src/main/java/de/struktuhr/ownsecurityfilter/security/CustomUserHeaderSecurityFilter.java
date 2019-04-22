package de.struktuhr.ownsecurityfilter.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


public class CustomUserHeaderSecurityFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(CustomUserHeaderSecurityFilter.class);

    private final UserAuthenticationReader userAuthenticationReader;

    public CustomUserHeaderSecurityFilter(UserAuthenticationReader userAuthenticationReader) {
        this.userAuthenticationReader = userAuthenticationReader;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        final String userId = req.getHeader("x-user");
        
        
        log.info("Request {} {} : User {}", req.getMethod(), req.getRequestURI(), userId);
        
        // Build Authentication Context
        final Authentication authentication = userAuthenticationReader.getAuthentication(userId);
        log.info("User {} : Authentication {}", userId, authentication);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        chain.doFilter(req, res);

        log.info("Response : {}", res.getContentType());
    }
    
};