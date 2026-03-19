package com.smartcity.backend.controller;

import com.smartcity.backend.dto.*;
import com.smartcity.backend.service.FileStorageService;
import com.smartcity.backend.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {
	 private final IssueService issueService;
	 private final FileStorageService fileStorageService;
	 
		 @PreAuthorize("hasRole('CITIZEN')")
		 @PostMapping(consumes = "multipart/form-data")
		 public IssueResponse createIssue(
		         @ModelAttribute IssueRequest request,
		         @RequestParam Long userId,
		         @RequestParam("files") List<MultipartFile> files) {
	
			 List<String> fileNames = files.stream()
			            .map(fileStorageService::saveFile)
			            .toList();

			    return issueService.createIssue(request, userId, fileNames);
		 }
	 	
	 	@PreAuthorize("hasAnyRole('CITIZEN','ADMIN')")
	    @GetMapping
	    public List<IssueResponse> getAllIssues() {
	        return issueService.getAllIssues();
	    }

	    @GetMapping("/{id}")
	    public IssueResponse getIssueById(@PathVariable Long id) {
	        return issueService.getIssueById(id);
	    }

	    @GetMapping("/user/{userId}")
	    public List<IssueResponse> getIssuesByUser(@PathVariable Long userId) {
	        return issueService.getIssuesByUser(userId);
	    }
	    
	    @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping("/{id}/status")
	    public IssueResponse updateStatus(@PathVariable Long id,@RequestBody UpdateStatusRequest request) {
	        return issueService.updateIssueStatus(id, request.getStatus());
	    }
	    
	    @PutMapping("/{id}/priority")
	    public IssueResponse updatePrioriy(@PathVariable Long id,@RequestBody UpdatePrioriyRequest request) {
	        return issueService.updateIssuePriority(id, request.getPriority());
	    }
	    
	    @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping("/{issueId}/assign-department")
	    public IssueResponse assignDepartment(@PathVariable Long issueId,@RequestBody AssignDepartmentRequest request) {

	        return issueService.assignDepartment(issueId, request.getDepartmentId());
	    }
	   
}
