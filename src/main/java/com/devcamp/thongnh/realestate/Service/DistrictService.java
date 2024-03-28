package com.devcamp.thongnh.realestate.Service;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.CDistrict;



public interface DistrictService {
    List<CDistrict> getAll();
    Optional<CDistrict> getById(Long id);
    void saveDistrict(CDistrict district);
    void deleteDistrictById(Long id);
    void deleteAllDistrict();
}
