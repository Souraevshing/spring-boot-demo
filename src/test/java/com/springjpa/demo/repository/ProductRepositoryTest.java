package com.springjpa.demo.repository;

import com.springjpa.demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveProduct() {
        Product newProduct = new Product();
        newProduct.setName("Phone4");
        newProduct.setSku("5");
        newProduct.setDescription("pixel 6a");
        newProduct.setPrice(BigDecimal.valueOf(4000));
        newProduct.setActive(true);
        newProduct.setImageUrl("nothing_phone.svg");

        productRepository.save(newProduct);
    }

    @Test
    public void updateProduct() {
        Long id = 3L;
        Product updatedProduct = productRepository.findById(id).get();
        updatedProduct.setName("AC");
        updatedProduct.setPrice(BigDecimal.valueOf(23000));

        productRepository.save(updatedProduct);
    }

    @Test
    public  void findProductById() {
        Long id = 3L;
        Product findProduct = productRepository.findById(id).get();
        Product product = new Product();
        System.out.println(findProduct.getId());
    }

    @Test
    public void getAllProducts() {
        List<Product> getAllProducts = productRepository.findAll();

        String allProducts = getAllProducts.stream()
                .map((product) ->
                    product.toString()
                )
                .collect(Collectors.joining("\n"));
        System.out.println(allProducts + "\n");
    }

    @Test
    public void deleteProductById() {
        Long id = 1L;
        productRepository.deleteById(id);
        getAllProducts();
    }

    @Test
    public void countProducts() {
        System.out.println(productRepository.count() + "\n");
    }

    @Test
    public void isProductExist() {
        if (productRepository.existsById(2L)) {
            System.out.println("Product with id exist. \n");
        } else {
            System.out.println("Product with id not exist. \n");
        }
    }

}