package com.devcamp.thongnh.realestate.Controllers.Api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.thongnh.realestate.Model.CDistrict;
import com.devcamp.thongnh.realestate.Model.CProvince;
import com.devcamp.thongnh.realestate.Model.CWard;
import com.devcamp.thongnh.realestate.Service.Impl.DistrictServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.ProvinceServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.WardServiceImpl;

@RequestMapping("/")
@RestController
@CrossOrigin
public class WardController {
    @Autowired
    ProvinceServiceImpl pIProvinceServiceImpl;

    @Autowired
    DistrictServiceImpl pDistrictServiceImpl;

    @Autowired
    WardServiceImpl pIWardServiceImpl;

    // Get All Ward
    @GetMapping("/province/district/ward")
    public ResponseEntity<List<CWard>> getAllWard() {
        try {
            List<com.devcamp.thongnh.realestate.Model.CWard> wardList = pIWardServiceImpl.getAll();
            return new ResponseEntity<>(wardList, HttpStatus.OK);
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Ward By Id
    @GetMapping("/province/district/ward/{id}")
    public ResponseEntity<CWard> getWardById(@PathVariable("id") long pId) {
        try {
            Optional<CWard> wardById = pIWardServiceImpl.getById(pId);
            if (wardById.isPresent()) {
                return new ResponseEntity<>(wardById.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get All Ward By Id Of District
    @GetMapping("/province/district/{id}/ward")
    public ResponseEntity<List<CWard>> getAllWardByIdOfDistrict(@PathVariable("id") long pId) {
        try {
            Optional<CDistrict> districtId = pDistrictServiceImpl.getById(pId);
            if (districtId.isPresent()) {
                return new ResponseEntity<>(districtId.get().getWard(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get All Ward By Id Of Province
    @GetMapping("/province/{id}/district/ward")
    public ResponseEntity<List<CWard>> getAllWardByIdOfProvince(@PathVariable("id") long pId) {
        try {
            Optional<CProvince> provinceId = pIProvinceServiceImpl.getById(pId);
            if (provinceId.isPresent()) {
                List<CDistrict> districtList = provinceId.get().getDistrict();
                List<CWard> wardList = new ArrayList<>();
                districtList.forEach(district -> wardList.addAll(district.getWard()));
                return new ResponseEntity<>(wardList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Post Ward
    @PostMapping("/province/district/{id}/ward")
    public ResponseEntity<Object> postWard(@PathVariable("id") long pId, @RequestBody CWard pWard) {
        try {
            Optional<CDistrict> districtId = pDistrictServiceImpl.getById(pId);
            if (districtId.isPresent()) {
                pWard.setProvince(districtId.get().getProvince().getId());
                pWard.setDistrict(districtId.get());
                pIWardServiceImpl.saveWard(pWard);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Put Ward
    @PutMapping("/province/district/{districtId}/ward/{wardId}")
    public ResponseEntity<Object> putWard(@PathVariable("districtId") long pDistrictId,
            @PathVariable("wardId") long pWardId, @RequestBody CWard pWard) {
        try {
            Optional<CDistrict> districtId = pDistrictServiceImpl.getById(pDistrictId);
            Optional<CWard> wardId = pIWardServiceImpl.getById(pWardId);
            if (districtId.isPresent() && wardId.isPresent()) {
                pWard.setDistrict(districtId.get());
                pWard.setProvince(districtId.get().getProvince().getId());
                pIWardServiceImpl.saveWard(pWard);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Ward By Id
    @DeleteMapping("/province/district/ward/{id}")
    public ResponseEntity<Object> deleteWardById(@PathVariable("id") long pId) {
        try {
            Optional<CWard> wardId = pIWardServiceImpl.getById(pId);
            if (wardId.isPresent()) {
                pIWardServiceImpl.deleteWardById(pId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Delete All Ward
    @DeleteMapping("/province/district/ward")
    public ResponseEntity<Object> deleteAllWard(){
        try {
            pIWardServiceImpl.deleteAllWard();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
