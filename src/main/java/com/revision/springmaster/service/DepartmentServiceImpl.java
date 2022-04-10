package com.revision.springmaster.service;

import com.revision.springmaster.entity.Department;
import com.revision.springmaster.exception.DepartmentNotFoundException;
import com.revision.springmaster.repository.DepartmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    @SneakyThrows
    public Department getDepartmentById(Long departmentId)  {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        return departmentOptional.orElseThrow(() ->
                new DepartmentNotFoundException("No department exists with department id : " + departmentId));
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        getDepartmentById(departmentId);
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department)  {
        Department dbDepartment = getDepartmentById(departmentId);

        if (Objects.nonNull(dbDepartment.getDepartmentName()) && !"".equals(dbDepartment.getDepartmentName()))
            dbDepartment.setDepartmentName(department.getDepartmentName());

        if (Objects.nonNull(dbDepartment.getDepartmentAddress()) && !"".equals(dbDepartment.getDepartmentAddress()))
            dbDepartment.setDepartmentAddress(department.getDepartmentAddress());

        if (Objects.nonNull(dbDepartment.getDepartmentCode()) && !"".equals(dbDepartment.getDepartmentCode()))
            dbDepartment.setDepartmentCode(department.getDepartmentCode());

        return departmentRepository.save(dbDepartment);


    }
    @Override
    public Department getDepartmentByDepartmentName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
