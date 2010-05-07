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
 * Cif_Reader.java
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
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *This File reads and extracts data from the files with cif extension
 * 
 */
public class Cif_Reader {

    private static double waveLength;

    public static DataSet read(File aFile) {

        String aLine;
        Vector<Vector<Double>> localData = new Vector<Vector<Double>>();
        try {
            FileReader fileReader = new FileReader(aFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((aLine = bufferedReader.readLine()) != null) {
                if ( !aLine.startsWith("#") ) {
                    if (aLine.contains("_diffrn_radiation_wavelength")) {
//                    aLine = aLine.replace("_diffrn_radiation_wavelength", "");
//                    aLine = aLine.replaceAll(" ", "");


                        String[] splits = aLine.split(" ");
                        if (splits == null || splits.length == 0) {

                            javax.swing.JOptionPane.showMessageDialog(null, "Cannot find Wavelength");

                        } else {
                            try {
                                waveLength = Double.parseDouble(splits[splits.length - 1]);


                            } catch (NumberFormatException ex) {

                                javax.swing.JOptionPane.showMessageDialog(null, "Can not find the Wavelength");


                            }
                        }
                    }




                    if (aLine.contains("loop_")) // is cif case sensitive
                    {
                        int countColumns = 0;
                        int pd_proc_2theta_corrected_index = 0;
                        int pd_proc_intensity_total_index = 0;
                        while ((aLine = bufferedReader.readLine()) != null) {
                            if (aLine.contains("_")) {
                                countColumns++;
                                if (aLine.contains("_pd_proc_2theta_corrected") || aLine.contains("_pd_meas_2theta_scan")) {
                                    pd_proc_2theta_corrected_index = countColumns;
                                }
                                if (aLine.contains("_pd_proc_intensity_total") || aLine.contains("_pd_proc_intensity_net")) {
                                    pd_proc_intensity_total_index = countColumns;
                                }
                            } else {

                                break;
                            }
                        }
                        if (pd_proc_2theta_corrected_index == 0) {
                            break;
                        }
                        while ((aLine = bufferedReader.readLine()) != null) {
                            if (!aLine.startsWith("#")) {
                                Vector<Double> newRow = new Vector<Double>();
                                StringTokenizer stringTokenizer = new StringTokenizer(aLine);
                                int numToken = stringTokenizer.countTokens();
                                if (numToken != countColumns) {
                                    break;
                                }
                                for (int i = 1; i <= numToken; i++) {
                                    String stringToken = stringTokenizer.nextToken();
                                    if (i == pd_proc_2theta_corrected_index) {
                                        newRow.addElement(Double.parseDouble(stringToken));

                                    }
                                    if (i == pd_proc_intensity_total_index) {
                                        if (stringToken.contains("(")) {
                                            newRow.addElement(Double.parseDouble(stringToken.substring(0, stringToken.indexOf("("))));

                                            newRow.addElement(Double.parseDouble(stringToken.substring(stringToken.indexOf("(") + 1, stringToken.indexOf(")"))));

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
                }
            }
            fileReader.close();
            bufferedReader.close();
            // Determine how many columns there are
            int countColumn = localData.firstElement().size();

            DataSet retVal = null;

            // create dataset object
            if (countColumn == 2) {
                retVal = new DataSetNoErrors(localData, aFile.getName());
            } else if (countColumn == 3) {
                retVal = new DataSetWithErrors(localData, aFile.getName());
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "File must contain either 2 or 3 columns");
            }
            retVal.setWaveLength(waveLength);

            return retVal;


        } catch (MalformedURLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file formate.");
            System.out.println("Malformed URL = " + e);
            return null;
        } catch (IOException io) {
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file formate.");
            System.out.println("IOException throws " + io);
            return null;
        } catch (java.lang.NumberFormatException nfe) {

            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file formate.");
            System.out.println("NumberFormatException throws " + nfe);
            return null;
        }
    }
}
