package com.codecool.springcrudrestapi.controller;

import com.codecool.springcrudrestapi.exception.ResourceNotFoundException;
import com.codecool.springcrudrestapi.model.Employee;
import com.codecool.springcrudrestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository er; //injektáljuk a JPA repository-t

    //Összes employee kilistázása
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return er.findAll();
    }

    //employee kreálás
    @PostMapping("/employees")
    public Employee createEmployee(@Validated @RequestBody Employee employee){
        return er.save(employee);
    }

    //employee keresése ID alapján
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Employee employee = er.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + id));
        return ResponseEntity.ok().body(employee);
    }

    //employee felülírása
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long id,
                                   @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
        Employee employee = er.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEamilId(employeeDetails.getEamilId());
        er.save(employee);
        return ResponseEntity.ok().body(employee);
    }

    //employee törlése
    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        er.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        er.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
