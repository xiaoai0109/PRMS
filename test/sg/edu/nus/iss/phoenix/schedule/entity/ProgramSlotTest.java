/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mia
 */
public class ProgramSlotTest {
    
    public ProgramSlotTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class ProgramSlot.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        ProgramSlot instance = new ProgramSlot();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class ProgramSlot.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 1;
        ProgramSlot instance = new ProgramSlot();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getRpname method, of class ProgramSlot.
     */
    @Test
    public void testGetRpname() {
        System.out.println("getRpname");
        ProgramSlot instance = new ProgramSlot();
        String expResult = "";
        String result = instance.getRpname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRpname method, of class ProgramSlot.
     */
    @Test
    public void testSetRpname() {
        System.out.println("setRpname");
        String rpname = "news";
        ProgramSlot instance = new ProgramSlot();
        instance.setRpname(rpname);
        assertEquals(rpname, instance.getRpname());

    }

    /**
     * Test of getDate method, of class ProgramSlot.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        ProgramSlot instance = new ProgramSlot();
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setDate method, of class ProgramSlot.
     */
    @Test
    public void testSetDate() throws ParseException {
        System.out.println("setDate");
        String dateOfProgram="2018-010-01";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(dateOfProgram);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
        ProgramSlot instance = new ProgramSlot();
        instance.setDate(sqlDate);
        System.out.println(instance.getDate());
        assertEquals(sqlDate, instance.getDate());
    }

    /**
     * Test of getSttime method, of class ProgramSlot.
     */
    @Test
    public void testGetSttime() {
        System.out.println("getSttime");
        ProgramSlot instance = new ProgramSlot();
        Time expResult = null;
        Time result = instance.getSttime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSttime method, of class ProgramSlot.
     */
    @Test
    public void testSetSttime() {
        System.out.println("setSttime");
        Time sttime = null;
        ProgramSlot instance = new ProgramSlot();
        instance.setSttime(sttime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDuration method, of class ProgramSlot.
     */
    @Test
    public void testGetDuration() {
        System.out.println("getDuration");
        ProgramSlot instance = new ProgramSlot();
        Time expResult = null;
        Time result = instance.getDuration();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDuration method, of class ProgramSlot.
     */
    @Test
    public void testSetDuration() {
        System.out.println("setDuration");
        Time duration = null;
        ProgramSlot instance = new ProgramSlot();
        instance.setDuration(duration);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresenter method, of class ProgramSlot.
     */
    @Test
    public void testGetPresenter() {
        System.out.println("getPresenter");
        ProgramSlot instance = new ProgramSlot();
        String expResult = "";
        String result = instance.getPresenter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPresenter method, of class ProgramSlot.
     */
    @Test
    public void testSetPresenter() {
        System.out.println("setPresenter");
        String presenter = "";
        ProgramSlot instance = new ProgramSlot();
        instance.setPresenter(presenter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProducer method, of class ProgramSlot.
     */
    @Test
    public void testGetProducer() {
        System.out.println("getProducer");
        ProgramSlot instance = new ProgramSlot();
        String expResult = "";
        String result = instance.getProducer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProducer method, of class ProgramSlot.
     */
    @Test
    public void testSetProducer() {
        System.out.println("setProducer");
        String producer = "";
        ProgramSlot instance = new ProgramSlot();
        instance.setProducer(producer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAll method, of class ProgramSlot.
     */
    @Test
    public void testSetAll() {
        System.out.println("setAll");
        int idIn = 0;
        String rpnameIn = "";
        Date dateIn = null;
        Time sttimeIn = null;
        Time durationIn = null;
        String presenterIn = "";
        String producerIn = "";
        ProgramSlot instance = new ProgramSlot();
        instance.setAll(idIn, rpnameIn, dateIn, sttimeIn, durationIn, presenterIn, producerIn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasEqualMapping method, of class ProgramSlot.
     */
    @Test
    public void testHasEqualMapping() {
        System.out.println("hasEqualMapping");
        ProgramSlot valueObject = null;
        ProgramSlot instance = new ProgramSlot();
        boolean expResult = false;
        boolean result = instance.hasEqualMapping(valueObject);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ProgramSlot.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ProgramSlot instance = new ProgramSlot();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class ProgramSlot.
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        ProgramSlot instance = new ProgramSlot();
        Object expResult = null;
        Object result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
