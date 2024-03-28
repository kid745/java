package com.devcamp.thongnh.realestate.Controllers.Api;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.thongnh.realestate.Model.CCart;
import com.devcamp.thongnh.realestate.Service.Impl.CartServiceImpl;
import com.devcamp.thongnh.realestate.security.JwtUtil;
import com.devcamp.thongnh.realestate.security.UserPrincipal;

@RequestMapping("/")
@RestController
@CrossOrigin
public class CartController {
    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/cart")
    public ResponseEntity<List<CCart>> getCartByCustomerId(HttpServletRequest request) {
        try {
            String tokenValue = getTokenFromCookies(request);
            if (tokenValue == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            UserPrincipal user = jwtUtil.getUserFromToken(tokenValue);
            return new ResponseEntity<>(cartServiceImpl.getAllRealEstateByCustomerId(user.getUserId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Object> deleteCart(@PathVariable("id") long pId, HttpServletRequest request) {
        try {
            String tokenValue = getTokenFromCookies(request);
            if (tokenValue == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            UserPrincipal user = jwtUtil.getUserFromToken(tokenValue);
            List<CCart> carts = cartServiceImpl.findByCustomersIdAndRealestateId(user.getUserId(), pId);

            if (carts != null && !carts.isEmpty()) {
                for (CCart cart : carts) {
                    cartServiceImpl.deleteById(cart.getId());
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Add cart by id project
    @GetMapping("/cart/add/{id}")
    public ResponseEntity<Object> addCartByIdProject(@PathVariable("id") long pId,HttpServletRequest request){
        try {
            cartServiceImpl.addCartByIdProject(pId, request);
            return new ResponseEntity<>(HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private String getTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
