package com.devcamp.thongnh.realestate.Controllers.Web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.devcamp.thongnh.realestate.Model.CBlog;
import com.devcamp.thongnh.realestate.Model.CProvince;
import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Service.Impl.BlogServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.ProvinceServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.RealEstateServiceImpl;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;
import com.devcamp.thongnh.realestate.security.JwtUtil;
import com.devcamp.thongnh.realestate.security.UserPrincipal;

@RestController
public class myProfileController {
    @Autowired
    private RealEstateServiceImpl realEstateServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomersServiceImpl customersServiceImpl;

    @Autowired
    private ProvinceServiceImpl provinceServiceImpl;
    @Autowired
    private BlogServiceImpl blogServiceImpl;

    @RequestMapping("/my-profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView property(Model model,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("myProfile");
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
            List<CRealEstate> properties = realEstateServiceImpl.getRealEstateByCustomerId(user.getUserId());
            List<CBlog> blogList=blogServiceImpl.findByCustomersId(user.getUserId());
            int propertiesCount = properties.size();
            List<CProvince> cProvinces = provinceServiceImpl.getAll();
            // Đưa dữ liệu vào model để hiển thị trên trang JSP
            model.addAttribute("profile", profile);// dữ liệu khách hàng
            model.addAttribute("properties", properties);// dữ liệu property đã đăng
            model.addAttribute("propertiesCount", propertiesCount);// dữ liệu số lượng property
            model.addAttribute("provinces", cProvinces);// dữ liệu số lượng Provinces
            model.addAttribute("blogList", blogList);// dữ liệu số lượng blogList

        }
        return modelAndView;
    }

}
