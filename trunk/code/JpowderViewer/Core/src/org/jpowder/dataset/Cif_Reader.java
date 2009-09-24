/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author milad
 */
public class Cif_Reader {

    public static DataSet read(File aFile) {

        String aLine;
        Vector<Vector<Double>> localData = new Vector<Vector<Double>>();
        try {
            FileReader fr = new FileReader(aFile);
            BufferedReader br = new BufferedReader(fr);
            while ((aLine = br.readLine()) != null) {
                if (aLine.contains("loop_")) // is cif case sensitive
                {
                    int countColumns = 0;
                    int pd_proc_2theta_corrected_index = 0;
                    int pd_proc_intensity_total_index = 0;
                    while ((aLine = br.readLine()) != null) {
                        if (aLine.contains("_")) {
                            countColumns++;
                            if (aLine.contains("_pd_proc_2theta_corrected")) {
                                pd_proc_2theta_corrected_index = countColumns;
                            }
                            if (aLine.contains("_pd_proc_intensity_total")) {
                                pd_proc_intensity_total_index = countColumns;
                            }
                        } else {
                            break;
                        }
                    }
                    if (pd_proc_2theta_corrected_index == 0) {
                        break;
                    }
                    while ((aLine = br.readLine()) != null) {
                        Vector<Double> newRow = new Vector<Double>();
                        StringTokenizer st2 = new StringTokenizer(aLine);
                        int numToken = st2.countTokens();
                        if (numToken != countColumns) {
                            break;
                        }
                        for (int i = 1; i <= numToken; i++) {
                            String stringToken = st2.nextToken();
                            if (i == pd_proc_2theta_corrected_index) {
                                newRow.addElement(Double.parseDouble(stringToken));
                                // System.out.println(stringToken);
                            }
                            if (i == pd_proc_intensity_total_index) {
                                if (stringToken.contains("(")) {
                                    newRow.addElement(Double.parseDouble(stringToken.substring(0, stringToken.indexOf("("))));
                                    // System.out.println(stringToken.substring(0,stringToken.indexOf("(")));
                                    newRow.addElement(Double.parseDouble(stringToken.substring(stringToken.indexOf("(")+1, stringToken.indexOf(")"))));
                                      // System.out.println(stringToken.substring(stringToken.indexOf(")")+1,stringToken.indexOf(")")));
                                } else {
                                    newRow.addElement(Double.parseDouble(stringToken));

                                }
                                localData.addElement(newRow);
                                //System.out.println(localData);
                            }
                        }
                    }
                    
                }
            }
            fr.close();
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
