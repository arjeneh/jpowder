import junit.framework.*;
import javax.swing.JScrollPane;
/*
 * MainAppletTest.java
 * JUnit based test
 *
 * Created on 30 March 2007, 11:31
 */

/**
 *
 * @author regtrain
 */
public class MainAppletTest extends TestCase {
    
    public MainAppletTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MainAppletTest.class);
        
        return suite;
    }

    /**
     * Test of getFileToReadVector method, of class MainApplet.
     */
    public void testGetFileToReadVector() {
        System.out.println("getFileToReadVector");
        
        JPowder instance = new JPowder();
        
        java.util.Vector expResult = null;
        java.util.Vector result = instance.getFileToReadVector();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentFile method, of class MainApplet.
     */
    public void testSetCurrentFile() {
        System.out.println("setCurrentFile");
        
        String str = "";
        JPowder instance = new JPowder();
        
        instance.setCurrentFile(str);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentFile method, of class MainApplet.
     */
    public void testGetCurrentFile() {
        System.out.println("getCurrentFile");
        
        JPowder instance = new JPowder();
        
        String expResult = "";
        String result = instance.getCurrentFile();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class MainApplet.
     */
    public void testInit() {
        System.out.println("init");
        
        JPowder instance = new JPowder();
        
        instance.init();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initURLSource method, of class MainApplet.
     */
    public void testInitSource() {
        System.out.println("initSource");
        
        JPowder instance = new JPowder();
        
        instance.initURLSource();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readURLFileToTable method, of class MainApplet.
     */
    public void testReadFileToTable() {
        System.out.println("readFileToTable");
        
        java.net.URL codeURL = null;
        JPowder instance = new JPowder();
        
        instance.readURLFileToTable(codeURL);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAppletParam method, of class MainApplet.
     */
    public void testReadAppletParam() {
        System.out.println("readAppletParam");
        
        JPowder instance = new JPowder();
        
        instance.readAppletParam();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
