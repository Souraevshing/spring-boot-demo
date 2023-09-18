package com.spring.reactive.web.repository;

import com.spring.reactive.web.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, String> {

}
