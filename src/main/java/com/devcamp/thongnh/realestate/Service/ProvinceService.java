package com.devcamp.thongnh.realestate.Service;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.CProvince;

public interface ProvinceService {
    List<CProvince> getAll();

    Optional<CProvince> getById(Long id);

    void saveProvince(CProvince province);

    void deleteProvinceById(Long id);

    void deleteAllProvince();

    Optional<CProvince> getByCode(String code);
}
