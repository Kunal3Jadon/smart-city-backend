package com.smartcity.backend.controller;

import com.smartcity.backend.dto.DepartmentRequest;
import com.smartcity.backend.dto.DepartmentResponse;
import com.smartcity.backend.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public DepartmentResponse createDepartment(@Valid @RequestBody DepartmentRequest request) {

        return departmentService.createDepartment(request);
    }

    @GetMapping
    public List<DepartmentResponse> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentResponse getDepartment(@PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);
        return "Department deleted successfully";
    }
}