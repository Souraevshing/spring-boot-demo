package spring.restapi.search.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.restapi.search.entity.Product;
import spring.restapi.search.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/api/v1/products?query=Nothing%20phone%282%29
    // for GET request we will pass query parameter to fetch single product from db based on product name or product description.

    // THIS IS AN EXAMPLE FOR IMPLEMENTING REAL-TIME SEARCH API, SINCE USING QUERY TO SEARCH PRODUCTS WILL BE VERY FAST AS COMPARED TO
    // TRADITIONAL WAY OF SENDING GET REQUEST EVERYTIME TO FETCH DATA.

    @GetMapping
    public ResponseEntity<List<Product>> searchProduct(@RequestParam("query") String query) {
        return new ResponseEntity<List<Product>>(productService.searchProducts(query), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.createProduct(product), HttpStatus.CREATED);
    }

}
