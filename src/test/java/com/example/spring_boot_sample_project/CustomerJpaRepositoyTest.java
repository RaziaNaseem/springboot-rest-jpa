package com.example.spring_boot_sample_project;

import com.example.spring_boot_sample_project.models.Customer;
import com.example.spring_boot_sample_project.repositories.CustomerJpaRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@Sql( "/data.sql")
public class CustomerJpaRepositoyTest {

    @Autowired
    CustomerJpaRepository customerJpaRepository;

    @Test
    public void testFindAll()
    {
        List<Customer> customers =  customerJpaRepository.findAll();
        assertEquals(6,customers.size());
    }

}

