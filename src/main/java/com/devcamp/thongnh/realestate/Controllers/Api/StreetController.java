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
import com.devcamp.thongnh.realestate.Model.CStreet;
import com.devcamp.thongnh.realestate.Service.Impl.DistrictServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.ProvinceServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.StreetServiceImpl;

@RequestMapping("/")
@RestController
@CrossOrigin
public class StreetController {
    @Autowired
    ProvinceServiceImpl provinceServiceImpl;

    @Autowired
    DistrictServiceImpl districtServiceImpl;

    @Autowired
    StreetServiceImpl streetServiceImpl;

    // Get All Street
    @GetMapping("/province/district/street")
    public ResponseEntity<List<CStreet>> getAllStreet() {
        try {
            List<CStreet> streetList = streetServiceImpl.getAll();
            return new ResponseEntity<>(streetList, HttpStatus.OK);
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Street By Id
    @GetMapping("/province/district/street/{id}")
    public ResponseEntity<CStreet> getStreetById(@PathVariable("id") long pId) {
        try {
            Optional<CStreet> streetById = streetServiceImpl.getById(pId);
            if (streetById.isPresent()) {
                return new ResponseEntity<>(streetById.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get All Street By Id Of District
    @GetMapping("/province/district/{id}/street")
    public ResponseEntity<List<CStreet>> getAllStreetByIdOfDistrict(@PathVariable("id") long pId) {
        try {
            Optional<CDistrict> districtId = districtServiceImpl.getById(pId);
            if (districtId.isPresent()) {
                return new ResponseEntity<>(districtId.get().getStreet(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get All Street By Id Of Province
    @GetMapping("/province/{id}/district/street")
    public ResponseEntity<List<CStreet>> getAllStreetByIdOfProvince(@PathVariable("id") long pId) {
        try {
            Optional<CProvince> provinceId = provinceServiceImpl.getById(pId);
            if (provinceId.isPresent()) {
                List<CDistrict> districtList = provinceId.get().getDistrict();
                List<CStreet> streetList = new ArrayList<>();
                districtList.forEach(district -> streetList.addAll(district.getStreet()));
                return new ResponseEntity<>(streetList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Post Street
    @PostMapping("/province/district/{id}/street")
    public ResponseEntity<Object> postStreet(@PathVariable("id") long pId, @RequestBody CStreet pStreet) {
        try {
            Optional<CDistrict> districtId = districtServiceImpl.getById(pId);
            if (districtId.isPresent()) {
                Long provinceId = districtId.get().getProvince().getId();
                pStreet.setProvince(provinceId);
                pStreet.setDistrict(districtId.get());
                streetServiceImpl.saveStreet(pStreet);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Put Street
    @PutMapping("/province/district/{districtId}/street/{streetId}")
    public ResponseEntity<Object> putStreet(@PathVariable("districtId") long pDistrictId,
            @PathVariable("streetId") long pStreetId, @RequestBody CStreet pStreet) {
        try {
            Optional<CDistrict> districtId = districtServiceImpl.getById(pDistrictId);
            Optional<CStreet> streetId = streetServiceImpl.getById(pStreetId);
            if (districtId.isPresent() && streetId.isPresent()) {
                Long provinceId = districtId.get().getProvince().getId();
                pStreet.setDistrict(districtId.get());
                pStreet.setProvince(provinceId);
                streetServiceImpl.saveStreet(pStreet);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Street By Id
    @DeleteMapping("/province/district/street/{id}")
    public ResponseEntity<Object> deleteStreetById(@PathVariable("id") long pId) {
        try {
            Optional<CStreet> streetId = streetServiceImpl.getById(pId);
            if (streetId.isPresent()) {
                streetServiceImpl.deleteStreetById(pId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Delete All Street
    @DeleteMapping("/province/district/street")
    public ResponseEntity<Object> deleteAllStreet(){
        try {
            streetServiceImpl.deleteAllStreet();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
