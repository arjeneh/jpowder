/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qyt21516
 */
public class GSAS_FormatChecker {


    public static Vector<DataSet> read(File aFile) {
        FileReader fileReader = null;
        String aLine = null;
        try {

            fileReader = new FileReader(aFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((aLine = bufferedReader.readLine()) != null) {

                if (aLine.contains("BANK")) {

                    String[] splits = aLine.split(" ");

                    if (splits == null || splits.length == 0) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Can't compute binwidth");
                        return null;
                    } else {
                        try {
                            if (splits[splits.length - 1].equals("FXYE")) {
                                return GSAS_FXYE_Reader.read(aFile);

                            }
                            if (splits[splits.length - 1].equals("ALT")) {
                                return GSAS_ALT_Reader.read(aFile);
                            }

                        } catch (NumberFormatException ex) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Can't compute binwidth");
                            return null;
                        }
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GSAS_FormatChecker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileReader.close();
            } catch (IOException ex) {
                Logger.getLogger(GSAS_FormatChecker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
