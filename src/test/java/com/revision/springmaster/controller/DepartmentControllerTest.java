package com.revision.springmaster.controller;

import com.revision.springmaster.entity.Department;
import com.revision.springmaster.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setup() {
        department = Department.builder().departmentName("Aeronautical Engineering")
                .departmentAddress("Los Gatos, California").departmentCode("AE").departmentId(1L).build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder().departmentName("Aeronautical Engineering")
                .departmentAddress("Los Gatos, California").departmentCode("AE").build();
        when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        mockMvc.perform(
                post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentName\" : \"Aeronautical Engineering\", " +
                                        "\"departmentAddress\" : \"Los Gatos, California\", " +
                                "\"departmentCode\" : \"AE\"}"))
                .andExpect(status().isCreated());
    }


    @Test
    void getDepartment() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName())
        );

    }
}