package com.example.spring_boot_sample_project.repositories;

import com.example.spring_boot_sample_project.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer,Integer> {

    boolean existsByCustomerId(int customerID);

    @Query("Select c from Customer c where lower(c.firstName) = lower(?1)")
    List<Customer> myCustomers(String firstName);
}
