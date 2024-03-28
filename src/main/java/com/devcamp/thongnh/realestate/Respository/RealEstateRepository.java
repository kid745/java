package com.devcamp.thongnh.realestate.Respository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CRealEstate;
import com.devcamp.thongnh.realestate.Model.User.CCustomers;

public interface RealEstateRepository extends JpaRepository<CRealEstate, Long> {
    List<CRealEstate> findTop6ByOrderByIdDesc();

    List<CRealEstate> findByFeaturedTrue();

    List<CRealEstate> findByCustomersId(Long customerId);

    List<CRealEstate> findByHiddenAndApproveAndStatus(boolean hidden, boolean approve, boolean status);

    List<CRealEstate> findByCustomersAndIdNot(CCustomers customers, Long id);

    List<CRealEstate> findTop10ByOrderByViewDesc(Pageable pageable);

    List<CRealEstate> findTop6ByOrderByDateCreateDesc();

    List<CRealEstate> findByHiddenAndApprove(boolean hidden, boolean approve);

}
