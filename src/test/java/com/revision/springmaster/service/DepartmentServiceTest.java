package com.revision.springmaster.service;

import com.revision.springmaster.entity.Department;
import com.revision.springmaster.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentId(1L).departmentName("Computer Science")
                .departmentAddress("Newark, California 94560")
                .departmentCode("CSE").build();
        when(departmentRepository.findByDepartmentNameIgnoreCase("COMPUTER SCIENCE"))
                .thenReturn(department);
        when(departmentRepository.findByDepartmentNameIgnoreCase("ELECTRONICS"))
                .thenReturn(null);
    }


    @Test
    @DisplayName("When valid department name, department should be found")
    //public void whenValidDepartmentName_DepartmentShouldBeFound() {
    public void testGetDepartmentByDepartmentNameForValidDepartment() {
        String departmentName = "COMPUTER SCIENCE";
        Department department = departmentService.getDepartmentByDepartmentName(departmentName);
        assertEquals(departmentName, department.getDepartmentName().toUpperCase(Locale.ROOT));
    }

    @Test
    @DisplayName("When invalid department name, department should be null")
    //public void whenValidDepartmentName_DepartmentShouldBeFound() {
    public void testGetDepartmentByDepartmentNameForInvalidDepartment() {
        String departmentName = "ELECTRONICS";
        Department department = departmentService.getDepartmentByDepartmentName(departmentName);
        assert(department == null);
    }
}