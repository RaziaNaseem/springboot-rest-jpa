package com.example.spring_boot_sample_project.services;

import com.example.spring_boot_sample_project.models.Customer;
import com.example.spring_boot_sample_project.viewmodels.CustomerCreateViewModel;
import com.example.spring_boot_sample_project.viewmodels.CustomerUpdateViewModel;
import com.example.spring_boot_sample_project.viewmodels.CustomerViewModel;

import java.util.List;

public interface CustomerService {
    List<CustomerViewModel> getAll();
    List<CustomerViewModel> getCustomerByName(String firstName);
    CustomerViewModel getById(int customerId);
    CustomerViewModel create(CustomerCreateViewModel customer);
    CustomerViewModel update(int customerId, CustomerUpdateViewModel customer);
    void deleteById(int customerId);
}
