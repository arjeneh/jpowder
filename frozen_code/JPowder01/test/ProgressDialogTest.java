import junit.framework.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
/*
 * ProgressDialogTest.java
 * JUnit based test
 *
 * Created on 30 March 2007, 11:31
 */

/**
 *
 * @author regtrain
 */
public class ProgressDialogTest extends TestCase {
    
    public ProgressDialogTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ProgressDialogTest.class);
        
        return suite;
    }

    /**
     * Test of create method, of class ProgressDialog.
     */
    public void testCreate() {
        System.out.println("create");
        
        Object parent = null;
        String title = "";
        String message = "";
        
        ProgressDialog expResult = null;
        ProgressDialog result = ProgressDialog.create(parent, title, message);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTask method, of class ProgressDialog.
     */
    public void testAddTask() {
        System.out.println("addTask");
        
        Runnable r = null;
        ProgressDialog instance = null;
        
        instance.addTask(r);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompletionMessage method, of class ProgressDialog.
     */
    public void testSetCompletionMessage() {
        System.out.println("setCompletionMessage");
        
        String msg = "";
        ProgressDialog instance = null;
        
        instance.setCompletionMessage(msg);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCancellable method, of class ProgressDialog.
     */
    public void testSetCancellable() {
        System.out.println("setCancellable");
        
        boolean canCancel = true;
        ProgressDialog instance = null;
        
        instance.setCancellable(canCancel);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCancelText method, of class ProgressDialog.
     */
    public void testSetCancelText() {
        System.out.println("setCancelText");
        
        String text = "";
        ProgressDialog instance = null;
        
        instance.setCancelText(text);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCloseText method, of class ProgressDialog.
     */
    public void testSetCloseText() {
        System.out.println("setCloseText");
        
        String text = "";
        ProgressDialog instance = null;
        
        instance.setCloseText(text);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class ProgressDialog.
     */
    public void testRun() {
        System.out.println("run");
        
        ProgressDialog instance = null;
        
        instance.run();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finished method, of class ProgressDialog.
     */
    public void testFinished() {
        System.out.println("finished");
        
        ProgressDialog instance = null;
        
        instance.finished();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
