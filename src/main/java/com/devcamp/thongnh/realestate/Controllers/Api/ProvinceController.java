package com.devcamp.thongnh.realestate.Controllers.Api;

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

import com.devcamp.thongnh.realestate.Model.CProvince;
import com.devcamp.thongnh.realestate.Service.Impl.ProvinceServiceImpl;

@RequestMapping("/")
@RestController
@CrossOrigin
public class ProvinceController {
    @Autowired
    ProvinceServiceImpl provinceServiceImpl;

    // Get All Province
    @GetMapping("/province")
    public ResponseEntity<List<CProvince>> getAllProvince() {
        {
            try {
                List<CProvince> provinces = provinceServiceImpl.getAll();
                return new ResponseEntity<>(provinces, HttpStatus.OK);
            } catch (Exception e) {
                
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    // Get Province By Id
    @GetMapping("/province/{id}")
    public ResponseEntity<CProvince> getProvinceById(@PathVariable("id") long pId) {
        try {
            Optional<CProvince> provinceId = provinceServiceImpl.getById(pId);
            if (provinceId.isPresent()) {
                return new ResponseEntity<>(provinceId.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Post Province
    @PostMapping("/province")
    public ResponseEntity<CProvince> postProvince(@RequestBody CProvince pProvince) {
        try {
            Optional<CProvince> provinceCode = provinceServiceImpl.getByCode(pProvince.getCode());
            if (!provinceCode.isPresent()) {
                provinceServiceImpl.saveProvince(pProvince);
                return new ResponseEntity<>(pProvince, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Put Province By Id
    @PutMapping("/province/{id}")
    public ResponseEntity<Object> putProvince(@RequestBody CProvince pProvince, @PathVariable("id") long pId) {
        try {
            Optional<CProvince> provinceId = provinceServiceImpl.getById(pId);
            Optional<CProvince> provinceCode = provinceServiceImpl.getByCode(pProvince.getCode());
            if (!provinceId.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else if (provinceCode.isPresent() && provinceCode.get().getId() != pId) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                CProvince provinceUpdate = provinceId.get();
                provinceUpdate.setName(pProvince.getName());
                provinceUpdate.setCode(pProvince.getCode());
                provinceServiceImpl.saveProvince(provinceUpdate);
                return new ResponseEntity<>(provinceUpdate, HttpStatus.OK);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Province By Id
    @DeleteMapping("/province/{id}")
    public ResponseEntity<Object> deleteByIdProvince(@PathVariable("id") Long pId) {
        try {
            Optional<CProvince> provinceId = provinceServiceImpl.getById(pId);
            if (provinceId.isPresent()) {
                provinceServiceImpl.deleteProvinceById(pId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete All Province
    @DeleteMapping("/province")
    public ResponseEntity<Object> deleteAllProvince() {
        try {
            provinceServiceImpl.deleteAllProvince();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
