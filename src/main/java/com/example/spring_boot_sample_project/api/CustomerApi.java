package com.example.spring_boot_sample_project.api;


import com.example.spring_boot_sample_project.exceptions.RecordNotFoundException;
import com.example.spring_boot_sample_project.services.CustomerService;
import com.example.spring_boot_sample_project.viewmodels.CustomerCreateViewModel;
import com.example.spring_boot_sample_project.viewmodels.CustomerUpdateViewModel;
import com.example.spring_boot_sample_project.viewmodels.CustomerViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerApi {
    private final CustomerService customerService;

    public CustomerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerViewModel>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("id/{customerid}")
    public ResponseEntity<CustomerViewModel> getById(@PathVariable int customerid) {
        return ResponseEntity.ok(customerService.getById(customerid));
    }

    @GetMapping("name/{firstName}")
    public ResponseEntity<List<CustomerViewModel>> getByName(@PathVariable String firstName) {
        return ResponseEntity.ok(customerService.getCustomerByName(firstName));
    }

    @PostMapping
    public ResponseEntity<CustomerViewModel> create(@RequestBody CustomerCreateViewModel viewModel) {
        return ResponseEntity.ok(customerService.create(viewModel));
    }

    @PutMapping("{customerid}")
    public ResponseEntity<CustomerViewModel> update(@PathVariable int customerid, @RequestBody CustomerUpdateViewModel updateModel) {
        return ResponseEntity.ok(customerService.update(customerid, updateModel));
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<?> deleteById(@PathVariable int customerId) {
        customerService.deleteById(customerId);
        return ResponseEntity.noContent().build();
        // return ResponseEntity.status(204).build();
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRecordNotFoundException(RecordNotFoundException exception) {
        return ResponseEntity.status(404).body(Map.of("error", exception.getMessage()));
    }

}