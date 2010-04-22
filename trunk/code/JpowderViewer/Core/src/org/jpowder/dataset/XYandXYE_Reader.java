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
            FileInputStream fileInputStream = new FileInputStream(aFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            int lineNum = 0;

            while ((aLine = bufferedReader.readLine()) != null) {
                lineNum++;
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
                localData.addElement(newRow);
         
            }//while readLine

            //System.out.print("Total LineNumber is: " + lineNum);

            fileInputStream.close();
            bufferedReader.close();

            // Determine if DASH xye format, which has an extra line at the
            // top equal to the wavelength
            int countColumn = 0;
            boolean dashFormat = false;
            if ( localData.firstElement().size() == 1)
            {
                int secondRowCount = localData.elementAt(1).size();
                for (int i = 1; i < localData.size(); i++)
                {

                    if  (i <= 5)
                    {
                        if ( localData.elementAt(i).size() != secondRowCount )
                        {
                          javax.swing.JOptionPane.showMessageDialog(null, "Invalid file formate.");
                          return null;
                        }
                    }
                    else
                    {
                        countColumn = secondRowCount;
                        dashFormat = true;
                        break;
                    }
                }
            }
            else
            {
              // Determine how many columns there are
              countColumn = localData.firstElement().size();
            }
            
            double wavelengthIfDASH = 0.0;
            if (dashFormat)
            {
                wavelengthIfDASH = localData.firstElement().firstElement();
                localData.remove(0);  
            }

            DataSet retVal = null;

            // create dataset object
            if (countColumn == 2) {
                retVal = new DataSetNoErrors(localData, aFile.getName());
            } else if (countColumn == 3) {
                retVal = new DataSetWithErrors(localData, aFile.getName());
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "File must contain either 2 or 3 columns");
            }

            if (dashFormat)
                retVal.setWaveLength(wavelengthIfDASH);

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
