package com.springjpa.springjpademo.repository;

import com.springjpa.springjpademo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveData() {
        Product product = new Product();
        product.setName("MacBook Pro");
        product.setDescription("Apple Product");
        product.setSku("900");
        product.setPrice(new BigDecimal(129900));
        product.setActive(true);
        product.setImageUrl("https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/mbp-spacegray-select-202206?wid=452&hei=420&fmt=jpeg&qlt=95&.v=1664497359481");

        Product savedData = productRepository.save(product);

        System.out.println("The Product has been saved to db with following values :\t");
        System.out.println(savedData.toString());
        System.out.println();
        System.out.println(savedData.getId());
    }

    @Test
    void saveAllData() {
        Product product1 = new Product();
        product1.setName("MacBook Pro 2022");
        product1.setDescription("Apple Product");
        product1.setSku("2300");
        product1.setPrice(new BigDecimal(129900));
        product1.setActive(true);
        product1.setImageUrl("https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/mbp-spacegray-select-202206?wid=452&hei=420&fmt=jpeg&qlt=95&.v=1664497359481");

        Product product2 = new Product();
        product2.setName("MacBook Pro 2023");
        product2.setDescription("Apple Product");
        product2.setSku("2000");
        product2.setPrice(new BigDecimal(129900));
        product2.setActive(true);
        product2.setImageUrl("https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/mbp-spacegray-select-202206?wid=452&hei=420&fmt=jpeg&qlt=95&.v=1664497359481");


        productRepository.saveAll(List.of(product1,product2));

        System.out.println("The Product has been saved to db with following values :\t");
        System.out.println(productRepository.toString());
        System.out.println();
    }

    @Test
    void updateData() {
        Product product = productRepository.findById(1L).orElseThrow();
        product.setName("MacBook Pro 2023");

        //here save() method will update db since Product is existing entity in db and have primary key, so it will call EntityManager merge() method to merge/update db.
        Product updatedData = productRepository.save(product);

        System.out.println("The Product has been updated to db with following values :\t");
        System.out.println(updatedData.toString());
        System.out.println();
        System.out.println(updatedData.getId());
    }

    @Test
    void findByIdData() {
        Long id = 1L;
        Product productById = productRepository.findById(id).orElseThrow();

        System.out.println("The Product fetched from db with id "+id+" is:\n");
        System.out.println(productById.toString());
        System.out.println();
    }

    @Test
    void findAllData() {
        List <Product> products = productRepository.findAll();
        products.forEach((data) -> {
            System.out.println(data.toString());
            System.out.println();
        });
    }

    @Test
    void deleteByIdData() {
        Long id = 7L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteData() {
        Long id = 6L;
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);      //deletes only one row after fetched by id
    }

    @Test
    void deleteAllData() {
        //productRepository.deleteAll();    //deletes all data inside database instantly
        Product p1 = productRepository.findById(12L).orElseThrow();
        Product p2 = productRepository.findById(13L).orElseThrow();
        productRepository.deleteAll(List.of(p1,p2));    //first we select the row by id(here, primary key) then pass List of rows fetched as argument to delete fetched rows.
    }

    @Test
    void countRows() {
        long count = productRepository.count();
        System.out.println(count);
    }
}