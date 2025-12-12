package com.yuva.cryptopulse.filter;


import com.yuva.cryptopulse.constants.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtHeader = request.getHeader(ApplicationConstants.JWT_HEADER);
        if (null != jwtHeader) {
            try {
                // Extract the JWT token
                String jwt = jwtHeader.substring(7); // Remove 'Bearer ' prefix
                Environment env = getEnvironment();
                if (null != env) {
                    String secret = env.getProperty(ApplicationConstants.SECRET_KEY);

                    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                    if (null != secretKey) {
                        Claims claims = Jwts.parser().verifyWith(secretKey)
                                .build().parseSignedClaims(jwt).getPayload();
                        String email = String.valueOf(claims.get("email"));
                        String roles = String.valueOf(claims.get("roles"));
                        Authentication authentication = new UsernamePasswordAuthenticationToken(email,
                                null, AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                    }
                }

            } catch (Exception exception) {
                throw new BadCredentialsException("Invalid Token received!");
            }
        }
        filterChain.doFilter(request, response);

    }
}





