package com.devcamp.thongnh.realestate.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.devcamp.thongnh.realestate.Model.User.Token;
import com.devcamp.thongnh.realestate.Service.User.Impl.TokenServiceImpl;

@Component
public class CookieFilter extends OncePerRequestFilter  {
     @Autowired
    private JwtUtil jwtUtil;
     @Autowired
    private TokenServiceImpl verificationTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Lấy token từ cookie
        String token = getCookieValue(request, "token");
        UserPrincipal user = null;
        Token checkToken = verificationTokenService.findByToken(token);
        
        if (StringUtils.hasText(token) && null != token) {
            // Thêm token vào header
            response.addHeader("Authorization", "Token " + token);
            user= jwtUtil.getUserFromToken(token);
        }
        if(null !=user && checkToken.getTokenExpDate().after(new Date())){
            Set<GrantedAuthority> authorities = new HashSet<>();
            user.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority((String) p)));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    // Phương thức để lấy giá trị của cookie dựa vào tên
    public String getCookieValue(HttpServletRequest request, String name) {
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (javax.servlet.http.Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
