package com.springjpa.springjpademo.repository;

import com.springjpa.springjpademo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//      we don't need to use Repository to create Repository since JpaRepository implements Repository interface

public interface ProductRepository extends JpaRepository<Product,Long> {

    

}
