package com.devcamp.thongnh.realestate.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CStreet;
public interface StreetRespoitory extends JpaRepository<CStreet,Long> {
    
}
