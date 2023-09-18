package spring.restapi.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.restapi.search.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // for jpa query, using table name as Product in the query to select data from Product entity and not products since we are using
    // JPQL to fetch data, therefore we are setting table name as Product entity and not table name products.

    // If we had to use native sql query, then we will set table name as products to fetch data from db.

    // JPQL query (Java Persistence Query Language)
    // fetching data from db using query
    @Query("SELECT p FROM Product p WHERE "
            +
            "p.name LIKE CONCAT('%', :query, '%')"
            +
            "Or p.description LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);

    // Native SQL query
    // fetching using native sql query
    @Query(value = "SELECT * FROM products p WHERE "
            +
            "p.name LIKE CONCAT('%', :query, '%')"
            +
            "Or p.description LIKE CONCAT('%', :query, '%')", nativeQuery = true
    )
    List<Product>searchProductsUsingSQL(String query);

}
