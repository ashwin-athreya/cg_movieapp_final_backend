package com.cg.mts.dto;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userId;
    

    @Size(min = 3, max = 9, message = "Username must be between 3 and 9 characters")
    private String username;

    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    private String password;

    @Pattern(regexp = "^(ADMIN|CUSTOMER)$", message = "Role must be ADMIN or CUSTOMER")
    private String role;
    
    private CustomerDTO customer;
    
	
}