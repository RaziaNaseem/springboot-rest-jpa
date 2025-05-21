package com.example.spring_boot_sample_project;

import com.example.spring_boot_sample_project.models.Customer;
import com.example.spring_boot_sample_project.repositories.CustomerJpaRepository;
import com.example.spring_boot_sample_project.services.CustomerService;
import com.example.spring_boot_sample_project.services.CustomerServiceJpaImplementation;
import com.example.spring_boot_sample_project.viewmodels.CustomerViewModel;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class git add CustomerServiceTest {

   @InjectMocks
   CustomerServiceJpaImplementation customerServiceJpaImplementation;
    @Mock
    CustomerJpaRepository customerJpaRepository;

    @Test
    public void getAllTest(){
        when(customerJpaRepository.findAll()).thenReturn(List.of(new Customer(1,"Alice","Berv","Emaildfsd@gmail.com"),
                new Customer(2,"Jad","Berddv","Emaidsldfsd@gmail.com")));

        List<CustomerViewModel> expectedList = customerServiceJpaImplementation.getAll();
        for(CustomerViewModel model :expectedList)
        System.out.println("model ="+model.getCustomerId());

        List<CustomerViewModel> actualList = List.of(new CustomerViewModel(1,"Alice","Berv","Emaildfsd@gmail.com"),
              new CustomerViewModel(2,"Jad","Berddv","Emaidsldfsd@gmail.com"));

        assertTrue("Comparison failed",expectedList.equals(actualList));


    }
}
