/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * LookAndFeel.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Milad Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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
 * 
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
        //try {
          //  UIManager.setLookAndFeel("com.apple.mrj.swing.MacLookAndFeel");


        //} catch (Exception e) {
        //    JOptionPane.showMessageDialog(null, "Need Mac operating system ",
      //              "error", JOptionPane.ERROR_MESSAGE);
       // }
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
