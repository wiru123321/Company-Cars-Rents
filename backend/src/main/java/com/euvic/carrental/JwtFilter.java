package com.euvic.carrental;

import com.euvic.carrental.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final String HEADER_STRING = "Authorization";
    public final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JwtUtil jwtUtil;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith((TOKEN_PREFIX))) {
            authToken = header.replace(TOKEN_PREFIX, "");
            try {
                username = jwtUtil.getUsernameFromToken(authToken);
            } catch (final IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (final ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch (final SignatureException e) {
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(authToken, userDetails)) {
                final UsernamePasswordAuthenticationToken authentication = jwtUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

}
