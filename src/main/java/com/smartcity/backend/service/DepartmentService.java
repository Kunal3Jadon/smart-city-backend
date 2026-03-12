package com.smartcity.backend.service;

import com.smartcity.backend.dto.DepartmentRequest;
import com.smartcity.backend.dto.DepartmentResponse;
import java.util.*;
public interface DepartmentService {
	DepartmentResponse createDepartment(DepartmentRequest request);

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(Long id);

    void deleteDepartment(Long id);
}
