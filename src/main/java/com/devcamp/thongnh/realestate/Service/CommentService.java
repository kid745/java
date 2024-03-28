package com.devcamp.thongnh.realestate.Service;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.CComment;


public interface CommentService {
    List<CComment> getAll();
    Optional<CComment> getById(Long id);
    void saveComment(CComment customers);
    void deleteCommentsById(Long id);
    void deleteAllComment();
    List<CComment> getCommentsByRealEsetateId(Long realEstateId);
    List<CComment> getCommentsByPage(List<CComment> comments,int page, int pageSize);
}
