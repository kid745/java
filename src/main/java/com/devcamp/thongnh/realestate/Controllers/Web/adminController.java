package com.devcamp.thongnh.realestate.Controllers.Web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;
import com.devcamp.thongnh.realestate.security.JwtUtil;
import com.devcamp.thongnh.realestate.security.UserPrincipal;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
public class adminController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomersServiceImpl customersServiceImpl;

    @RequestMapping("/project")
    public ModelAndView projectAdmin(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("projectAdmin");
        String tokenValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        if (tokenValue != null) {
            UserPrincipal user = jwtUtil.getUserFromToken(tokenValue);
            CCustomers profile = customersServiceImpl.getById(user.getUserId()).get();
            model.addAttribute("profile", profile);// dữ liệu tài khoản

        }
        return modelAndView;
    }

    @RequestMapping("/account")
    public ModelAndView accountAdmin(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("accountAdmin");
        String tokenValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        if (tokenValue != null) {
            UserPrincipal user = jwtUtil.getUserFromToken(tokenValue);
            CCustomers profile = customersServiceImpl.getById(user.getUserId()).get();
            model.addAttribute("profile", profile);// dữ liệu tài khoản
        }
        return modelAndView;
    }
    @RequestMapping("/comment")
    public ModelAndView commentAdmin(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("commentAdmin");
        String tokenValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        if (tokenValue != null) {
            UserPrincipal user = jwtUtil.getUserFromToken(tokenValue);
            CCustomers profile = customersServiceImpl.getById(user.getUserId()).get();
            model.addAttribute("profile", profile);// dữ liệu tài khoản
        }
        return modelAndView;
    }
    @RequestMapping("/blog")
    public ModelAndView blogAdmin(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("blogAdmin");
        String tokenValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        if (tokenValue != null) {
            UserPrincipal user = jwtUtil.getUserFromToken(tokenValue);
            CCustomers profile = customersServiceImpl.getById(user.getUserId()).get();
            model.addAttribute("profile", profile);// dữ liệu tài khoản
        }
        return modelAndView;
    }
    @RequestMapping("/email")
    public ModelAndView emailAdmin(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("emailAdmin");
        String tokenValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        if (tokenValue != null) {
            UserPrincipal user = jwtUtil.getUserFromToken(tokenValue);
            CCustomers profile = customersServiceImpl.getById(user.getUserId()).get();
            model.addAttribute("profile", profile);// dữ liệu tài khoản
        }
        return modelAndView;
    }
}
