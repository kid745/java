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

import com.devcamp.thongnh.realestate.Model.CDistrict;
import com.devcamp.thongnh.realestate.Model.CProvince;
import com.devcamp.thongnh.realestate.Service.Impl.DistrictServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.ProvinceServiceImpl;

@RequestMapping("/")
@RestController
@CrossOrigin
public class DistrictController {
    @Autowired
    ProvinceServiceImpl pProvinceServiceImpl;

    @Autowired
    DistrictServiceImpl districtServiceImpl;

    // Get All District
    @GetMapping("/province/district")
    public ResponseEntity<List<CDistrict>> getAllDistrict() {
        try {
            List<CDistrict> districts = districtServiceImpl.getAll();
            return new ResponseEntity<>(districts, HttpStatus.OK);
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get All District By Id Of Province
    @GetMapping("/province/{id}/district")
    public ResponseEntity<List<CDistrict>> getAllDistrictByIdOfProvince(@PathVariable("id") long pId) {
        try {
            Optional<CProvince> provinceId = pProvinceServiceImpl.getById(pId);
            if (provinceId.isPresent()) {
                List<CDistrict> districts = provinceId.get().getDistrict();
                return new ResponseEntity<>(districts, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get District By Id
    @GetMapping("/province/district/{id}")
    public ResponseEntity<CDistrict> getDistrictById(@PathVariable("id") long pId) {
        try {

            Optional<CDistrict> districtId = districtServiceImpl.getById(pId);
            if (districtId.isPresent()) {
                return new ResponseEntity<>(districtId.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Post District
    @PostMapping("province/{id}/district")
    public ResponseEntity<Object> postDistrict(@PathVariable("id") long pId, @RequestBody CDistrict pDistrict) {
        try {
            Optional<CProvince> provinceId = pProvinceServiceImpl.getById(pId);
            if (provinceId.isPresent()) {
                CProvince province = provinceId.get();
                pDistrict.setProvince(province);
                districtServiceImpl.saveDistrict(pDistrict);
                return new ResponseEntity<>(pDistrict, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Put District
    @PutMapping("province/{provinceId}/district/{districtId}")
    public ResponseEntity<Object> putDistrict(@PathVariable("provinceId") long pProvinceId,
            @PathVariable("districtId") long pDistrictId, @RequestBody CDistrict pDistrict) {
        try {
            Optional<CDistrict> districtId = districtServiceImpl.getById(pDistrictId);
            Optional<CProvince> provinceId = pProvinceServiceImpl.getById(pProvinceId);
            if (!districtId.isPresent() || !provinceId.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                pDistrict.setProvince(provinceId.get());
                districtServiceImpl.saveDistrict(pDistrict);
                return new ResponseEntity<>(pDistrict, HttpStatus.OK);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete District By Id
    @DeleteMapping("province/district/{id}")
    public ResponseEntity<Object> deleteDistrictById(@PathVariable("id") long pId) {
        try {
            Optional<CDistrict> districtId = districtServiceImpl.getById(pId);
            if (districtId.isPresent()) {
                districtServiceImpl.deleteDistrictById(pId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Delete All District
    @DeleteMapping("province/district")
    public   ResponseEntity<Object> deleteAllDistrict(){
        try {
            districtServiceImpl.deleteAllDistrict();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
