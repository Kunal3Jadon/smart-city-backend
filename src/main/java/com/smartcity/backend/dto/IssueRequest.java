package com.smartcity.backend.dto;

import lombok.*;

import com.smartcity.backend.entity.Priority;

import jakarta.validation.constraints.*;

@Getter
@Setter
public class IssueRequest {
	@NotBlank(message = "Title is required")
	private String title;
	 
	@NotBlank(message = "Description is required")
    private String description;
	
    @NotNull(message = "Latitude is required")
    private Double latitude;
    
    @NotNull(message = "Longitude is required")
    private Double longitude;
    
    @NotNull(message = "Department id is required")
    private Long departmentId;
    
    @NotNull
    private Priority priority;

}
