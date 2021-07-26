package ar.edu.unlp.info.bd2.Tp.promocion;

//class TpPromocionApplicationTests {

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.*;


import ar.edu.unlp.info.bd2.config.Config;
import ar.edu.unlp.info.bd2.repositories.MLException;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;


@Rollback(true)
@SpringBootTest(classes = SpringDataMLService.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {Config.class},
        loader = AnnotationConfigContextLoader.class)
public class TpPromocionApplicationTests {

    @Autowired
    MLService service;

    protected MLService getService() {
        return service;
    }

    @BeforeEach
    public void setUp() {
        this.service = this.getService();
    }
    
    /*
    @Test
    public void testCreateCategory() throws MLException {
        Category c = this.service.createCategory("Hogar");
        System.out.println("PRINTTTTTTTTTTTTTTTTTTTTTTT");
        System.out.println(c.getId());
        assertNotNull(c.getId());
        assertEquals("Hogar",c.getName());
        Optional<Category> oc = this.service.getCategoryByName("Hogar");
        if (! oc.isPresent()) {
            throw new MLException("Category not found");
        }
        Category cat = oc.get();
        assertNotNull(cat.getId());
        assertEquals("Hogar",cat.getName());
    }
    
    @Test
    public void testCreateUser() throws MLException{
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 17);
        Date dob = cal.getTime();
        User u = this.service.createUser("federico.orlando@info.unlp.edu.ar", "Federico Orlando", "pas$w0rd", dob);
        assertNotNull (u.getId());
        assertEquals("Federico Orlando",u.getFullname());
        Optional<User> us = this.service.getUserByEmail("federico.orlando@info.unlp.edu.ar");
        if (!us.isPresent()) {
            throw new MLException("User doesn't exists");
        }
        User user = us.get();
        assertNotNull (user.getId());
        assertEquals("Federico Orlando",user.getFullname());
        assertEquals(dob, user.getDayOfBirth());
        assertEquals("pas$w0rd", user.getPassword());
        MLException ex = assertThrows(MLException.class, () -> this.service.createUser("federico.orlando@info.unlp.edu.ar", "Federico Orlando", "pas$w0rd", dob));
        assertEquals("Constraint Violation",ex.getMessage());
    }
    */
    @Test
    public void testCreateProvider() throws MLException {
        Provider p = this.service.createProvider("Philips",30715589634L);
        assertNotNull (p.getId());
        assertEquals("Philips", p.getName());
        Optional<Provider> prov = this.service.getProviderByCuit(30715589634L);
        if (!prov.isPresent()) {
            throw new MLException("Provider doesn't exists");
        }
        Provider provider = prov.get();
        assertNotNull (provider.getId());
        assertEquals("Philips", provider.getName());
        MLException ex = assertThrows(MLException.class, () -> this.service.createProvider("Philips",30715589634L));
        assertEquals("Constraint Violation",ex.getMessage());
    }
    
}
