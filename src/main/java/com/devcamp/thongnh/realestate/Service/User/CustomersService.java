package com.devcamp.thongnh.realestate.Service.User;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.security.UserPrincipal;


public interface CustomersService {
    List<CCustomers> getAll();
    Optional<CCustomers> getById(Long id);
    void saveCustomers(CCustomers customers);
    void deleteCustomersById(Long id);
    void deleteAllCustomers();
    UserPrincipal getByUsername(String username);
    Optional<CCustomers> getByMobile(String mobile);
    Optional<CCustomers> getByEmail(String email);
    List<CCustomers> find6CCustomerTopToRealEstate();
}
