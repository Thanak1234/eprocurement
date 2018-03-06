package com.eprocurement;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.eprocurement.domain.Department;
import com.eprocurement.domain.DepartmentRepository;
import com.eprocurement.domain.Item;
import com.eprocurement.domain.ItemRepository;
import com.eprocurement.domain.Supplier;
import com.eprocurement.domain.SupplierRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class DataJpaTests {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private SupplierRepository supplierRepository;
  
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    //Basic JPA test for Item
    @Test
    public void testItemRepository()throws Exception {
    	//Create test item
    	Item testItem = new Item();
    	testItem.setName("shoes");
    	testItem.setDescription("black");
    	this.entityManager.persist(testItem);//save item 	
    	//check if item exist
    	Item item = this.itemRepository.findOne( (long) 1);
    	assertThat(item.getDescription()).isEqualTo("black");
    }
    
    //Basic JPA test for Supplier
    @Test
    public void testSupplierRepository()throws Exception {
    	//Create test supplier
    	Supplier testSupplier = new Supplier();
    	testSupplier.setSupplierName("ABC Company");
    	testSupplier.setAddress("Bayombong, Nueva Vizcaya");
    	testSupplier.setContactNumber("N/A");
    	this.entityManager.persist(testSupplier);//save supplier
	
    	//check if supplier exists
    	Supplier supplier = this.supplierRepository.findOne((long) 1);
    	assertThat(supplier.getSupplierName()).isEqualTo("ABC Company");
    }
    
    @Test
    public void testDepartmentRepository()throws Exception {
    	//create department
    	Department testDepartment = new Department();
    	testDepartment.setDepartmentName("Treasury");
    	testDepartment.setDepartmentHead("Charmia Dacanay");
    	this.entityManager.persist(testDepartment);	
    	//check if department exist
    	Department department = this.departmentRepository.findOne((long) 1);
    	assertThat(department.getDepartmentName()).isEqualTo("Treasury");
    	assertThat(department.getDepartmentHead()).isEqualTo("Charmia Dacanay");
    	
    }
    
    
 

}
