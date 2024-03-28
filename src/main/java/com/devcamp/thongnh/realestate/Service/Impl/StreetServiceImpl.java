package com.devcamp.thongnh.realestate.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CStreet;
import com.devcamp.thongnh.realestate.Respository.StreetRespoitory;
import com.devcamp.thongnh.realestate.Service.StreetService;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetRespoitory streetRepository;

    @Override
    public List<CStreet> getAll() {
        
        return streetRepository.findAll();
    }

    @Override
    public Optional<CStreet> getById(Long id) {
        
        Optional<CStreet> Street = streetRepository.findById(id);
        if (Street.isPresent()) {
            return Street;
        } else {
            return null;
        }
    }

    @Override
    public void saveStreet(CStreet street) {
        
        streetRepository.save(street);
    }

    @Override
    public void deleteStreetById(Long id) {
        
        streetRepository.deleteById(id);
    }

    @Override
    public void deleteAllStreet() {
        
        streetRepository.deleteAll();
    }

}
