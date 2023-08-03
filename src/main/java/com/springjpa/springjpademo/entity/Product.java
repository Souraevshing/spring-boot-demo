package com.springjpa.springjpademo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//      Lombok library is used to reduce boilerplate code.
//      Getter is used to generate getter methods
//      Setter is used to generate setter methods
//      NoArgsConstructor for default constructor
//      AllArgsConstructor for parametrized constructor

//      UniqueConstraint is used to specify any column to be always unique
//      name = unique name
//      columnNames = which column name to be changed

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    //CreationTimestamp is used to keep track of time at which table is created
    //UpdateTimestamp is used to keep track of time at which table is updated

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;
}
