package spring.restapi.search.service;

import spring.restapi.search.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> searchProducts(String query);
    Product createProduct(Product product);

}
