package com.devcamp.thongnh.realestate.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CPhoto;

public interface PhotoRepository extends JpaRepository<CPhoto, Long> {
    List<CPhoto> findByRealestateId(Long realestateId);
}
