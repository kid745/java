package com.devcamp.thongnh.realestate.Service;

import java.util.List;
import java.util.Optional;

import com.devcamp.thongnh.realestate.Model.CBlog;

public interface BlogService {
    Optional<CBlog> findCBlogId(Long id);
    List<CBlog> getAll();
    CBlog getCBlogById(Long id);
    CBlog saveCBlog(CBlog cBlog);
    List<CBlog> findTop10ByOrderByDateCreateDesc();
    List<CBlog> findByCustomersId(Long customerId);
}