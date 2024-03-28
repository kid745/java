package com.devcamp.thongnh.realestate.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CComment;

public interface CommentRepository extends JpaRepository<CComment,Long> {
    List<CComment> findByRealestateId(Long realEstateId);
}
