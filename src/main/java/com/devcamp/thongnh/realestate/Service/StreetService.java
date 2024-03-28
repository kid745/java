package com.devcamp.thongnh.realestate.Service;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.CStreet;


public interface StreetService {
    List<CStreet> getAll();
    Optional<CStreet> getById(Long id);
    void saveStreet(CStreet street);
    void deleteStreetById(Long id);
    void deleteAllStreet();
}
