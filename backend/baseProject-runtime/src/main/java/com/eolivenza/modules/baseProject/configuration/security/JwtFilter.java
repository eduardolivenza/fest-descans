package com.eolivenza.modules.baseProject.configuration.security;

import com.eolivenza.modules.baseProject.application.security.BaseProjectGrantPermission;
import com.eolivenza.modules.baseProject.configuration.WebRestControllerAdvice;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtFilter extends OncePerRequestFilter {

    private final org.slf4j.Logger Logger = LoggerFactory.getLogger(WebRestControllerAdvice.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try
        {



            // 5. Create auth object
            // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
            // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
            UsernamePasswordAuthenticationToken auth = getAuthentication();

            // 6. Authenticate the user
            // Now, user is authenticated
            SecurityContextHolder.getContext().setAuthentication(auth);

            chain.doFilter(req, res);
        } catch (ExpiredJwtException var12) {
            Logger.error("JWT no authorised", var12);
            res.setStatus(401);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication() {
            String user = "edu";
            if (user != null) {
                List<GrantedAuthority> authorities = new ArrayList();
                authorities.add(new SimpleGrantedAuthority(BaseProjectGrantPermission.USER));
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, "", authorities);
                return auth;
            }
            return null;
    }


}
