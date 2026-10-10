package com.customersupport.dto;

import com.customersupport.enums.Role;
import lombok.*;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}
