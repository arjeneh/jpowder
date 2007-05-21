import junit.framework.*;
/*
 * FileNameTest.java
 * JUnit based test
 *
 * Created on 30 March 2007, 11:31
 */

/**
 *
 * @author regtrain
 */
public class FileNameTest extends TestCase {
    
    public FileNameTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(FileNameTest.class);
        
        return suite;
    }

    /**
     * Test of extension method, of class FileNameListModel.
     */
    public void testExtension() {
        System.out.println("extension");
        
        FileNameListModel instance = null;
        
        String expResult = "";
        String result = instance.extension();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filename method, of class FileNameListModel.
     */
    public void testFilename() {
        System.out.println("filename");
        
        FileNameListModel instance = null;
        
        String expResult = "";
        String result = instance.filename();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of path method, of class FileNameListModel.
     */
    public void testPath() {
        System.out.println("path");
        
        FileNameListModel instance = null;
        
        String expResult = "";
        String result = instance.path();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
