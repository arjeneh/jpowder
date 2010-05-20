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
 * GSAS_Reader.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             
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
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *This class is for reading Gsas file format.
 * 
 */
public class GSAS_Reader {

    public static DataSet read(File aFile) {

        String aLine;
        Vector<Vector<Double>> localData = new Vector<Vector<Double>>();
        try {
            FileReader fileReader = new FileReader(aFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((aLine = bufferedReader.readLine()) != null) {
                int step = 0;
                if (aLine.contains("BANK")) // 
                {
                    while ((aLine = bufferedReader.readLine()) != null) {
                        step++;

//                        System.out.println(aLine);
                        if (!aLine.isEmpty()) {

                            // create a vector to hold the field values
                            Vector<Double> newRow = new Vector<Double>();
                            StringTokenizer stringTokenizer = new StringTokenizer(aLine);
                            //

                            int numToken = stringTokenizer.countTokens();
                            for (int i = 0; i < numToken; i++) {
                                //ignore the last STD by minusing 1.
                                String stringToken = stringTokenizer.nextToken();
                                newRow.addElement(Double.parseDouble(stringToken));



                            } //for

                            if (numToken != 0) {
//                                System.out.println("NR"+newRow.get(1));
                                localData.addElement(newRow);
//                                System.out.println("newRow:"+newRow);

                            } else {
                                break;
                            }

                        }

                    }
                    System.out.println("step" + step);
                }

            }

            // devide by binwidth*step_number
            if (localData.size() > 1) {
                double binwidth = localData.get(1).get(0) - localData.get(0).get(0);
                System.out.println("binwidth"+binwidth);
                for (int i = 0; i < localData.size(); i++) {
                    // divide all y-data by binwidth*step_number
                     localData.get(i).setElementAt(localData.get(i).get(1)/((i+1)*binwidth), 1);
                     localData.get(i).setElementAt(localData.get(i).get(2)/((i+1)*binwidth), 2);

//                  // divide all error-data by binwidth*step_number
//                  localData.get(i).get(2) = localData.get(i).get(2)/((i+1)*binwidth);
                }
            }

            fileReader.close();
            bufferedReader.close();
            // Determine how many columns there are
            int countColumn = localData.firstElement().size();


            DataSet retVal = null;

            // create dataset object
            if (countColumn == 3) {
                retVal = new DataSetWithErrors(localData, aFile.getName());
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "File must contain either 2 or 3 columns");
            }

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
}
