package com.smartcity.backend.service.impl;

import com.smartcity.backend.dto.DepartmentRequest;
import com.smartcity.backend.dto.DepartmentResponse;
import com.smartcity.backend.entity.Department;
import com.smartcity.backend.repository.DepartmentRepository;
import com.smartcity.backend.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
	private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {

        Department department = Department.builder()
                .name(request.getName())
                .city(request.getCity())
                .createdAt(LocalDateTime.now())
                .build();

        Department saved = departmentRepository.save(department);

        return DepartmentResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .city(saved.getCity())
                .build();
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(dep -> DepartmentResponse.builder()
                        .id(dep.getId())
                        .name(dep.getName())
                        .city(dep.getCity())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {

        Department dep = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        return DepartmentResponse.builder()
                .id(dep.getId())
                .name(dep.getName())
                .city(dep.getCity())
                .build();
    }

    @Override
    public void deleteDepartment(Long id) {

        departmentRepository.deleteById(id);
    }

}
