package com.devcamp.thongnh.realestate.Controllers.Api.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Model.User.Role;
import com.devcamp.thongnh.realestate.Service.SendMail.SendMailService;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;
import com.devcamp.thongnh.realestate.security.JwtUtil;
import com.devcamp.thongnh.realestate.security.UserPrincipal;

@RestController
public class WithoutAuthorizeController {
    @Autowired
    private CustomersServiceImpl customersServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    SendMailService sendMailService;

    /**
     * Test trường hợp khôngcheck quyền Authorize lấy là danh sách user
     * 
     * @return
     */
    @GetMapping("/usersdetail")
    public ResponseEntity<Object> getUsers(HttpServletRequest request) {
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

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Test trường hợp khôngcheck quyền Authorize
     * Tạo mới user
     * 
     * @param user
     * @return
     */
    @PostMapping("/users/createAdmin")
    public ResponseEntity<Object> create(@RequestBody CCustomers user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        customersServiceImpl.saveCustomers(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Test có kiểm tra quyền.
     * 
     * @param user
     * @return
     */
    @PostMapping("/users/create")
    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    public ResponseEntity<Object> register(@RequestBody CCustomers user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        customersServiceImpl.saveCustomers(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/accout/lock/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<Object> approveUser(@PathVariable("id") long pId, HttpServletRequest request) {
        CCustomers customers = customersServiceImpl.getById(pId).get();
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
            if (hasRoleForCustomer(customers, "ROLE_ADMIN")) {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
            if (hasRoleForCustomer(customers, "ROLE_MANAGER") && !hasRoleForUser(user, "ROLE_ADMIN")) {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
            customers.setDeleted(true);
            customers.setUpdatedAt(new Date());
            customers.setUpdatedBy(user.getUserId());
            customersServiceImpl.saveCustomers(customers);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/accout/unlock/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<Object> unapproveUser(@PathVariable("id") long pId, HttpServletRequest request,
            @RequestParam("reason") String reason) {
        CCustomers customers = customersServiceImpl.getById(pId).get();
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
            if (hasRoleForCustomer(customers, "ROLE_ADMIN")) {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
            if (hasRoleForCustomer(customers, "ROLE_MANAGER") && !hasRoleForUser(user, "ROLE_ADMIN")) {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
            customers.setDeleted(false);
            customers.setUpdatedAt(new Date());
            customers.setUpdatedBy(user.getUserId());
            customersServiceImpl.saveCustomers(customers);
            sendMailService.sendMail(reason);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public boolean hasRoleForUser(UserPrincipal user, String roleKey) {
        CCustomers profile = customersServiceImpl.getById(user.getUserId()).get();
        Set<Role> roles = profile.getRoles();
        for (Role role : roles) {
            if (role.getRoleKey().equals(roleKey)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRoleForCustomer(CCustomers customer, String roleKey) {
        Set<Role> roles = customer.getRoles();
        for (Role role : roles) {
            if (role.getRoleKey().equals(roleKey)) {
                return true;
            }
        }
        return false;
    }
}