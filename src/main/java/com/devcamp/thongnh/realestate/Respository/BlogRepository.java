package com.devcamp.thongnh.realestate.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CBlog;

public interface BlogRepository extends JpaRepository<CBlog,Long> {

    List<CBlog> findTop10ByOrderByDateCreateDesc();
    List<CBlog> findByCustomersId(Long customerId);
} 
