package com.eprocurement;

import java.util.ArrayList;
import java.util.List;

import com.eprocurement.department.Department;
import com.eprocurement.department.DepartmentRepository;
import com.eprocurement.department.DepartmentRestController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * package com.eprocurement;DepartmentRestMvcTest
 */
@WebMvcTest(DepartmentRestController.class)
@RunWith(SpringRunner.class)
@WithMockUser(roles="ADMIN")
@EnableSpringDataWebSupport
public class DepartmentRestMvcTest {

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetDepartment() throws Exception{
        
        Department department = new Department();
        department.setDepartmentName("Test Department");
        department.setDepartmentHeadPosition("Position");
        department.setDepartmentHead("Department head");

        Pageable pageable = PageRequest.of(0,20);
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        Page<Department> departmentpage = new PageImpl<Department>(departments, pageable, 20);

        given(departmentRepository.findAll(any(Pageable.class))).willReturn(departmentpage);
        this.mvc.perform(get("/api/departments").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content", hasSize(1)))
            .andExpect(jsonPath("$.content[0].departmentName", is(department.getDepartmentName())));

    }
    
}