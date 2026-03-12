package com.smartcity.backend.service.impl;

import java.util.List;

import com.smartcity.backend.dto.IssueRequest;
import com.smartcity.backend.dto.IssueResponse;
import com.smartcity.backend.entity.*;
import com.smartcity.backend.repository.DepartmentRepository;
import com.smartcity.backend.repository.IssueRepository;
import com.smartcity.backend.repository.UserRepository;
import com.smartcity.backend.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {
	private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    
	@Override
	public IssueResponse createIssue(IssueRequest request, Long userId, String imageUrl) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Department department = departmentRepository.findById(request.getDepartmentId())
	            .orElseThrow(() -> new RuntimeException("Department not found"));

	    Issue issue= Issue.builder()
	            .title(request.getTitle())
	            .description(request.getDescription())
	            .latitude(request.getLatitude())
	            .longitude(request.getLongitude())
	            .status(IssueStatus.REPORTED)
	            .priority(Priority.LOW)
	            .imageUrl(imageUrl)
	            .user(user)
	            .department(department)
	            .createdAt(LocalDateTime.now())
	            .updatedAt(LocalDateTime.now())
	            .build();

	    Issue savedIssue = issueRepository.save(issue);

	    return mapToResponse(savedIssue);
	}

	@Override
	public List<IssueResponse> getAllIssues() {
		// TODO Auto-generated method stub
		 return issueRepository.findAll()
	                .stream()
	                .map(this::mapToResponse)
	                .collect(Collectors.toList());
	}

	@Override
	public IssueResponse getIssueById(Long id) {
		// TODO Auto-generated method stub
		Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        return mapToResponse(issue);
	}

	@Override
	public List<IssueResponse> getIssuesByUser(Long userId) {
		return issueRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
	}

	@Override
	public IssueResponse updateIssueStatus(Long id, IssueStatus status) {
		// TODO Auto-generated method stub
		 Issue issue = issueRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Issue not found"));

	        issue.setStatus(status);
	        issue.setUpdatedAt(LocalDateTime.now());

	        Issue updatedIssue = issueRepository.save(issue);

	        return mapToResponse(updatedIssue);
	}
	
	@Override
	public IssueResponse assignDepartment(Long issueId, Long departmentId) {

	    Issue issue = issueRepository.findById(issueId)
	            .orElseThrow(() -> new RuntimeException("Issue not found"));

	    Department department = departmentRepository.findById(departmentId)
	            .orElseThrow(() -> new RuntimeException("Department not found"));

	    issue.setDepartment(department);
	    issue.setUpdatedAt(LocalDateTime.now());

	    Issue updatedIssue = issueRepository.save(issue);

	    return mapToResponse(updatedIssue);
	}
	
	private IssueResponse mapToResponse(Issue issue) {

		return  IssueResponse.builder()
		        .id(issue.getId())
		        .title(issue.getTitle())
		        .description(issue.getDescription())
		        .status(issue.getStatus())
		        .priority(issue.getPriority())
		        .createdAt(issue.getCreatedAt())
		        .departmentName(
		                issue.getDepartment() != null ? issue.getDepartment().getName() : null
		        )
		        .imageUrl(issue.getImageUrl())
		        .build();
    }
	
	@Override
	public void updateImage(Long issueId, String fileName) {

	    Issue issue = issueRepository.findById(issueId)
	            .orElseThrow(() -> new RuntimeException("Issue not found"));

	    issue.setImageUrl(fileName);

	    issueRepository.save(issue);
	}

}
