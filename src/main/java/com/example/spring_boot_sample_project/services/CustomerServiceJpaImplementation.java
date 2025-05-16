package com.example.spring_boot_sample_project.services;

import com.example.spring_boot_sample_project.exceptions.RecordNotFoundException;
import com.example.spring_boot_sample_project.models.Customer;
import com.example.spring_boot_sample_project.repositories.CustomerJpaRepository;
import com.example.spring_boot_sample_project.viewmodels.CustomerCreateViewModel;
import com.example.spring_boot_sample_project.viewmodels.CustomerUpdateViewModel;
import com.example.spring_boot_sample_project.viewmodels.CustomerViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceJpaImplementation implements CustomerService{
    private final CustomerJpaRepository customerJpaRepository;

    public CustomerServiceJpaImplementation(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public List<CustomerViewModel> getAll() {
        return customerJpaRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerViewModel viewModel = new CustomerViewModel();
                    BeanUtils.copyProperties(customer, viewModel);
                    return viewModel;
                })
                .toList();
        // .collect(Collectors.toList());

    }

    @Override
    public List<CustomerViewModel> getCustomerByName(String firstName) {
        List<Customer> customers = customerJpaRepository.myCustomers(firstName);
        if(customers.isEmpty())
        {
            throw new RecordNotFoundException("No customers found with name: " + firstName);
        }
        return customers
                .stream()
                .map(customer -> {
                    CustomerViewModel viewModel = new CustomerViewModel();
                    BeanUtils.copyProperties(customer, viewModel);
                    return viewModel;
                })
                .toList();


    }

    @Override
    public CustomerViewModel getById(int customerId) {
        Customer customer = customerJpaRepository.findById(customerId).orElseThrow(()-> new RecordNotFoundException("Customer not found : "+customerId));
        return utilityMethod(customer,new CustomerViewModel());

    }

    @Override
    public CustomerViewModel create(CustomerCreateViewModel viewModel) {
        Customer customerD = new Customer();
        BeanUtils.copyProperties(viewModel, customerD);
        Customer customerInserted = customerJpaRepository.save(customerD);
        return utilityMethod(customerInserted,new CustomerViewModel());

    }

    @Override
    public CustomerViewModel update(int customerId, CustomerUpdateViewModel updateViewModel) {
        Customer customer = customerJpaRepository.findById(customerId).orElseThrow(()-> new RecordNotFoundException("Customer not found : "+customerId));
        BeanUtils.copyProperties(updateViewModel, customer);

        customerJpaRepository.saveAndFlush(customer);
        return utilityMethod(customer,new CustomerViewModel());

    }

    @Override
    public void deleteById(int customerId) {

        if(customerJpaRepository.existsByCustomerId(customerId))
            customerJpaRepository.deleteById(customerId);
        else
            throw new RecordNotFoundException("Customer not found : "+customerId);
    }

    public CustomerViewModel utilityMethod(Customer customer, CustomerViewModel viewModel)
    {
        BeanUtils.copyProperties(customer, viewModel);
        return viewModel;
    }

}

