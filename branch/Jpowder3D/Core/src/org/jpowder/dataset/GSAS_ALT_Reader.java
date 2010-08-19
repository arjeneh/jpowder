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
 * GSAS_ALT_Reader.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reading the old GSAS format.
 *
 */

public class GSAS_ALT_Reader {

    private static String aLine;
    private static double deltaT;
    private static int bankNum = 0;
/**
 * Read file
 * @param aFile
 * @return Vector<DataSet>
 */
    public static Vector<DataSet> read(File aFile) {
        bankNum = 0;

        Vector<Vector<Double>> localData = new Vector<Vector<Double>>();

        Vector<DataSet> retVal = new Vector<DataSet>();
        try {
            FileReader fileReader = new FileReader(aFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((aLine = bufferedReader.readLine()) != null) {

                if (aLine.contains("BANK")) //
                {
                    bankNum++;
                    String[] splits = aLine.split(" ");

                    if (splits == null || splits.length == 0) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Can't compute binwidth");
                    } else {
                        try {
                            deltaT = Double.parseDouble(splits[splits.length - 2]);
                        } catch (NumberFormatException ex) {

                            javax.swing.JOptionPane.showMessageDialog(null, "Can't compute binwidth");

                        }
                    }

                    localData = readBank(bufferedReader);
                    calculateData(localData);
                    int countColumn = localData.firstElement().size();
                    // create dataset object
//                    if (countColumn == 3) {

                    retVal.addElement(new DataSetWithErrors(localData, aFile.getName()));
                    //                retVal.addElement(new DataSetWithErrors(localData, "fake"));
//                    }
//                    else {
//                        javax.swing.JOptionPane.showMessageDialog(null, "File must contain either 2 or 3 columns");
//                        return null;
//                    }
                } // finished reading in bank
            }
            fileReader.close();
            bufferedReader.close();
            for (int i = 0; i < bankNum; i++) {
                retVal.get(i).setFileName("_Bank " + i + " " + aFile.getName());
            }
            retVal.get(0).setXUnit("TOF");

            return retVal;

        } catch (MalformedURLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file format.");
            System.out.println("Malformed URL = " + e);
            return null;
        } catch (IOException io) {
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file format.");
            System.out.println("IOException throws " + io);
            return null;
        } catch (java.lang.NumberFormatException nfe) {

            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file format.");
            System.out.println("NumberFormatException throws " + nfe);
            return null;
        }
    }

    /**
     * read in powder data file until row does not consist of numbers then return
     * a data set when ever reaches a new bank.
     * @param br
     * @return
     */
    public static Vector<Vector<Double>> readBank(BufferedReader br) {
        Vector<Vector<Double>> localData = new Vector<Vector<Double>>();
        try {
            while ((aLine = br.readLine()) != null) {
                if (!aLine.isEmpty()) {
                    // create a vector to hold the field values

                    Vector<Double> newRow = new Vector<Double>();

                    String[] st = aLine.split("");
                    try {
                        newRow.addElement(Double.parseDouble(aLine.substring(0, 8)));
                        newRow.addElement(Double.parseDouble(aLine.substring(8, 15)));
                        newRow.addElement(Double.parseDouble(aLine.substring(15, 20)));

                        newRow.addElement(Double.parseDouble(aLine.substring(20, 28)));
                        newRow.addElement(Double.parseDouble(aLine.substring(28, 35)));
                        newRow.addElement(Double.parseDouble(aLine.substring(35, 40)));

                        newRow.addElement(Double.parseDouble(aLine.substring(40, 48)));
                        newRow.addElement(Double.parseDouble(aLine.substring(48, 55)));
                        newRow.addElement(Double.parseDouble(aLine.substring(55, 60)));

                        newRow.addElement(Double.parseDouble(aLine.substring(60, 68)));
                        newRow.addElement(Double.parseDouble(aLine.substring(68, 75)));
                        newRow.addElement(Double.parseDouble(aLine.substring(75, 80)));
                        
                    } catch (NumberFormatException e) {
                        return localData;
                    }

                    if (st.length != 0) {
                        localData.addElement(newRow);

                    } else {
                        return localData;

                    }
                 
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GSAS_FXYE_Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localData;
    }

    /**
     * This method calculate binwidth and get data and then gets y and error
     * bar column and divided by binwidth.
     * @param localData
     */
    public static void calculateData(Vector<Vector<Double>> localData) {



        if (localData.size() > 1) {

            for (int i = 0; i < localData.size(); i++) {
//                System.out.println(localData.get(i).get(0));


                double xDivideBy32 = localData.get(i).get(0) / 32;//time of flight
                double yDividedBy1000 = localData.get(i).get(1) / 1000;//intensity
                double eDividedBy1000 = localData.get(i).get(2) / 1000;//error bar

                double binwidth = xDivideBy32 * deltaT;

                localData.get(i).setElementAt(xDivideBy32, 0);
                localData.get(i).setElementAt(yDividedBy1000 / (binwidth), 1);
                localData.get(i).setElementAt(eDividedBy1000 / (binwidth), 2);
            }
        }

    }
//
//    public static void main(String[] args) {
//        read(new File("C:/Users/Arjeneh/Desktop/newPRL.gss"));
//    }
}
