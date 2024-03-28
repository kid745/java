package com.devcamp.thongnh.realestate.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CWard;
import com.devcamp.thongnh.realestate.Respository.WardRepository;
import com.devcamp.thongnh.realestate.Service.WardService;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardRepository wardRespository;

    @Override
    public List<CWard> getAll() {
        
        return wardRespository.findAll();
    }

    @Override
    public Optional<CWard> getById(Long id) {
        
        Optional<CWard> ward = wardRespository.findById(id);
        if (ward.isPresent()) {
            return ward;
        } else {
            return null;
        }
    }

    @Override
    public void saveWard(CWard ward) {
        
        wardRespository.save(ward);
    }

    @Override
    public void deleteWardById(Long id) {
        
        wardRespository.deleteById(id);
    }

    @Override
    public void deleteAllWard() {
        
        wardRespository.deleteAll();
    }

}
