package com.smartcity.backend.dto;

import com.smartcity.backend.entity.Role;

import lombok.*;

@Getter
@Setter
@Builder
public class LoginResponse {
	private Long id;
	private String name;
	private String email;
	private Role role;
	private String token;
}
