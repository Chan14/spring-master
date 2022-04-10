package com.revision.springmaster.service;

import com.revision.springmaster.entity.Department;
import lombok.SneakyThrows;

import java.util.List;


public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    @SneakyThrows
    Department getDepartmentById(Long departmentId);

    void deleteDepartmentById(Long departmentId);

    Department updateDepartmentById(Long departmentId, Department department);

    Department getDepartmentByDepartmentName(String departmentName);
}
