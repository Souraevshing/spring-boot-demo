package com.springjpa.demo.repository;

import com.springjpa.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // returns null if product is not found.
    Product findProductByName(String name);

    // get product by id.
    Optional<Product> findProductById(Long id);

    // get products by name or description.
    List<Product> findByNameOrDescription(String name, String description);

    // fetch unique data from db.
    Product findDistinctByName(String name);

    // fetch products greater than.
    List<Product> findByPriceGreaterThan(BigDecimal price);

    // fetch products lesser than.
    List<Product> findByPriceLessThan(BigDecimal price);

    // filter product containing name.
    List<Product> findByNameContaining(String name);

    // filter product having similar name by triggering like query.
    List<Product> findByNameLike(String name);

    // filter products between price range.
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // filter products between date range.
    List<Product> findByDateBetween(LocalDateTime startDateCreated, LocalDateTime endDateCreated);

    // fetch products having multiple values.
    List<Product> findByNameIn(List<String> name);

    // sort name in ascending order.
    List<Product> findFirstByOrderByNameAsc();

}
