package com.devcamp.thongnh.realestate.Respository.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.thongnh.realestate.Model.User.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    
}
