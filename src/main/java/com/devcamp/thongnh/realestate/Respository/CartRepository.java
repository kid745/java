package com.devcamp.thongnh.realestate.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CCart;

public interface CartRepository extends JpaRepository<CCart,Long> {
    List<CCart> findByCustomersId(Long customerId);
    List<CCart> findByCustomersIdAndRealestateId(Long customerId, Long realestateId);   
    void deleteByCustomersIdAndRealestateId(Long customerId, Long realestateId);

}