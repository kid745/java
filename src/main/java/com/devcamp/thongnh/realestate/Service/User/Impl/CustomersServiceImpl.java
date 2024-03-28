package com.devcamp.thongnh.realestate.Service.User.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Respository.User.CustomersRepository;
import com.devcamp.thongnh.realestate.Service.User.CustomersService;
import com.devcamp.thongnh.realestate.security.UserPrincipal;

@Service
public class CustomersServiceImpl implements CustomersService {
    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public List<CCustomers> getAll() {

        return customersRepository.findAll();
    }

    @Override
    public Optional<CCustomers> getById(Long id) {

        Optional<CCustomers> Customers = customersRepository.findById(id);
        if (Customers.isPresent()) {
            return Customers;
        } else {
            return null;
        }
    }

    @Override
    public void saveCustomers(CCustomers customers) {

        customersRepository.save(customers);
    }

    @Override
    public void deleteCustomersById(Long id) {

        customersRepository.deleteById(id);
    }

    @Override
    public void deleteAllCustomers() {

        customersRepository.deleteAll();
    }

    @Override
    public UserPrincipal getByUsername(String username) {
        CCustomers user = customersRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();
            if (null != user.getRoles())
                user.getRoles().forEach(r -> {
                    authorities.add(r.getRoleKey());
                    r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
                });
            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }

    @Override
    public Optional<CCustomers> getByMobile(String mobile) {

        return customersRepository.findByMobile(mobile);
    }

    @Override
    public Optional<CCustomers> getByEmail(String email) {

        return customersRepository.findByEmail(email);
    }

    @Override
    public List<CCustomers> find6CCustomerTopToRealEstate() {
        List<CCustomers> cCustomersList = customersRepository.findAllByOrderByRealEstateSizeDesc();
        return cCustomersList.subList(0, Math.min(cCustomersList.size(), 6));

    }

}
