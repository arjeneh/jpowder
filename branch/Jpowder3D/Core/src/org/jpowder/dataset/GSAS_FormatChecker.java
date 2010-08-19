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
 * GSAS_FormatChecker.java
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This class check which GSAS file is being read
 * The Old one or the new format.
 * 
 */
public class GSAS_FormatChecker {

/**
 * Check for keyword to determine what kind of GSAS file format is being read.
 * if is FXYE then is new format if ALT then is old format.
 * @param aFile
 * @return Vector<DataSet>
 */
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
