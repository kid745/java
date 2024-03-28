package com.devcamp.thongnh.realestate.Controllers.Api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Respository.RealEstateRepository;
import com.devcamp.thongnh.realestate.Service.Impl.RealEstateServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.StreetServiceImpl;
import com.devcamp.thongnh.realestate.Service.Impl.WardServiceImpl;
import com.devcamp.thongnh.realestate.Service.SendMail.SendMailService;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;

@RequestMapping("/")
@RestController
@CrossOrigin
public class RealEstateController {
    @Autowired
    WardServiceImpl wardServiceImpl;

    @Autowired
    CustomersServiceImpl customerServiceImpl;

    @Autowired
    StreetServiceImpl streetServiceImpl;

    @Autowired
    RealEstateServiceImpl realEstateServiceImpl;

    @Autowired
    RealEstateRepository realEstateRepository;

    @Autowired
    SendMailService sendMailService;

    // Get All RealEstate By Hidden False And Approve True And Status False
    @GetMapping("/realestate")
    public ResponseEntity<List<CRealEstate>> getAllRealEstateByHiddenFalseAndApproveTrueAndStatusFalse() {
        try {
            List<CRealEstate> realEstateList = realEstateServiceImpl
                    .getAllRealEstateByHiddenFalseAndApproveTrueAndStatusFalse();
            return new ResponseEntity<>(realEstateList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get All RealEstate
    @GetMapping("/all/realestate")
    public ResponseEntity<List<CRealEstate>> getAllRealEstate() {
        try {
            List<CRealEstate> realEstateList = realEstateServiceImpl.getAll();
            return new ResponseEntity<>(realEstateList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get RealEstate By Id
    @GetMapping("/realestate/{id}")
    public ResponseEntity<CRealEstate> getRealEstateById(@PathVariable("id") long pId) {
        try {
            Optional<CRealEstate> realEstateId = realEstateServiceImpl.getById(pId);
            if (realEstateId.isPresent()) {
                return new ResponseEntity<>(realEstateId.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get RealEstate By Id Customer
    @GetMapping("/realestate/customer/{id}")
    public ResponseEntity<List<CRealEstate>> getAllRealEstateByCustomerId(@PathVariable("id") long pId) {
        try {
            return new ResponseEntity<>(realEstateServiceImpl.getRealEstateByCustomerId(pId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get sold RealEstate By Id Customer
    @GetMapping("/realestate/customer/sold/{id}")
    public ResponseEntity<List<CRealEstate>> getSoldRealEstateByCustomerId(@PathVariable("id") long pId) {
        try {
            List<CRealEstate> realEstates = realEstateServiceImpl.getRealEstateByCustomerId(pId);
            return new ResponseEntity<>(realEstateServiceImpl.filterByStatusSold(realEstates), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get RealEstate By Id Customer Not Input RealEstate Id
    @GetMapping("/realestate/{id}/customer")
    public ResponseEntity<List<CRealEstate>> getOtherRealEstatesWithSameCustomers(@PathVariable("id") long pId) {
        try {
            return new ResponseEntity<>(realEstateServiceImpl.getOtherRealEstatesWithSameCustomers(pId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get RealEstate Featured By Of page
    @GetMapping("/realestate/featured/page/{page}")
    public ResponseEntity<List<CRealEstate>> getRealEstateFeaturedByPage(@PathVariable("page") int page) {
        try {
            List<CRealEstate> realEstateList = realEstateServiceImpl.getRealEstateByFeaturedTrue();
            List<CRealEstate> realEstateListInPage = realEstateServiceImpl.get6RealEstatesInPage(realEstateList, page);
            return new ResponseEntity<>(realEstateListInPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Put RealEstate Featured
    @GetMapping("/realestate/featured/{id}")
    public ResponseEntity<Object> updateFeaturedRealestate(@PathVariable("id") long pId) {
        try {
            Optional<CRealEstate> realEstateId = realEstateServiceImpl.getById(pId);
            CRealEstate realEstate = realEstateId.get();
            realEstate.setFeatured(true);
            realEstateServiceImpl.saveRealEstate(realEstate);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Put RealEstate UnFeatured
    @GetMapping("/realestate/unfeatured/{id}")
    public ResponseEntity<Object> updateUnFeaturedRealestate(@PathVariable("id") long pId,
            @RequestParam("reason") String reason) {
        try {
            Optional<CRealEstate> realEstateId = realEstateServiceImpl.getById(pId);
            CRealEstate realEstate = realEstateId.get();
            realEstate.setFeatured(false);
            realEstateServiceImpl.saveRealEstate(realEstate);
            sendMailService.sendMail(reason);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Put RealEstate approve
    @GetMapping("/realestate/approve/{id}")
    public ResponseEntity<Object> updateApproveRealestate(@PathVariable("id") long pId) {
        try {
            Optional<CRealEstate> realEstateId = realEstateServiceImpl.getById(pId);
            CRealEstate realEstate = realEstateId.get();
            realEstate.setApprove(true);
            realEstateServiceImpl.saveRealEstate(realEstate);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Put RealEstate Unapprove
    @GetMapping("/realestate/unapprove/{id}")
    public ResponseEntity<Object> updateUnApproveRealestate(@PathVariable("id") long pId,
            @RequestParam("reason") String reason) {
        try {
            Optional<CRealEstate> realEstateId = realEstateServiceImpl.getById(pId);
            CRealEstate realEstate = realEstateId.get();
            realEstate.setApprove(false);
            realEstateServiceImpl.saveRealEstate(realEstate);
            sendMailService.sendMail(reason);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete RealEstate
    @DeleteMapping("/realestate/{id}")
    public ResponseEntity<Object> deleteRealEstate(@PathVariable("id") long pId) {
        try {
            Optional<CRealEstate> realEstateId = realEstateServiceImpl.getById(pId);
            if (realEstateId.isPresent()) {
                realEstateServiceImpl.deleteRealEstateById(pId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete All RealEstate
    @DeleteMapping("/realestate")
    public ResponseEntity<Object> deleteAllRealEstate() {
        try {
            realEstateServiceImpl.deleteAllRealEstate();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
