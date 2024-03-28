package com.devcamp.thongnh.realestate.security;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CookieFilter cookieFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(cookieFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .logout()
                .logoutUrl("/logout") // Đường dẫn để đăng xuất
                .logoutSuccessHandler((request, response, authentication) -> {
                    Cookie cookie = new Cookie("token", null);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    SecurityContextHolder.clearContext();
                }).invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/user/error")
                .and()
                .formLogin()
                    .loginPage("/login");
    }
}
