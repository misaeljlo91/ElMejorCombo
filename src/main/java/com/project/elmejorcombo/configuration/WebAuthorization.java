package com.project.elmejorcombo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //ALL
                .antMatchers(HttpMethod.POST,"/api/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/clients").permitAll()
                .antMatchers(HttpMethod.POST,"/api/forgot-data").permitAll()
                .antMatchers(HttpMethod.PATCH,"/api/clients/forgot-username").permitAll()
                .antMatchers(HttpMethod.PATCH,"/api/clients/forgot-password").permitAll()

                //ADMIN
                .antMatchers(HttpMethod.GET,"/api/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/admin/**").hasAuthority("ADMIN")
                //ADMIN, USER
                .antMatchers(HttpMethod.GET,"/api/clients/current/**").hasAnyAuthority("ADMIN, USER")
                .antMatchers(HttpMethod.POST,"/api/clients/current/**").hasAnyAuthority("ADMIN, USER")
                .antMatchers(HttpMethod.PUT,"/api/clients/current/**").hasAnyAuthority("ADMIN, USER")
                .antMatchers(HttpMethod.DELETE,"/api/clients/current/**").hasAnyAuthority("ADMIN, USER");


        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout");

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendRedirect("/web/index.html"));
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
