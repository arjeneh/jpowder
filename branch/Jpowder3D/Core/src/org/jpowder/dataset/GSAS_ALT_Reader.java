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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qyt21516
 */
public class GSAS_ALT_Reader {

    private static String aLine;
    private static double deltaT;
    private static int bankNum = 0;

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
                    System.out.println(localData);
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
                    int numberDataPointInLine = 0;
                    Vector<Double> newRow = new Vector<Double>();
                    Vector<Double> dataPoint1 = new Vector<Double>();
                    Vector<Double> dataPoint2 = new Vector<Double>();
                    Vector<Double> dataPoint3 = new Vector<Double>();
                    Vector<Double> dataPoint4 = new Vector<Double>();


                    String[] splits = aLine.trim().split("  ");


          StringTokenizer stringTokenizer = new StringTokenizer(aLine);
                    //
          
                    int numToken = stringTokenizer.countTokens();
                    for (int i = 0; i < numToken; i++) {
                        String stringToken = stringTokenizer.nextToken();
                          newRow.addElement(Double.parseDouble(stringToken));
                    }


                    for (int i = 0; i < 3; i++) {
                       
                        dataPoint1.add(newRow.get(i));
                    }

                    for (int i = 3; i < 6; i++) {

                        dataPoint2.add(newRow.get(i));
                    }
                         for (int i = 6; i < 9; i++) {

                        dataPoint3.add(newRow.get(i));
                    }
                         for (int i = 9; i < 12; i++) {

                        dataPoint4.add(newRow.get(i));
                    }
             
//                    for (int i = 0; i < 3; i++) {
//
//                        String dataPoint = splits[i];
//
//                        try {
//                            newRow.addElement(Double.parseDouble(dataPoint));
//
//                        } catch (NumberFormatException e) {
//                            return localData;
//                        }
//
//                    } //for

                 
                    if (splits.length != 0) {
                        localData.addElement(dataPoint1);
                        localData.addElement(dataPoint2);
                        localData.addElement(dataPoint3);
                        localData.addElement(dataPoint4);
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


                double xDivideBy32=localData.get(i).get(0)/32;//time of flight      
                double yDividedBy1000=localData.get(i).get(1)/1000;//intensity
                double eDividedBy1000=localData.get(i).get(2)/1000;//error bar
      
                double binwidth = xDivideBy32 * deltaT;
    
                localData.get(i).setElementAt(xDivideBy32, 0);
                localData.get(i).setElementAt(yDividedBy1000 / (binwidth), 1);
                localData.get(i).setElementAt(eDividedBy1000 / (binwidth), 2);
            }
        }

    }

//    public static void main(String[] args) {
//        read(new File("C:/Users/Arjeneh/Desktop/PRL_ALT.gss"));
//    }
}
