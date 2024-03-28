package com.devcamp.thongnh.realestate.Controllers.Api.User;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Model.User.Token;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;
import com.devcamp.thongnh.realestate.Service.User.Impl.RoleServiceImpl;
import com.devcamp.thongnh.realestate.Service.User.Impl.TokenServiceImpl;
import com.devcamp.thongnh.realestate.security.JwtUtil;
import com.devcamp.thongnh.realestate.security.UserPrincipal;

@RestController
public class AuthController {

    @Autowired
     CustomersServiceImpl userService;

    @Autowired
     TokenServiceImpl tokenService;

    @Autowired
    RoleServiceImpl roleServiceImpl;
    
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/userbytoken")
    public ResponseEntity<Object> getUserByToken(@RequestParam("data") String token)
    {
        try {
         UserPrincipal user =   jwtUtil.getUserFromToken(token);
         return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("False", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody CCustomers user ) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(roleServiceImpl.getById(2L));
        user.setCreatedAt(new Date());
        userService.saveCustomers(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<Object> login(@RequestBody CCustomers user,HttpServletResponse response) {
        UserPrincipal userPrincipal = userService.getByUsername(user.getUsername());
        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
        Token token = new Token();
        token.setToken(jwtUtil.genCerateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.saveToken(token);

        Cookie cookie = new Cookie("token",token.getToken());
        cookie.setPath("/");
        cookie.setMaxAge(864000);
        response.addCookie(cookie);
        return ResponseEntity.ok(token.getToken());
    }
   
    @RequestMapping("/user/error")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }
}
