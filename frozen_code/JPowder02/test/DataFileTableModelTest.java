import junit.framework.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.io.*;
import java.util.*;
import java.net.*;
/*
 * DataFileTableModelTest.java
 * JUnit based test
 *
 * Created on 30 March 2007, 11:31
 */

/**
 *
 * @author regtrain
 */
public class DataFileTableModelTest extends TestCase {
    
    public DataFileTableModelTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(DataFileTableModelTest.class);
        
        return suite;
    }

    /**
     * Test of getRowCount method, of class DataFileTableModel.
     */
    public void testGetRowCount() {
        System.out.println("getRowCount");
        
        DataFileTableModel instance = null;
        
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnCount method, of class DataFileTableModel.
     */
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        
        DataFileTableModel instance = null;
        
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnName method, of class DataFileTableModel.
     */
    public void testGetColumnName() {
        System.out.println("getColumnName");
        
        int columnIndex = 0;
        DataFileTableModel instance = null;
        
        String expResult = "";
        String result = instance.getColumnName(columnIndex);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnClass method, of class DataFileTableModel.
     */
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        
        int columnIndex = 0;
        DataFileTableModel instance = null;
        
        Class expResult = null;
        Class result = instance.getColumnClass(columnIndex);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCellEditable method, of class DataFileTableModel.
     */
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        
        int rowIndex = 0;
        int columnIndex = 0;
        DataFileTableModel instance = null;
        
        boolean expResult = true;
        boolean result = instance.isCellEditable(rowIndex, columnIndex);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class DataFileTableModel.
     */
    public void testGetValueAt() {
        System.out.println("getValueAt");
        
        int rowIndex = 0;
        int columnIndex = 0;
        DataFileTableModel instance = null;
        
        Object expResult = null;
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueAt method, of class DataFileTableModel.
     */
    public void testSetValueAt() {
        System.out.println("setValueAt");
        
        Object aValue = null;
        int rowIndex = 0;
        int columnIndex = 0;
        DataFileTableModel instance = null;
        
        instance.setValueAt(aValue, rowIndex, columnIndex);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class DataFileTableModel.
     */
    public void testGetData() {
        System.out.println("getData");
        
        DataFileTableModel instance = null;
        
        Vector expResult = null;
        Vector result = instance.getData();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
