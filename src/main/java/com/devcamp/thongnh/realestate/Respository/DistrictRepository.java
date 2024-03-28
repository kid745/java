package com.devcamp.thongnh.realestate.Respository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CDistrict;

public interface DistrictRepository extends JpaRepository<CDistrict,Long > {
    
}
