package com.smartcity.backend.dto;

import com.smartcity.backend.entity.Priority;

import lombok.*;

@Getter
@Setter
public class UpdatePrioriyRequest {
	private Priority priority;
}
