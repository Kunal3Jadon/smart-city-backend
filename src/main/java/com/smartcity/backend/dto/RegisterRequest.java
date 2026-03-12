package com.smartcity.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;
@Getter
@Setter
public class RegisterRequest {
	 @NotBlank(message = "Name is required") 
	 private String name;
	 
	 @Email(message = "Invalid email format")
	 @NotBlank(message = "Email is required")
	 private String email;
	 
	 @Size(min = 6, message = "Password must be at least 6 characters")
	 private String password;
}
