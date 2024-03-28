package com.devcamp.thongnh.realestate.Controllers.Api.SendEmail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.thongnh.realestate.Service.SendMail.SendMailService;

@RestController
@CrossOrigin
public class SendEmailController {
    @Autowired
    SendMailService sendMailService;

    @PostMapping("/sendemail")
    public ResponseEntity<Object> sendEmail(@RequestParam("email") String email,@RequestParam("content") String content) {
        try {
            sendMailService.sendMail(content);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
