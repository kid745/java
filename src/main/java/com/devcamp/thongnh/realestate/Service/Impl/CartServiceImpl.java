package com.devcamp.thongnh.realestate.Service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CCart;
import com.devcamp.thongnh.realestate.Model.User.CCustomers;
import com.devcamp.thongnh.realestate.Service.CartService;
import com.devcamp.thongnh.realestate.Service.User.Impl.CustomersServiceImpl;
import com.devcamp.thongnh.realestate.security.JwtUtil;
import com.devcamp.thongnh.realestate.security.UserPrincipal;
import com.devcamp.thongnh.realestate.Respository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private CustomersServiceImpl customersServiceImpl;

  @Autowired
  private RealEstateServiceImpl realEstateServiceImpl;

  @Override
  public List<CCart> getAllRealEstateByCustomerId(Long id) {
    return cartRepository.findByCustomersId(id);
  }

  @Override
  public Long totalPriceAllRealEstateByCustomerId(Long id) {
    List<CCart> carts = cartRepository.findByCustomersId(id);
    Long totalPrice = 0L;
    for (CCart cart : carts) {
      if (cart.getRealestate() != null) {
        totalPrice += cart.getRealestate().getPrice();
      }
    }
    return totalPrice;
  }

  @Override
  public List<CCustomers> getAllCustomerByAllRealEstate(List<CCart> carts) {
    Set<CCustomers> uniqueCustomers = new HashSet<>();

    for (CCart cart : carts) {
      if (cart.getCustomers() != null) {
        uniqueCustomers.add(cart.getCustomers());
      }
    }

    return new ArrayList<>(uniqueCustomers);
  }

  @Override
  public void deleteByCustomersIdAndRealestateId(Long customerId, Long realestateId) {
    cartRepository.deleteByCustomersIdAndRealestateId(customerId, realestateId);
  }

  @Override
  public List<CCart> findByCustomersIdAndRealestateId(Long customerId, Long realestateId) {
    return cartRepository.findByCustomersIdAndRealestateId(customerId, realestateId);
  }

  @Override
  public void deleteById(Long id) {
    cartRepository.deleteById(id);
  }

  @Override
  public void addCartByIdProject(Long realestateId, HttpServletRequest request) {
    String tokenValue = getTokenFromCookies(request);
    if (tokenValue == null) {
      return;
    }
    UserPrincipal user = jwtUtil.getUserFromToken(tokenValue);
    List<CCart> cCartList = cartRepository.findByCustomersIdAndRealestateId(user.getUserId(), realestateId);
    if (cCartList.size() == 0) {
      CCart newCart = new CCart();
      newCart.setCustomers(customersServiceImpl.getById(user.getUserId()).get());
      newCart.setRealestate(realEstateServiceImpl.getById(realestateId).get());
      cartRepository.save(newCart);
    }

  }

  private String getTokenFromCookies(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("token".equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }
}
