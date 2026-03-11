package com.smartcity.backend.dto;

import lombok.*;
@Getter
@Setter
public class IssueRequest {
	private String title;
    private String description;
    private Double latitude;
    private Double longitude;
}
