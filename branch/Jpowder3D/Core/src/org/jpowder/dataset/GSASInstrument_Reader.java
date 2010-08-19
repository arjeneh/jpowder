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
 * GSASInstrument_Reader.java
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
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Reads data from the instrument file and store into a Vector of a Vector.
 * It find DifC, DifA and Zero values from the instrument file.
 */
public class GSASInstrument_Reader {

    private static double t = 1;//time of flight
    private static double d;//d spacing
    private static Vector<Vector<String>> aBC = new Vector<Vector<String>>();
/**
 * Get the file paths then extract the required information and store into the
 * DataSet as Vector.
 *
 * @param dataset
 * @param aFile
 */
    public static void read(Vector<DataSet> dataset, File aFile) {
        FileReader fileReader = null;
        try {
            String aLine;
            fileReader = new FileReader(aFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            while ((aLine = bufferedReader.readLine()) != null) {
//                System.out.println(aLine);

                if (!aLine.isEmpty()) {

                    if (aLine.contains("ICONS")) {

//                        System.out.println(aLine);

                        StringTokenizer stringTokenizer = new StringTokenizer(aLine);
                        int numToken = stringTokenizer.countTokens();

                        Vector<String> str = new Vector<String>();

                        for (int i = 0; i < numToken; i++) {

                            String stringToken = stringTokenizer.nextToken();
                            str.add(stringToken);

                        }

                        aBC.addElement(str);


//                          for(String i:splits)
//                          System.out.print(i);
//t=zero+difC*d+difA*d^2
                        //Ad^2+C*d+(zero-t)=0



                    }
                }

            }

//            System.out.println(Double.parseDouble(aBC.get(0).get(4)) + "d^2 + " +
//                    Double.parseDouble(aBC.get(0).get(3)) + "d + " +
//                    Double.parseDouble(aBC.get(0).get(5)) + "-t" + "=0");
        } catch (IOException ex) {
            Logger.getLogger(GSASInstrument_Reader.class.getName()).log(Level.SEVERE, null, ex);

        }

        //

        for (int i = 0; i < dataset.size(); i++) {
            double difc = Double.parseDouble(aBC.get(i).get(3));
            double difa = Double.parseDouble(aBC.get(i).get(4));
            double zero = Double.parseDouble(aBC.get(i).get(5));

            GSAS_Instrument inst = new GSAS_Instrument(difc, difa, zero);
            dataset.elementAt(i).setGSAS_Instrument(inst);
        }
    }

}
