/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author qyt21516
 */
public class LookAndFeel {

  private JFrame jpowderFrame;

  public LookAndFeel(JFrame fr) {
    this.jpowderFrame = fr;

  }

  public void metal() {
    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      SwingUtilities.updateComponentTreeUI(jpowderFrame);
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

  public void nimbus() {
    try {
      UIManager.setLookAndFeel(
              new NimbusLookAndFeel());
      SwingUtilities.updateComponentTreeUI(jpowderFrame);
    } catch (Exception j) {
      j.printStackTrace();
    }
  }

  public void windows() {
    try {
      UIManager.setLookAndFeel(
              new WindowsLookAndFeel());
      SwingUtilities.updateComponentTreeUI(jpowderFrame);
    } catch (Exception j) {
      j.printStackTrace();
    }
  }

  public void motif() {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      SwingUtilities.updateComponentTreeUI(jpowderFrame);

    } catch (Exception e) {
      javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

  public void java() {
    try {
      UIManager.getCrossPlatformLookAndFeelClassName();
      SwingUtilities.updateComponentTreeUI(jpowderFrame);

    } catch (Exception e) {
      javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

  public void appleLook() {
    try {
      UIManager.setLookAndFeel("com.apple.mrj.swing.MacLookAndFeel");
      SwingUtilities.updateComponentTreeUI(jpowderFrame);

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Need Mac operating system ",
              "error", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void windosclassic() {
    try {
      UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
      SwingUtilities.updateComponentTreeUI(jpowderFrame);

    } catch (Exception e) {
      javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }

  public void LinuxandSolaris() {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
      SwingUtilities.updateComponentTreeUI(jpowderFrame);

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Need Linux operating system ",
              "error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
