package com.devcamp.thongnh.realestate.Controllers.Web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.devcamp.thongnh.realestate.Model.CBlog;
import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Service.Impl.BlogServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.RealEstateServiceImpl;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;

@RestController
public class IndexController {
    @Autowired
    RealEstateServiceImpl realEstateServiceImpl;
    @Autowired
    CustomersServiceImpl customersServiceImpl;
    @Autowired
    BlogServiceImpl blogServiceImpl;

    @RequestMapping(value = { "/", "/index" })
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView("index");
        List<CRealEstate> top10ViewRealestateList = realEstateServiceImpl.findTop10ByOrderByViewDesc();
        List<CRealEstate> new6RealestateList = realEstateServiceImpl.findTop9ByOrderByDateCreateDesc();
        List<CRealEstate> featuredRealestateList = realEstateServiceImpl.getRealEstateByFeaturedTrue();
        int featuredRealestateListSize = featuredRealestateList.size();
        int featuredRealestateInPage = 6;
        int featuredRealestatePage = (int) Math.ceil((double) featuredRealestateListSize / featuredRealestateInPage);
        List<CCustomers> agenList=customersServiceImpl.find6CCustomerTopToRealEstate();
        // Tạo danh sách các số trang để hiển thị
        List<Integer> pageNumbers = IntStream.rangeClosed(1, featuredRealestatePage).boxed()
                .collect(Collectors.toList());
        List<CBlog> blogs=blogServiceImpl.findTop10ByOrderByDateCreateDesc();
        // Đưa dữ liệu vào model để hiển thị trên trang JSP
        model.addAttribute("top10ViewRealestateList", top10ViewRealestateList);
        model.addAttribute("new6RealestateList", new6RealestateList);
        model.addAttribute("featuredRealestateListSize", featuredRealestateListSize);//số lượng featured Realestate
        model.addAttribute("pageNumbers", pageNumbers);// dữ liệu trang featured Realestate
        model.addAttribute("agenList", agenList);// dữ liệu trang agent
        model.addAttribute("blogs", blogs);// dữ liệu trang blog
        return modelAndView;
    }
}
