package com.spark.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Retrieve the JWT from the header
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt != null) {
            try {
                // Extract email from the JWT
                String email = JwtProvider.getEmailFromJwtToken(jwt);

                // Prepare authorities (empty if roles aren't included in the JWT)
                List<GrantedAuthority> authorities = new ArrayList<>();

                // Create an authentication object
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // Handle invalid tokens
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                response.getWriter().flush();
                return; // Stop further processing for invalid tokens
            }
        }
        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}
