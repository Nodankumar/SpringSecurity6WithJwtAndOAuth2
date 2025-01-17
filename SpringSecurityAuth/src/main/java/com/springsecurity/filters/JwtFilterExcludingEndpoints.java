
package com.springsecurity.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilterExcludingEndpoints extends OncePerRequestFilter {

    private final JWTFilter jwtFilter;
    private final List<String> excludedEndpoints;

    public JwtFilterExcludingEndpoints(JWTFilter jwtFilter, String... excludedEndpoints) {
        this.jwtFilter = jwtFilter;
        this.excludedEndpoints = Arrays.asList(excludedEndpoints);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
       
        if (excludedEndpoints.stream().anyMatch(path::startsWith)) {
        	 System.out.println("path: "+path);
            filterChain.doFilter(request, response);
        } else {
            jwtFilter.doFilter(request, response, filterChain);
        }
    }
}
