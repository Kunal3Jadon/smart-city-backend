package com.smartcity.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import com.smartcity.backend.entity.IssueStatus;
import com.smartcity.backend.entity.Priority;

@Getter
@Setter
@Builder
public class IssueResponse {
	 private Long id;
	 private String title;
	 private String description;
	 private IssueStatus status; 
	 private Priority priority;
	 private LocalDateTime createdAt;
	 private String departmentName;
	 private List<String> images;
}
