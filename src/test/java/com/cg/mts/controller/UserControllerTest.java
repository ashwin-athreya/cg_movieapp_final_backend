package com.cg.mts.controller;

import com.cg.mts.repository.CustomerRepository;
import com.cg.mts.service.CustomerService;
import com.cg.mts.service.IUserService;
import com.cg.mts.service.ScreenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.cg.mts.dto.UserDTO;
import com.cg.mts.entity.Customer;
import com.cg.mts.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Mock
    private IUserService userService;
    
    @Mock
    private ScreenService screenService;
    
    @Mock
    private CustomerService customerService;

    @InjectMocks
    UserController userController;

    @Mock
    CustomerRepository customerRepository;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void addUserTest() throws Exception {
        // Mock the behavior of userService.addUser
        when(userService.addUser(any(User.class))).thenReturn(new User());

        // Mock the behavior of customerService.addCustomer
        when(customerService.addCustomer(any(Customer.class))).thenReturn(new Customer());

        // Perform the HTTP POST request
        mvc.perform(post("/user/adduser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getUserDTO())))
                .andExpect(status().isOk());

        // Verify that userService.addUser was called with the correct arguments
        verify(userService, times(1)).addUser(any(User.class));

        // Verify that customerService.addCustomer was called when the role is "CUSTOMER"
        verify(customerService, times(1)).addCustomer(any(Customer.class));
    }
    


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    UserDTO getUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("john.doe");
        userDTO.setPassword("password123");
        userDTO.setRole("CUSTOMER");
        return userDTO;
    }
}