package com.devcamp.thongnh.realestate.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CComment;
import com.devcamp.thongnh.realestate.Respository.CommentRepository;
import com.devcamp.thongnh.realestate.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CComment> getAll() {
        
        return commentRepository.findAll();
    }

    @Override
    public Optional<CComment> getById(Long id) {
        
        return commentRepository.findById(id);
    }

    @Override
    public void saveComment(CComment customers) {
        
        commentRepository.save(customers);
    }

    @Override
    public void deleteCommentsById(Long id) {
        
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAllComment() {
        
        commentRepository.deleteAll();
    }

    @Override
    public List<CComment> getCommentsByRealEsetateId(Long realEstateId) {
        return commentRepository.findByRealestateId(realEstateId);
    }

    @Override
    public List<CComment> getCommentsByPage(List<CComment> comments,int page, int pageSize) {
        int totalComments = comments.size();
    
    // Tính toán vị trí của bình luận đầu tiên trên trang hiện tại
    int startIndex = (page - 1) * pageSize;
    
    // Tính toán vị trí của bình luận cuối cùng trên trang hiện tại
    int endIndex = Math.min(startIndex + pageSize, totalComments);
    
    // Kiểm tra xem có bình luận nào không
    if (startIndex >= totalComments || startIndex < 0) {
        return Collections.emptyList(); // Trả về danh sách rỗng nếu không có bình luận nào
    }
    
    // Lấy danh sách bình luận trên trang hiện tại
    List<CComment> commentsOnPage = comments.subList(startIndex, endIndex);
    
    return commentsOnPage;

    }

}
