package com.devcamp.thongnh.realestate.Service;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.CPhoto;


public interface PhotoService {
    List<CPhoto> getAll();
    Optional<CPhoto> getById(Long id);
    void savePhoto(CPhoto photo);
    void deletePhotoById(Long id);
    void deleteAllPhoto();
    List<CPhoto>  getPhotoIdsByRealEstateId(Long realEstateId);
}
