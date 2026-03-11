package com.smartcity.backend.entity;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
    @Column(unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
    @Enumerated(EnumType.STRING)
	private Role role;
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Issue> issues;
    
	private LocalDateTime createdAt;
}
