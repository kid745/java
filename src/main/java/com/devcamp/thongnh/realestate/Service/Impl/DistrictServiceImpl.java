package com.devcamp.thongnh.realestate.Service.Impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CDistrict;
import com.devcamp.thongnh.realestate.Respository.DistrictRepository;
import com.devcamp.thongnh.realestate.Service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<CDistrict> getAll() {
        
        return districtRepository.findAll();
    }

    @Override
    public Optional<CDistrict> getById(Long id) {
        
        Optional<CDistrict> district = districtRepository.findById(id);
        if (district.isPresent()) {
            return district;
        } else {
            return null;
        }
    }

    @Override
    public void saveDistrict(CDistrict district) {
        
        districtRepository.save(district);
    }

    @Override
    public void deleteDistrictById(Long id) {
        
        districtRepository.deleteById(id);
    }

    @Override
    public void deleteAllDistrict() {
        
        districtRepository.deleteAll();
    }

}
