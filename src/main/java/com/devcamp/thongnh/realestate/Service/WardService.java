package com.devcamp.thongnh.realestate.Service;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.CWard;


public interface WardService {
    List<CWard> getAll();
    Optional<CWard> getById(Long id);
    void saveWard(CWard ward);
    void deleteWardById(Long id);
    void deleteAllWard();
}
