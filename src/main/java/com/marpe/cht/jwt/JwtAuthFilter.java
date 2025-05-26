package com.marpe.cht.jwt;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.marpe.cht.entities.enums.Role;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            try {
				filterChain.doFilter(request, response);
			} catch (java.io.IOException | ServletException e) {
				e.printStackTrace();
			}
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtService.isTokenValid(token)) {
            try {
				filterChain.doFilter(request, response);
			} catch (java.io.IOException | ServletException e) {
				e.printStackTrace();
			}
            return;
        }

        String username = jwtService.extractUsername(token);
        Role role = jwtService.extractRole(token);

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));

        var authToken = new UsernamePasswordAuthenticationToken(
                username, null, authorities
        );
        
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Autenticação no contexto: " + (auth != null ? auth.getName() : "nulo"));
        
        try {
			filterChain.doFilter(request, response);
		} catch (java.io.IOException | ServletException e) {
			e.printStackTrace();
		}

    }
}
