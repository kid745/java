package com.devcamp.thongnh.realestate.Respository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.CProvince;
public interface ProvinceRepository extends JpaRepository<CProvince, Long> {
    Optional<CProvince> findByCode(String code);
}
