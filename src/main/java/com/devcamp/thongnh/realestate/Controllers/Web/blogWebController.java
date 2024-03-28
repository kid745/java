package com.devcamp.thongnh.realestate.Controllers.Web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.devcamp.thongnh.realestate.Model.CBlog;
import com.devcamp.thongnh.realestate.Service.Impl.BlogServiceImpl;

@RestController
public class blogWebController {
    @Autowired
    private BlogServiceImpl blogServiceImpl;

    @RequestMapping("/blog")
    public ModelAndView blog(Model model) {
        ModelAndView modelAndView = new ModelAndView("blog");
        List<CBlog> blogList = blogServiceImpl.getAll();
        int countBlog = blogList.size();

        model.addAttribute("blogList", blogList);// dữ liệu blog
        model.addAttribute("countBlog", countBlog);// dữ liệu blog
        return modelAndView;
    }

    @RequestMapping("/blog/{id}")
    public ModelAndView blogDetail(Model model, @PathVariable("id") long pId) {
        ModelAndView modelAndView = new ModelAndView("blogDetail");
        CBlog blog = blogServiceImpl.getCBlogById(pId);
        blog.setView(blog.getView()+1);
        blogServiceImpl.saveCBlog(blog);
        model.addAttribute("blog", blog);// dữ liệu blog
        return modelAndView;
    }
}
