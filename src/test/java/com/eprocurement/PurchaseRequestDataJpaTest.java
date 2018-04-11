package com.eprocurement;

import com.eprocurement.department.Department;
import com.eprocurement.purchaserequest.PurchaseRequest;
import com.eprocurement.purchaserequest.PurchaseRequestRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PurchaseRequestDataJpaTest
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PurchaseRequestDataJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PurchaseRequestRepository purchaseRequestRepository;

    @MockBean
    private PurchaseRequest testPr;

    @Before
    public void setUp() {

        //create department
        Department department = new Department();
        department.setDepartmentName("MPDO");
        department.setDepartmentHead("Head of MPDO");

        //create pr
        testPr = new PurchaseRequest();
        testPr.setDepartment(department);
        testPr.setPrNo("1000");
        testPr.setModeOfProcurement("Small Value Procurement");
        testPr.setPurpose("for testing");

    }

    @Test
    public void shouldSavePurchaseRequest() throws Exception {
        this.entityManager.persist(testPr);
        if (purchaseRequestRepository.findById("1000").isPresent()) {
            PurchaseRequest pr = this.purchaseRequestRepository.findById("1000").get();
            assertThat(pr.getPurpose()).isEqualTo("for testing");
        }
    }

    @Test
    public void shouldUpdatePurchaseRequest() throws Exception {
        testPr.setPurpose("for testing update");
        this.entityManager.persist(testPr);
        if (purchaseRequestRepository.findById("1000").isPresent()) {
            PurchaseRequest pr = this.purchaseRequestRepository.findById("1000").get();
            assertThat(pr.getPurpose()).isEqualTo("for testing update");
        }
    }
}