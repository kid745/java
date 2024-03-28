package com.devcamp.thongnh.realestate.Controllers.Api;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.thongnh.realestate.Model.CPhoto;
import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Service.Impl.PhotoServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.RealEstateServiceImpl;

@RestController
@CrossOrigin
@RequestMapping
public class PhotoController {
    @Autowired
    PhotoServiceImpl photoServiceImpl;
    @Autowired
    RealEstateServiceImpl realEstateServiceImpl;

    // // Get Photo By Id
    // @GetMapping("/photo/{id}")
    // public ResponseEntity<byte[]> getPhotoById(@PathVariable("id") long pId) {
    // try {
    // Optional<CPhoto> photo = photoServiceImpl.getById(pId);
    // if (photo.isPresent()) {
    // byte[] photoContent = photo.get().getContent();
    // Tika tika = new Tika();
    // String mimeType = tika.detect(photoContent);
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.parseMediaType(mimeType)); // Điều chỉnh
    // kiểu ảnh tùy theo định dạng
    // headers.setContentLength(photoContent.length);
    // return new ResponseEntity<>(photoContent, headers, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // } catch (Exception e) {
    // 
    // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    // Get Photo By RealEstate Id
    @GetMapping("/photo")
    public ResponseEntity<List<CPhoto>> getPhotoByIdOfRealEstate(@RequestParam("realEstateId") long pRealEstateId) {
        try {
            Optional<CRealEstate> realEstateId = realEstateServiceImpl.getById(pRealEstateId);
            if (realEstateId.isPresent()) {
                return new ResponseEntity<>(photoServiceImpl.getPhotoIdsByRealEstateId(pRealEstateId), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // Delete Photo By Id
    @DeleteMapping("/photo/{id}")
    public ResponseEntity<Object> deletePhotoById(@PathVariable("id") long pId) {
        try {
            Optional<CPhoto> photoId = photoServiceImpl.getById(pId);
            if (photoId.isPresent()) {
                photoServiceImpl.deletePhotoById(pId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete All Photo
    @DeleteMapping("/photo")
    public ResponseEntity<Object> deleteAllPhoto() {
        try {
            photoServiceImpl.deleteAllPhoto();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
