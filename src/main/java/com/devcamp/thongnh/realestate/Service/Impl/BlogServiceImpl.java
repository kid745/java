package com.devcamp.thongnh.realestate.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.thongnh.realestate.Model.CBlog;
import com.devcamp.thongnh.realestate.Respository.BlogRepository;
import com.devcamp.thongnh.realestate.Service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<CBlog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public List<CBlog> findTop10ByOrderByDateCreateDesc() {
        return blogRepository.findTop10ByOrderByDateCreateDesc();
    }

    @Override
    public CBlog getCBlogById(Long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public CBlog saveCBlog(CBlog cBlog) {
       return blogRepository.save(cBlog);
    }

    @Override
    public List<CBlog> findByCustomersId(Long customerId) {
      return blogRepository.findByCustomersId(customerId);
    }

    @Override
    public Optional<CBlog> findCBlogId(Long id) {
       return blogRepository.findById(id);
    }

}
