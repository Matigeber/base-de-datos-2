package ar.edu.unlp.info.bd2.Tp.promocion;

import ar.edu.unlp.info.bd2.config.*;
import ar.edu.unlp.info.bd2.services.*;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.MLException;
import ar.edu.unlp.info.bd2.utils.DBInitializer;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

@Rollback(true)
@SpringBootTest(classes = SpringDataMLService.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		classes = {Config.class},
        loader = AnnotationConfigContextLoader.class)

public class MLStatisticsTestCase {

    @Autowired
    MLService service;
    
    protected MLService getService() {
        return service;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    
    @BeforeEach
    public void setUp() throws Exception {
    	this.service = this.getService();
//    	DBInitializer initializer = new DBInitializer();
//        initializer.prepareDB(this.getService());
    }
    
    

    private <T> void assertListEquality(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size()) {
            Assert.fail("Lists have different size");
        }

        for (T objectInList1 : list1) {
            if (!list2.contains(objectInList1)) {
                Assert.fail(objectInList1 + " is not present in list2");
            }
        }
    }

    
    @Test
    public void testGetAllPurchasesMadeByUser() {
        assertEquals(5,this.service.getAllPurchasesMadeByUser("silviasez428@gmail.com").size());
    }
    
    
    
    @Test
    public void testGetUsersSpendingMoreThanInPurchase() {
        List<User> users = this.service.getUsersSpendingMoreThanInPurchase(Float.valueOf(920000F));
        assertEquals(3,users.size());
        this.assertListEquality(users.stream().map(property -> property.getEmail()).collect(Collectors.toList()),Arrays.asList("carlospascual402@hotmail.com","matiasgarca37@hotmail.com","maracalvo55@yahoo.com"));
    }
    
    @Test
    public void testGetPurchasesInPeriod() throws ParseException {
        List<Purchase> purchases = this.service.getPurchasesInPeriod(sdf.parse("8/1/2020"),sdf.parse("20/01/2020"));
        assertEquals(4,purchases.size());
        this.assertListEquality(purchases.stream().map(property -> property.getAddress()).collect(Collectors.toList()),Arrays.asList("Calle 56 Nº1582","Calle 51 Nº399","Calle 44 Nº812","Calle 52 Nº816"));
    }
    

}