package com.smartcity.backend.dto;

import com.smartcity.backend.entity.IssueStatus;

import lombok.*;
@Getter
@Setter
public class UpdateStatusRequest {
    private IssueStatus status;

}
