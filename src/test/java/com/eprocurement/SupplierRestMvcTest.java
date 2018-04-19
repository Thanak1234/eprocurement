package com.eprocurement;

import java.util.ArrayList;
import java.util.List;

import com.eprocurement.supplier.Supplier;
import com.eprocurement.supplier.SupplierRepository;

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

@WebMvcTest
@RunWith(SpringRunner.class)
@WithMockUser(roles="ADMIN")
@EnableSpringDataWebSupport
class SupplierRestMvcTest{

    @MockBean
    private SupplierRepository supplierRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetSuppliers()throws Exception{
        //Set supplier
        Supplier supplier = new Supplier();
        supplier.setAddress("Bayombong, Nueva Vizcaya");
        supplier.setContactNumber("NA");
        supplier.setSupplierName("Test Supplier");
        supplier.setTin("tin");
        
        Pageable pageable = PageRequest.of(0,20);
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        Page<Supplier> supplierPage = new PageImpl<Supplier>(suppliers, pageable, 20);

        given(supplierRepository.findAll(any(Pageable.class))).willReturn(supplierPage);

        this.mvc.perform(get("/api/suppliers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].supplierName", is(supplier.getSupplierName())));
    }

}