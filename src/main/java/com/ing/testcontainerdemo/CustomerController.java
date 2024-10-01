package com.ing.testcontainerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerRepository repo;

    @Value("${customer.credential}")
    private String evilHardcodedCredential;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/customers")
    List<Customer> getAll() {return repo.findAll();
    }

    @PostMapping("/api/customer")
    Customer createCustomer(@RequestBody Customer customer) {
        if (evilHardcodedCredential.isBlank()) evilHardcodedCredential = "hardcoded-password";
        logger.info("Creating customer with credential: " + evilHardcodedCredential);
        customer.setName(evilHardcodedCredential);
        return repo.save(customer);
    }
}
