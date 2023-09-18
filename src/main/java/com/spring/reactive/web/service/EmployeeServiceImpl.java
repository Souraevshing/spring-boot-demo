package com.spring.reactive.web.service;

import com.spring.reactive.web.dto.EmployeeDto;
import com.spring.reactive.web.entity.Employee;
import com.spring.reactive.web.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import com.spring.reactive.web.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    // Mono is used for sending single request at once, so we are using Mono for saveEmployee and getEmployeeById.
    // Flux is used for sending multiple request at once, so to get all employees we use Flux.

    private EmployeeRepository employeeRepository;

    @Override
    public Mono<EmployeeDto> saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Mono<Employee> saveEmployee = employeeRepository.save(employee);
        return  saveEmployee
                .map((emp) -> {
                    return EmployeeMapper.mapToEmployeeDto(emp);
                });
    }

    @Override
    public Mono<EmployeeDto> getEmployeeById(String id) {
        Mono<Employee> getEmployee = employeeRepository.findById(id);
        return  getEmployee
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee));
    }

    // if no employees are found in collection, then we return empty flux.

    public Flux<EmployeeDto> getAllEmployees() {
        Flux<Employee> employees = employeeRepository.findAll();
        return employees
                .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<EmployeeDto> updateEmployee(EmployeeDto employee, String id) {
        Mono<Employee> allEmployees = employeeRepository.findById(id);
        Mono<Employee> updatedEmployee = allEmployees
                .flatMap((e) -> {
                    e.setFirstName(employee.getFirstName());
                    e.setLastName(employee.getLastName());
                    e.setEmail(employee.getEmail());
                    return employeeRepository.save(e);
                });
        return updatedEmployee
                .map((e) -> EmployeeMapper.mapToEmployeeDto(e));
    }

    @Override
    public Mono<Void> deleteEmployee(String id) {
        return employeeRepository.deleteById(id);
    }

}
