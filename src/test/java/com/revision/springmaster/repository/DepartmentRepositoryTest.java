package com.revision.springmaster.repository;

import com.revision.springmaster.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Long departmentId;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Mechanical Engineering")
                .departmentCode("ME").departmentAddress("Palo Alto, California").build();
        department = entityManager.persist(department);
        departmentId = department.getDepartmentId();
    }

    @Test
    @DisplayName("When valid department id, return matching department")
    public void testFindById() {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        assert(optionalDepartment.isPresent());
        Department department = optionalDepartment.get();
        assertEquals(department.getDepartmentName(), "Mechanical Engineering");
    }
}