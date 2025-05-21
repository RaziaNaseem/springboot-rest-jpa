package com.example.spring_boot_sample_project;

import com.example.spring_boot_sample_project.api.CustomerApi;
import com.example.spring_boot_sample_project.services.CustomerService;
import com.example.spring_boot_sample_project.viewmodels.CustomerViewModel;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerApi.class)
public class CustomerApiTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    public CustomerService customerService;


    @Test
    public void getAllControllerTest() throws Exception {

        when(customerService.getAll()).thenReturn(Arrays.asList(
                new CustomerViewModel(1, "Alice", "Smith", "alice.smith@example.com"),
                new CustomerViewModel(2, "Alice", "Vergese", "alice.smith123@example.com")
        ));
        RequestBuilder requestBuilder =MockMvcRequestBuilders.get("/api/v1/customers").accept(MediaType.APPLICATION_JSON);
        MvcResult result =mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "{\n" +
                        "\"customerId\": 1,\n" +
                        "\"firstName\": \"Alice\",\n" +
                        "\"lastName\": \"Smith\",\n" +
                        "\"email\": \"alice.smith@example.com\"\n" +
                        "},\n" +
                        "{\n" +
                        "\"customerId\": 2,\n" +
                        "\"firstName\": \"Alice\",\n" +
                        "\"lastName\": \"Vergese\",\n" +
                        "\"email\": \"alice.smith123@example.com\"\n" +
                        "}]"))
                .andReturn();

    }
}
