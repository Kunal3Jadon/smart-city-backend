package com.smartcity.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class LoginRequest {
	@Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
	private String email;
	
    @NotBlank(message = "Password is required")
    private String password;
}
