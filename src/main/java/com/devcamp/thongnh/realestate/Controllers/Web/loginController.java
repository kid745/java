package com.devcamp.thongnh.realestate.Controllers.Web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class loginController {
    @RequestMapping("/login")
     public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
     }
}
