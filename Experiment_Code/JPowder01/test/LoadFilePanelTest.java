import junit.framework.*;
/*
 * LoadFilePanelTest.java
 * JUnit based test
 *
 * Created on 30 March 2007, 11:31
 */

/**
 *
 * @author regtrain
 */
public class LoadFilePanelTest extends TestCase {
    
    public LoadFilePanelTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(LoadFilePanelTest.class);
        
        return suite;
    }

    /**
     * Test of getFiles method, of class LoadFilePanel.
     */
    public void testGetFiles() {
        System.out.println("getFiles");
        
        String directory = "";
        String extension = "";
        LoadFilePanel instance = new LoadFilePanel();
        
        java.util.Vector expResult = null;
        java.util.Vector result = instance.getFiles(directory, extension);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
