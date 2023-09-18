package com.spring.reactive.web.service;

import com.spring.reactive.web.dto.EmployeeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

    Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto);

    Mono<EmployeeDto> getEmployeeById(String id);

    Flux<EmployeeDto> getAllEmployees();

    Mono<EmployeeDto> updateEmployee(EmployeeDto employee, String id);

    Mono<Void> deleteEmployee(String id);

}
