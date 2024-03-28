package com.devcamp.thongnh.realestate.Controllers.Web;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.devcamp.thongnh.realestate.Model.CCart;
import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Service.Impl.CartServiceImpl;
import com.devcamp.thongnh.realestate.security.JwtUtil;
import com.devcamp.thongnh.realestate.security.UserPrincipal;

@RestController
public class myCartController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CartServiceImpl cartServiceImpl;
    
    @RequestMapping("/my-cart")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView mycart(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("myCart");
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
            List<CCart> carts = cartServiceImpl.getAllRealEstateByCustomerId(user.getUserId());
            int cartCount = carts.size();
            Long totalPrice = cartServiceImpl.totalPriceAllRealEstateByCustomerId(user.getUserId());
            List<CCustomers> customers = cartServiceImpl.getAllCustomerByAllRealEstate(carts);
            List<CCustomers> uniqueCustomers = customers.stream()
                    .collect(Collectors.toSet()) // Chuyển danh sách thành một Set (loại bỏ các giá trị trùng lặp)
                    .stream() // Chuyển lại từ Set thành Stream
                    .collect(Collectors.toList());
            int customerCount = uniqueCustomers.size();
            model.addAttribute("carts", carts);
            model.addAttribute("cartCount", cartCount);
            model.addAttribute("uniqueCustomers", uniqueCustomers);
            model.addAttribute("customerCount", customerCount);
            model.addAttribute("totalPrice", totalPrice);
        }
        
        return modelAndView;
    }
}
