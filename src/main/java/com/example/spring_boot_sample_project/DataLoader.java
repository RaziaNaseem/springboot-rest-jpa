package com.example.spring_boot_sample_project;

import com.example.spring_boot_sample_project.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.spring_boot_sample_project.repositories.CustomerJpaRepository;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CustomerJpaRepository CustomerJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insert sample customers
        CustomerJpaRepository.save(new Customer("Alice", "Smith", "alice.smith@example.com"));
        CustomerJpaRepository.save(new Customer("Alice", "Vergese", "alice.smith123@example.com"));
        CustomerJpaRepository.save(new Customer("Bob", "Johnson", "bob.johnson@example.org"));
        CustomerJpaRepository.save(new Customer("Charlie", "Brown", "charlie.brown@example.net"));
        CustomerJpaRepository.save(new Customer("Diana", "García", "diana.garcia@example.com"));
        CustomerJpaRepository.save(new Customer("Ethan", "gargi", "ethan@example.org"));
    }
}


