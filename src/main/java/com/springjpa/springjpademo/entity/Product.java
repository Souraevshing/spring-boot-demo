package com.springjpa.springjpademo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//UniqueConstraint is used to specify any column to be always unique
//name = unique name
//columnNames = which column name to be changed

@Entity
@Table( name = "products",
        schema = "ecommerce",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "sku_unique",
                    columnNames = "stock_keeping_unit"
            )
        }
)
public class Product {

//GeneratedValue is used to specify primary key generation type
//here we have used IDENTITY that relies on db auto-increment column

//    Hibernate provide 4 types of GenerationType :
//    1. GenerationType.AUTO : this is the default generation type and let the persistence provider choose the generation strategy
//    2. Generation.IDENTITY : relies on auto-incremented db column and lets db generate a new value with each insert operation
//    3. Generation.SEQUENCE : used to generate primary key values and used db sequence to generate unique values, most commonly used.
//                             Extra select statement is used and SequenceGenerator annotation is used to provide name, sequence name, allocationSize.
//                             By default, allocationSize is 50. Whenever any rows are added, hibernate will search and select query from the sequence name.
//    4. Generation.TABLE : used rarely, generates sequence by storing & updating its current value in db. It requires the use of pessimistic locks which
//                             put all transactions into order which makes it very slow, so we don't use this nowadays.

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )

    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequence_name",
            allocationSize = 1
    )
    private long id;
    @Column(name = "stock_keeping_unit", nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    private String imageUrl;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public Product() {

    }
    public Product(long id, String sku, String name, String description, BigDecimal price, boolean active, String imageUrl, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.imageUrl = imageUrl;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
