package com.devcamp.thongnh.realestate.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.devcamp.thongnh.realestate.Model.CCart;
import com.devcamp.thongnh.realestate.Model.User.CCustomers;

public interface CartService {
    List<CCart> getAllRealEstateByCustomerId(Long id);

    Long totalPriceAllRealEstateByCustomerId(Long id);

    List<CCustomers> getAllCustomerByAllRealEstate(List<CCart> carts);

    void deleteByCustomersIdAndRealestateId(Long customerId, Long realestateId);

    List<CCart> findByCustomersIdAndRealestateId(Long customerId, Long realestateId);

    void deleteById(Long id);

    void addCartByIdProject(Long realestateId,HttpServletRequest request);
}
