package com.devcamp.thongnh.realestate.Controllers.Api;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.thongnh.realestate.Model.CComment;
import com.devcamp.thongnh.realestate.Service.Impl.CommentServiceImpl;
import com.devcamp.thongnh.realestate.Service.SendMail.SendMailService;

@RequestMapping("/")
@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentServiceImpl commentServiceImpl;
    @Autowired
    SendMailService sendMailService;

    // Get All Data Comment
    @GetMapping("/comments")
    public ResponseEntity<List<CComment>> getAllComment() {
        try {
            List<CComment> comments = commentServiceImpl.getAll();
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Data Comment By Id
    @GetMapping("/comments/{id}")
    public ResponseEntity<CComment> getCommentById(@PathVariable("id") long pId) {
        try {
            Optional<CComment> commentId = commentServiceImpl.getById(pId);
            if (commentId.isPresent()) {
                return new ResponseEntity<>(commentId.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Data Comment By Page
    @GetMapping("/realestate/{id}/comments/page/{page}")
    public ResponseEntity<List<CComment>> getCommentByPage(@PathVariable("page") int pPage,
            @PathVariable("id") long pId) {
        try {
            int commentPageSize = 5;
            List<CComment> commentsByRealEstate = commentServiceImpl.getCommentsByRealEsetateId(pId);
            List<CComment> commentsComfirm = commentsByRealEstate.stream().filter(comment -> comment.isApprove())
                    .collect(Collectors.toList());
            // Sắp xếp comments theo thứ tự giảm dần theo id
            Collections.sort(commentsComfirm, (comment1, comment2) -> comment2.getId().compareTo(comment1.getId()));
            List<CComment> comments = commentServiceImpl.getCommentsByPage(commentsComfirm, pPage, commentPageSize);

            return new ResponseEntity<>(comments, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/comments/approve/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<Object> approveCommentById(@PathVariable("id") long pId) {
        try {
            Optional<CComment> commentId = commentServiceImpl.getById(pId);
            if (commentId.isPresent()) {
                CComment comment = commentId.get();
                comment.setApprove(true);
                 commentServiceImpl.saveComment(comment);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/comments/unapprove/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public ResponseEntity<Object> unApproveCommentById(@PathVariable("id") long pId,@RequestParam("reason") String reason) {
        try {
            Optional<CComment> commentId = commentServiceImpl.getById(pId);
            if (commentId.isPresent()) {
                CComment comment = commentId.get();
                comment.setApprove(false);
                commentServiceImpl.saveComment(comment);
                sendMailService.sendMail(reason);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
