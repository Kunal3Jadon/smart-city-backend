package com.smartcity.backend.service;

import java.util.List;

import com.smartcity.backend.dto.IssueRequest;
import com.smartcity.backend.dto.IssueResponse;
import com.smartcity.backend.entity.IssueStatus;

public interface IssueService {
	IssueResponse createIssue(IssueRequest request, Long userId);

    List<IssueResponse> getAllIssues();

    IssueResponse getIssueById(Long id);

    List<IssueResponse> getIssuesByUser(Long userId);

    IssueResponse updateIssueStatus(Long id, IssueStatus status);
}
