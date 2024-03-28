package com.devcamp.thongnh.realestate.Respository.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devcamp.thongnh.realestate.Model.User.CCustomers;

public interface CustomersRepository extends JpaRepository<CCustomers, Long> {
    // Kiểm tra xem mobile đã tồn tại trong cơ sở dữ liệu hay chưa
    Optional<CCustomers> findByMobile(String mobile);

    // Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
    Optional<CCustomers> findByEmail(String email);

    // Kiểm tra username đã tồn tại trong cơ sở dữ liệu hay chưa
    CCustomers findByUsername(String username);

    // sắp xếp theo kích thước của danh sách realEstate
    @Query("SELECT c FROM CCustomers c ORDER BY SIZE(c.realEstate) DESC")
    List<CCustomers> findAllByOrderByRealEstateSizeDesc();
}
