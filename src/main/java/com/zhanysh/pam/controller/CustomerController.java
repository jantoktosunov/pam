package com.zhanysh.pam.controller;

import com.sun.istack.NotNull;
import com.zhanysh.pam.model.Customer;
import com.zhanysh.pam.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return ResponseEntity.ok(customerService.findCustomerById(customer.getId()));
    }

    @GetMapping(value = "/json/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> getCustomerJson(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }
    @GetMapping(value = "/xml/{id}", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Customer> getCustomerXml(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }
}
