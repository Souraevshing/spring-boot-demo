package com.spring.reactive.web.controller;

import com.spring.reactive.web.dto.EmployeeDto;
import com.spring.reactive.web.repository.EmployeeRepository;
import com.spring.reactive.web.service.EmployeeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WebTestClient webTestClient;

    // BeforeAll will at the start when running any test case.
    @BeforeAll
    public static void beforeAll() {
        System.out.println("Testing Spring rest api using junit jupiter for reactive web app.\n".toUpperCase());
    }

    // BeforeEach will run before every test.
    @BeforeEach
    public void before() {
        System.out.println("Deleting all employees before running any test case.\n");
        employeeRepository.deleteAll().subscribe();
    }

    // AfterAll will at the end when running any test case.
    @AfterAll
    public static void afterAll() {
        System.out.println("Bye Bye.\n".toUpperCase());
    }

    // AfterEach will run after every test.
    @AfterEach
    public void after() {
        System.out.println("Done running test cases.\n");
    }

    @Test
    public void saveEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("test");
        employeeDto.setLastName("user");
        employeeDto.setEmail("testuser@yopmail.com");

/**      POST /api/v1/employees HTTP/1.1
        Host: [localhost:RANDOM_PORT]
        Content-Type: application/json
        Accept: application/json
        Content-Length: [101]
        [Request Body in JSON Format]
 */
        // jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
        // getting firstName and matching it with firstName from response.

        // consumeWith(System.out::println)
        // the consumer for response body, prints on console or empty [] as returned.

        // exchange()
        // Perform the exchange without a request body and returns decoded response.

        // expectStatus()
        // expect HTTP status.

        // isCreated()
        // expects status code is HttpStatus.CREATED (201).


        webTestClient
                .post()
                .uri("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(employeeDto), EmployeeDto.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
                .jsonPath("$.email").isEqualTo(employeeDto.getEmail());
    }

    @Test
    public void getEmployeeById() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("s");
        employeeDto.setLastName("kumar");
        employeeDto.setEmail("skumar@yopmail.com");
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();
        /**
         GET /api/v1/employees/${id} HTTP/1.1
         Host: [localhost:RANDOM_PORT]
         Content-Type: application/json
         Accept: application/json
         Content-Length: [97]
         [Request Body in JSON Format]
         */

        webTestClient
                .get()
                .uri("/api/v1/employees/{id}", Collections.singletonMap("id", savedEmployee.getId()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.id").isEqualTo(savedEmployee.getId())
                .jsonPath("$.firstName").isEqualTo(employeeDto.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employeeDto.getLastName())
                .jsonPath("$.email").isEqualTo(employeeDto.getEmail());
    }

    @Test
    public void getAllEmployees() {
        webTestClient
                .get()
                .uri("/api/v1/employees")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(EmployeeDto.class)
                .consumeWith(System.out::println);
    }

    @Test
    public void updateEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("admin");
        employeeDto.setLastName("user1");
        employeeDto.setEmail("adminuser1@yopmail.com");
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();
        EmployeeDto updatedEmployee = new EmployeeDto();
        updatedEmployee.setFirstName("admin");
        updatedEmployee.setLastName("user2");
        updatedEmployee.setEmail("adminuser2@yopmail.com");

        webTestClient
                .put()
                .uri("/api/v1/employees/{id}", Collections.singletonMap("id", savedEmployee.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updatedEmployee), EmployeeDto.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(updatedEmployee.getFirstName())
                .jsonPath("$.lastName").isEqualTo(updatedEmployee.getLastName())
                .jsonPath("$.email").isEqualTo(updatedEmployee.getEmail());
    }

    @Test
    public void deleteEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("admin");
        employeeDto.setLastName("user1");
        employeeDto.setEmail("adminuser1@yopmail.com");
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto).block();
        webTestClient
                .delete()
                .uri("/api/v1/employees/{id}", Collections.singletonMap("id", savedEmployee.getId()))
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody()
                .consumeWith(System.out::println);
    }

}