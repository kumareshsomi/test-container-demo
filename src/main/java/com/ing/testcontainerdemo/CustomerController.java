package com.ing.testcontainerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerRepository repo;
    private final String evilHardcodedCredential = "npa-password";

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/customers")
    List<Customer> getAll() {return repo.findAll();
    }

    @PostMapping("/api/customer")
    Customer createCustomer(@RequestBody Customer customer) {
        logger.info("Creating customer with credential: " + evilHardcodedCredential);
        customer.setName(evilHardcodedCredential);
        return repo.save(customer);
    }
}
