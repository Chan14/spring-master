package com.revision.springmaster.controller;

import com.revision.springmaster.entity.Department;
import com.revision.springmaster.exception.DepartmentNotFoundException;
import com.revision.springmaster.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("saveDepartment entry with department {}", department);
        return departmentService.saveDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        LOGGER.info("getAllDepartments entry");
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{nameOrId}")
    public Department getDepartment(@PathVariable("nameOrId") String nameOrId) {
        LOGGER.info("getDepartment entry with {}", nameOrId);
        char firstChar = nameOrId.charAt(0);
        if (firstChar >= '0' && firstChar <= '9')
            return departmentService.getDepartmentById(Long.parseLong(nameOrId));
        return departmentService.getDepartmentByDepartmentName(nameOrId);
    }

    @PutMapping("/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long departmentId,
                                     @RequestBody Department department) throws DepartmentNotFoundException {
        LOGGER.info("updateDepartment entry with id {}. Request Body Department {}.", departmentId, department);
        return departmentService.updateDepartmentById(departmentId, department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable("id") Long departmentId) {
        LOGGER.info("deleteDepartment entry with id {}", departmentId);
        departmentService.deleteDepartmentById(departmentId);
    }

}
