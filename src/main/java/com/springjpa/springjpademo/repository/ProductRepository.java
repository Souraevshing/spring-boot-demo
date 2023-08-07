package com.springjpa.springjpademo.repository;

import com.springjpa.springjpademo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

//      we don't need to use Repository to create Repository since JpaRepository implements Repository interface

public interface ProductRepository extends JpaRepository<Product,Long> {



}
