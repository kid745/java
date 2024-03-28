package com.devcamp.thongnh.realestate.Service.User;

import java.util.Set;

import com.devcamp.thongnh.realestate.Model.User.Role;

public interface RoLeService {
    Set<Role>  getById(Long id);
}
