package com.zhanysh.pam.controller;

import com.sun.istack.NotNull;
import com.zhanysh.pam.model.Customer;
import com.zhanysh.pam.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * Rest Controller for <code>Customer</code>.
 */
@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Adds new Customer to database.
     * @param customer - that will be added.
     * @param contentType - type of header.
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody Customer customer,
                                               @RequestHeader("Content-Type") String contentType) {
        HttpHeaders headers = new HttpHeaders();
        String format = "";
        headers.set("Content-Type", "application/json");
        if("application/xml".equals(contentType)) {
            headers.set("Content-Type", "application/xml");
            format = "xml";
        } else {
           headers.set("Content-Type", "application/json");
           format = "json";
        }
        customerService.createCustomer(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").query("format=" + format)
                .buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location)
                .headers(headers)
                //.body(location.getPath() + "?" +location.getQuery());
                .body(customer);
    }

    /**
     * Get existing customer by id.
     * @param id - of requested customer.
     * @param format - of response. Should be either xml or json.
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable Long id, @RequestParam String format) {
        HttpHeaders headers = new HttpHeaders();
        if(format.equals("xml")) {
            headers.set("Content-Type", "application/xml");
        } else if(format.equals("json")) {
            headers.set("Content-Type", "application/json");
        } else {
            headers.set("Content-Type", "application/json");
        }
        return ResponseEntity.ok()
                .headers(headers)
                .body(customerService.findCustomerById(id));
    }
}
