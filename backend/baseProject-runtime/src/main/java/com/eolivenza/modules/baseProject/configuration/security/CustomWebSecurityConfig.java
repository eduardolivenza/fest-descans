package com.eolivenza.modules.baseProject.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * The type Custom web security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private CSRFConfiguration csrfConfiguration;
    //private TokenService tokenService;
    //private TokenValidationService tokenValidationService;

    @Autowired
    public CustomWebSecurityConfig(CSRFConfiguration csrfConfiguration){
                                   //TokenService tokenService, TokenValidationService tokenValidationService) {
        this.csrfConfiguration = csrfConfiguration;
        //this.tokenService = tokenService;
        //this.tokenValidationService = tokenValidationService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/products/**",
                        "/categories",
                        "/image/**",
                        "/suppliers/**",
                        "/users/**",
                        "/actuator/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers("/**")
                .and()
                .formLogin().disable()
                .logout().disable();
        if (csrfConfiguration.getActivateCsrf()) {
            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        }
        http.addFilterAfter(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/h2-console/**");
        web.debug(false);
    }



}
