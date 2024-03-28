package com.devcamp.thongnh.realestate.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CProvince;
import com.devcamp.thongnh.realestate.Respository.ProvinceRepository;
import com.devcamp.thongnh.realestate.Service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<CProvince> getAll() {
        
        return provinceRepository.findAll();
    }

    @Override
    public Optional<CProvince> getById(Long id) {
        
        Optional<CProvince> Province = provinceRepository.findById(id);
        if (Province.isPresent()) {
            return Province;
        } else {
            return null;
        }
    }

    @Override
    public void saveProvince(CProvince province) {
        
        provinceRepository.save(province);
    }

    @Override
    public void deleteProvinceById(Long id) {
        
        provinceRepository.deleteById(id);
    }

    @Override
    public void deleteAllProvince() {
        
        provinceRepository.deleteAll();
    }

    @Override
    public Optional<CProvince> getByCode(String code) {
        
        return provinceRepository.findByCode(code);
    }

}
