package com.devcamp.thongnh.realestate.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CPhoto;
import com.devcamp.thongnh.realestate.Respository.PhotoRepository;
import com.devcamp.thongnh.realestate.Service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<CPhoto> getAll() {
        
        return photoRepository.findAll();
    }

    @Override
    public Optional<CPhoto> getById(Long id) {
        
        Optional<CPhoto> Photo = photoRepository.findById(id);
        if (Photo.isPresent()) {
            return Photo;
        } else {
            return null;
        }
    }

    @Override
    public void savePhoto(CPhoto photo) {
        
        photoRepository.save(photo);
    }

    @Override
    public void deletePhotoById(Long id) {
        
        photoRepository.deleteById(id);
    }

    @Override
    public void deleteAllPhoto() {
        
        photoRepository.deleteAll();
    }

    @Override
    public List<CPhoto> getPhotoIdsByRealEstateId(Long realEstateId) {
        
        List<CPhoto> photos = photoRepository.findByRealestateId(realEstateId);
        return photos;
    }
}
