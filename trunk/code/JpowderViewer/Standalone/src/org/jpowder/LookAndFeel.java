/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

//import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author M Arjeneh
 */
public class LookAndFeel {

    private JFrame jpowderFrame;

    public LookAndFeel(JFrame fr) {
        this.jpowderFrame = fr;

    }

    public static void metal() {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static void nimbus() {
        try {
            UIManager.setLookAndFeel(
                    new WindowsClassicLookAndFeel());
  
        } catch (Exception j) {
            j.printStackTrace();
        }
    }

    public static void windows() {
        try {
            UIManager.setLookAndFeel(
                    new WindowsLookAndFeel());

        } catch (Exception j) {
            j.printStackTrace();
        }
    }

    public static void motif() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
 

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void java() {
        try {
            UIManager.getCrossPlatformLookAndFeelClassName();
 

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void appleLook() {
        try {
            UIManager.setLookAndFeel("com.apple.mrj.swing.MacLookAndFeel");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Need Mac operating system ",
                    "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void windosclassic() {
        try {
            UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
  

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void LinuxandSolaris() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Need Linux operating system ",
                    "error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
