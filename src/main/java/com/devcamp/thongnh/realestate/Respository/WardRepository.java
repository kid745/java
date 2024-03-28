package com.devcamp.thongnh.realestate.Respository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CWard;

public interface WardRepository extends JpaRepository<CWard, Long> {

}
