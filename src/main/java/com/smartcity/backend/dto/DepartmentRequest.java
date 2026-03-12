package com.smartcity.backend.dto;
import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
	@NotBlank(message = "Department name is required")
	  private String name;
	
	@NotBlank(message = "City is required")
	 private String city;
	 
	private String description;

}
