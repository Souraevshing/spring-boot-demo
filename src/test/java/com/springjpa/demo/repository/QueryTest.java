package com.springjpa.demo.repository;

import com.springjpa.demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

// query to fetch data.
// since we have extended JpaRepository interface,
// that's why it allows Spring Data JPA to automatically execute database queries without the need for you to write explicit SQL statements

// Here's how it works:
// 1. Extending JpaRepository<Product, Long>: When you extend JpaRepository, you're inheriting a set of pre-defined query methods and functionalities provided by Spring Data JPA.
//
// 2. Method Naming Conventions: Spring Data JPA uses method naming conventions to derive the SQL queries to execute. By following these conventions in your method names, you can specify the criteria for the query you want to perform.
//
// 3. Auto Query Generation: When you declare a method like findProductByName(String name) in your repository interface, Spring Data JPA analyzes the method name and generates an appropriate SQL query for you. In this case, it generates a SQL query that searches for a Product entity by the name field.
//
// 4. Automatic Parameter Mapping: The method parameters, such as name in findProductByName(String name), are automatically mapped to query parameters, ensuring that data passed to the method is used in the generated SQL query.
//
// 5. Result Handling: The methods return the results of the queries. For example, if you declare a method to find a single entity, it returns that entity or an Optional if it might not exist. If you declare a method to find a list of entities, it returns a list of matching entities.

// So, by extending JpaRepository and following the method naming conventions,
// you enable Spring Data JPA to automatically generate and execute the necessary SQL queries behind the scenes,
// making it easier to interact with your database using Java methods and objects.

@SpringBootTest
class QueryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findProductByName() {
        Product product = productRepository.findProductByName("Phone");
        System.out.println(product.toString());
        System.out.println(product.getName());
    }

    @Test
    public void findProductById() {
        Product product = productRepository.findProductById(3L).get();
        System.out.println(product);
        System.out.println(product.getName());
    }

    @Test
    public  void findByNameOrDescription() {
        List<Product> products = productRepository.findByNameOrDescription("Phone", "pixel 7a");
        String allProducts = products.stream()
                .map((product) ->
                        product.toString()
                )
                .collect(Collectors.joining("\n"));
        System.out.println(allProducts + "\n");
    }

    @Test
    public void findDistinctByName() {
        Product product = productRepository.findDistinctByName("Phone");
        System.out.println(product.toString());
    }

    @Test
    public void findByPriceGreaterThan() {
        List<Product> products = productRepository.findByPriceGreaterThan(BigDecimal.valueOf(5000));
        products.stream()
                .map((product) ->
                        product.toString()
                )
                .collect(Collectors.joining("\n"));
        System.out.println(products);
    }

    @Test
    public void findByPriceLessThan() {
        List<Product> products = productRepository.findByPriceLessThan(BigDecimal.valueOf(15000));
        products.stream()
                .map((product) ->
                        product.toString()
                )
                .collect(Collectors.joining("\n"));
        System.out.println(products);
    }

    @Test
    public void findByNameContaining() {
        List<Product> products = productRepository.findByNameContaining("Phone");
        products.forEach((product) -> {
            System.out.println(product.toString());
        });
    }

    @Test
    public void findByNameLike() {
        List<Product> products = productRepository.findByNameLike("Phone2");
        products.forEach((product) -> {
            System.out.println(product.getName());
        });
    }

    @Test
    public void findByPriceBetween() {
        List<Product> products = productRepository
                .findByPriceBetween(BigDecimal.valueOf(10000), BigDecimal.valueOf(20000));
        products.forEach((product) -> {
            System.out.println(product.toString());
        });
    }

    @Test
    public void findByDateBetween() {
        LocalDateTime startDateCreated = LocalDateTime.of(2023, 9, 8, 9, 27, 32);
        LocalDateTime endDateCreated = LocalDateTime.of(2023, 9, 9, 21, 26, 59);
        List<Product> products = productRepository.findByDateBetween(startDateCreated, endDateCreated);
        products.forEach((product) -> {
            System.out.println(product.toString());
        });
    }

    @Test
    public void findByNameIn() {
        List<Product> products = productRepository.findByNameIn(
                List.of("Phone2", "Phone3", "Phone4")
        );
        products.forEach((product) -> {
            System.out.println(product.toString());
        });
    }

    @Test
    public void findFirstByOrderByNameAsc() {
        List<Product> products = productRepository.findFirstByOrderByNameAsc();
        products.forEach((product) -> {
            System.out.println(product.toString());
        });
    }

}