package com.eolivenza.modules.baseProject.configuration.security;

import com.eolivenza.modules.baseProject.application.security.BaseProjectGrantPermission;
import com.eolivenza.modules.baseProject.configuration.WebRestControllerAdvice;
import com.eolivenza.modules.baseProject.domain.model.user.Session;
import com.eolivenza.modules.baseProject.domain.model.user.UserRights;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
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

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            SecurityContextHolder.clearContext();
            String header = req.getHeader(HEADER_STRING);
            String email = null;
            UserRights rights = null;
            String authToken = null;
            logger.info("Received header: " + header);
            if (header != null && header.startsWith(TOKEN_PREFIX)) {
                authToken = header.replace(TOKEN_PREFIX,"");
                try {
                    email = Session.getUsernameFromToken(authToken);
                    rights = UserRights.valueOf(Session.getUserRightsFromToken(authToken));
                } catch (IllegalArgumentException e) {
                    logger.error("an error occured during getting username from token", e);
                } catch (ExpiredJwtException e) {
                    logger.warn("the token is expired and not valid anymore", e);
                } catch(SignatureException e){
                    logger.error("Authentication Failed. Username or Password not valid.");
                }
            } else {
                logger.warn("couldn't find bearer string, will ignore the header");
            }
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = getAuthentication(email, rights);

                // 6. Authenticate the user
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(req, res);
        }
        catch (ExpiredJwtException var12) {
            Logger.error("JWT no authorised", var12);
            res.setStatus(401);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String userId, UserRights rights) {
            if (userId != null) {
                List<GrantedAuthority> authorities = new ArrayList();
                if (rights.equals(UserRights.administrator)) {
                    authorities.add(new SimpleGrantedAuthority(BaseProjectGrantPermission.ADMIN));
                }
                if (rights.equals(UserRights.user)) {
                    authorities.add(new SimpleGrantedAuthority(BaseProjectGrantPermission.USER));
                }
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, "", authorities);
                return auth;
            }
            return null;
    }

}
