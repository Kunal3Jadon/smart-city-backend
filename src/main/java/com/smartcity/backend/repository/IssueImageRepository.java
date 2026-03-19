package com.smartcity.backend.repository;

import com.smartcity.backend.entity.IssueImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueImageRepository extends JpaRepository<IssueImage, Long> {
	
}