package com.devcamp.thongnh.realestate.Controllers.Api;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CustomerController {
    @Autowired
    CustomersServiceImpl customerServiceImpl;

    // Get All Data Customer By Table
    @GetMapping("/customers")
    public ResponseEntity<List<CCustomers>> getAllCustomer() {
        try {
            List<CCustomers> customers = customerServiceImpl.getAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);

        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Data Customer By Id
    @GetMapping("/customers/{id}")
    public ResponseEntity<CCustomers> getCustomerById(@PathVariable("id") long pId) {
        try {
            Optional<CCustomers> customerId = customerServiceImpl.getById(pId);
            if (customerId.isPresent()) {
                return new ResponseEntity<>(customerId.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }  
    
    // Put Data Customer By Id
    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> putCustomerById(@RequestBody CCustomers pCustomer, @PathVariable("id") long pId) {
        try {
            Optional<CCustomers> customerEmail = customerServiceImpl.getByEmail(pCustomer.getEmail());
            Optional<CCustomers> customerMobie = customerServiceImpl.getByMobile(pCustomer.getMobile());
            Optional<CCustomers> customerId = customerServiceImpl.getById(pId);
            if (!customerId.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else if (customerEmail.isPresent() && customerEmail.get().getId() != pId) {
                return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
            } else if (customerMobie.isPresent() && customerMobie.get().getId() != pId) {
                return new ResponseEntity<>("Mobile numberalready exists", HttpStatus.CONFLICT);
            } else {
                CCustomers customersUpdate = customerId.get();
                customersUpdate.setContactName(pCustomer.getContactName());
                customersUpdate.setAddress(pCustomer.getAddress());
                customersUpdate.setMobile(pCustomer.getMobile());
                customersUpdate.setEmail(pCustomer.getEmail());
                customersUpdate.setNote(pCustomer.getNote());
                customerServiceImpl.saveCustomers(customersUpdate);
                return new ResponseEntity<>(customersUpdate, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Data Customer By Id
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable("id") long pId) {
        try {
            Optional<CCustomers> customerId = customerServiceImpl.getById(pId);
            if (customerId.isPresent()) {
                customerServiceImpl.deleteCustomersById(pId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete All Data Customer
    @DeleteMapping("/customers")
    public ResponseEntity<Object> deleteAllCustomer() {
        try {
            customerServiceImpl.deleteAllCustomers();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
