package com.devcamp.thongnh.realestate.Controllers.Api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.devcamp.thongnh.realestate.Model.CBlog;
import com.devcamp.thongnh.realestate.Service.Impl.BlogServiceImpl;
import com.devcamp.thongnh.realestate.Service.SendMail.SendMailService;

@RestController
@CrossOrigin
@RequestMapping
@Transactional(rollbackFor = Exception.class)
public class BlogController {
    @Autowired
    BlogServiceImpl blogServiceImpl;
    @Autowired
    SendMailService sendMailService;

    @GetMapping("/10newblog")
    public ResponseEntity<List<CBlog>> get10NewBlog() {
        try {
            return new ResponseEntity<>(blogServiceImpl.findTop10ByOrderByDateCreateDesc(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<CBlog> getBlogById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(blogServiceImpl.getCBlogById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/blog")
    public ResponseEntity<List<CBlog>> getAllBlog() {
        try {
            return new ResponseEntity<>(blogServiceImpl.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/blog/unapprove/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<Object> unapprove(@PathVariable("id") long id,@RequestParam("reason") String reason) {
        try {
            Optional<CBlog> cOptional = blogServiceImpl.findCBlogId(id);
            if (!cOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            CBlog cBlog = cOptional.get();
            cBlog.setApprove(false);
            blogServiceImpl.saveCBlog(cBlog);
            sendMailService.sendMail(reason);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/blog/approve/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<Object> approve(@PathVariable("id") long id) {
        try {
            Optional<CBlog> cOptional = blogServiceImpl.findCBlogId(id);
            if (!cOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            CBlog cBlog = cOptional.get();
            cBlog.setApprove(true);
            blogServiceImpl.saveCBlog(cBlog);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
