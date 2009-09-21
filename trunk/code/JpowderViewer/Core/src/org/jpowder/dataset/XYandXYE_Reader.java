/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author ajm64
 */
public class XYandXYE_Reader {

    /**
     *  Read a powder diffraction dataset from a powder diffraction file
     *
     * @param aFile Name of the powder diffraction file to be read
     */
    public static DataSet read(File aFile) {
        String aLine;
        Vector<Vector<Double>> localData = new Vector<Vector<Double>>();

        try {
            FileInputStream fis = new FileInputStream(aFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            int lineNum = 0;

            while ((aLine = br.readLine()) != null) {
                lineNum++;
                // create a vector to hold the field values
                Vector<Double> newRow = new Vector<Double>();
                StringTokenizer st2 = new StringTokenizer(aLine);
                //
                int numToken = st2.countTokens();
                for (int i = 0; i < numToken; i++) {
                    //ignore the last STD by minusing 1.
                    String stringToken = st2.nextToken();
                    newRow.addElement(Double.parseDouble(stringToken));
                } //for
                localData.addElement(newRow);
            }//while readLine

            //System.out.print("Total LineNumber is: " + lineNum);

            fis.close();
            br.close();

            // Determine how many columns there are
            int countColumn = localData.firstElement().size();

            DataSet retVal = null;

            // create dataset object
            if (countColumn == 2) {
                retVal = new XY(localData, aFile.getName());
            } else if (countColumn == 3) {
                retVal = new XYE(localData, aFile.getName());
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "File must contain either 2 or 3 columns");
            }

            return retVal;

        } catch (MalformedURLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Not sure.");
            System.out.println("Malformed URL = " + e);
            return null;
        } catch (IOException io) {
            javax.swing.JOptionPane.showMessageDialog(null, "Can't open file.");
            System.out.println("IOException throws " + io);
            return null;
        } catch (java.lang.NumberFormatException nfe) {

            javax.swing.JOptionPane.showMessageDialog(null, "The file contains a character, we cannot process this file.");
            System.out.println("NumberFormatException throws " + nfe);
            return null;
        }



    }
}
