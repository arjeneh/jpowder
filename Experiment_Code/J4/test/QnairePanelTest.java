import junit.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
/*
 * QnairePanelTest.java
 * JUnit based test
 *
 * Created on 01 May 2007, 22:59
 */

/**
 *
 * @author regtrain
 */
public class QnairePanelTest extends TestCase {
    
    public QnairePanelTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getSelection method, of class QnairePanel.
     */
    public void testGetSelection() {
        System.out.println("getSelection");
        
        ButtonGroup group = null;
        
        JRadioButton expResult = null;
        JRadioButton result = QnairePanel.getSelection(group);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class QnairePanel.
     */
    public void testMain() {
        System.out.println("main");
        
        String[] args = null;
        
        QnairePanel.main(args);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
