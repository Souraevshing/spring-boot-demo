package com.springjpa.springjpademo.repository;

import com.springjpa.springjpademo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveData() {
        Product product = new Product();
        product.setName("MacBook Pro");
        product.setDescription("Apple Product");
        product.setSku("1500");
        product.setPrice(new BigDecimal(129900));
        product.setActive(true);
        product.setImageUrl("https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/mbp-spacegray-select-202206?wid=452&hei=420&fmt=jpeg&qlt=95&.v=1664497359481");

        Product savedData = productRepository.save(product);

        System.out.println("The Product has been saved to db with following values :\t");
        System.out.println(savedData.toString());
        System.out.println(savedData.getId());
    }

    @Test
    void updateData() {
        Product product = productRepository.findById(1L).get();
        product.setName("MacBook Pro 2023");

        //here save() method will update db since Product is existing entity in db and have primary key, so it will call EntityManager merge() method to merge/update db.
        Product updatedData = productRepository.save(product);

        System.out.println("The Product has been updated to db with following values :\t");
        System.out.println(updatedData.toString());
        System.out.println(updatedData.getId());
    }

}