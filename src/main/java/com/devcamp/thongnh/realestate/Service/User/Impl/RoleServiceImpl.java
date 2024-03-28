package com.devcamp.thongnh.realestate.Service.User.Impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.User.Role;
import com.devcamp.thongnh.realestate.Respository.User.RoleRepository;
import com.devcamp.thongnh.realestate.Service.User.RoLeService;

@Service
public class RoleServiceImpl implements RoLeService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Set<Role> getById(Long id) {
        
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            return roles;
        }
        return Collections.emptySet();
    }
    
}
