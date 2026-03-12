package com.smartcity.backend.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponse {
	 private Long id;
	    private String name;
	    private String city;
	    private String description;
	    private LocalDateTime createdAt;

}
