package com.smartcity.backend.controller;

import com.smartcity.backend.dto.*;
import com.smartcity.backend.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {
	 private final IssueService issueService;
	 
	 	@PreAuthorize("hasRole('CITIZEN')")
	    @PostMapping
	    public IssueResponse createIssue(@RequestBody IssueRequest request,@RequestParam Long userId) {
	        return issueService.createIssue(request, userId);
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
}
