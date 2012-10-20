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
 * PowderFileCabinet.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.fileCabinet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpowder.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFileChooser;
import org.jpowder.dataset.Cif_Reader;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.GSAS_FXYE_Reader;
import org.jpowder.dataset.GSAS_FormatChecker;
import org.jpowder.dataset.XYandXYE_Reader;
import org.jpowder.util.Stopwatch;

/**
 *
 * @description
 * THIS CLASS KEEPS ALL POWDER FILES IN A HASHMAP. IT LOADS AND
 * NOTIFIES ALL REGISTERED LISTENERS - 
 *      mPowderFileCabinet = new PowderFileCabinet();
 *      mPowderFileCabinet.registerObserver(this);
 *      mPowderFileCabinet.registerObserver((PowderFileObserver) listModel);
 * 
 * ALL LISTENER HAS TO IMPLEMENT POWDERFILEOBSERVER*  * 
 */
public class PowderFileCabinet extends javax.swing.JComponent implements Subject, Serializable {

    // if ACCEPTED_FILE_TYPE is modified also modify string in loadFiles()
    public static final String[] ACCEPTED_FILE_TYPE = {""};
    private Vector<PowderFileObserver> observers = new Vector<PowderFileObserver>();
    private HashMap<String, DataSet> data = new HashMap<String, DataSet>();
    private String lastUpdateFileName;
    private ArrayList<File> m_file;
    private ArrayList<String> m_filename;
    private String filePath = null;

    public PowderFileCabinet() {
    }

    // @return data as a hashMap of fileName and data.
    public HashMap<String, DataSet> getData() {
        return this.data;
    }

    public void registerObserver(PowderFileObserver o) {
        observers.add(o);
    }

    public void removeObserver(PowderFileObserver o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        java.util.Iterator i = observers.iterator();
        while (i.hasNext()) {
            PowderFileObserver o = (PowderFileObserver) i.next();
            o.powderFileCabinetUpdate(this);
        }
    }

    //@param fileName to be added to data HashMap as Key
    //@param vdata to be added to data HashMap as Value.
    public void addFile(String fileName, DataSet ds) {
        data.put(fileName, ds);
        //dataSet.add(ds);
        notifyObservers();
    }

    //@param fileName to be deleted from data HashMap.
    public void deleteFile(String fileName) {
        data.remove(fileName);
        notifyObservers();
    }

    /**
     *  Browse for files on user machine. User needs to hold Ctlr and select files.
     *  Create a list of files to be choosen and put data into
     *  this.addFile(this.eachFileName, localData);
     */
    public void loadFiles() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);

        Vector<DataSet> datasets = null;

        // Set the accepted powder diffraction file extensions
        // and open a file chooser window for the user to select powder
        // diffraction file
        fileChooser.addChoosableFileFilter(new AcceptFileFilter(ACCEPTED_FILE_TYPE, ""));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int returnVal = fileChooser.showOpenDialog(null);
        Stopwatch totalStopwatch = new Stopwatch();
        Stopwatch lStopwatch = new Stopwatch();
        totalStopwatch.start();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // get the selected files
            File selectedFiles[] = fileChooser.getSelectedFiles();

            // to time how long it takes to read file

            // loop over the selected file
            for (int i = 0, n = selectedFiles.length; i < n; i++) {
                filePath = selectedFiles[i].getParent();
                lastUpdateFileName = selectedFiles[i].getName();

                lStopwatch.start();

                datasets = null;
                datasets = createDataSetFromPowderFile(selectedFiles[i]);

                lStopwatch.reset();

                for (int iDataset = 0; iDataset < datasets.size(); iDataset++) {
                    DataSet oneDataset = datasets.elementAt(iDataset);
                    // is it really necessary to get the file extension here since
                    // you should not have been allowed to a file with one of the
                    // extensions as defined in ACCEPTED_FILE_TYPE in the first place
                    if (checkAcceptedFileType(this.getLastUpdateFileName())) {
                        if (oneDataset != null) {
                            this.addFile(this.getLastUpdateFileName(), oneDataset);
                        }
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "File extension not recognised.");
                    }//acceptable end if extension matched
                }
            }//for

        }//if open approved

        totalStopwatch.reset();
    }//loadFiles

    /**
     *  Create a dataset object from a powder diffraction file
     *
     * @param aFile Name of the powder diffraction file to be read
     */
    public static Vector<DataSet> createDataSetFromPowderFile(String filename) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);

        } catch (Exception ex) {
            System.out.println(ex);
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Can't process (1) file " + filename);
        }
        return PowderFileCabinet.createDataSetFromPowderFile(fis, new File(filename));
    }

    public static Vector<DataSet> createDataSetFromPowderFile(File aFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(aFile);

        } catch (Exception ex) {
            System.out.println(ex);
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Can't process (2) file " + aFile.getPath());
        }
        return PowderFileCabinet.createDataSetFromPowderFile(fis, aFile);
    }

    /**
     * Create a dataset object from a powder file. It appears to be necessary
     * to input this method both with a File (i.e. which hold the filename) and
     * a FileInputStream of this file in to get the JpowderApplet to load a file,
     * i.e. for some security reason the stream appears to be required to be
     * open in JpowderApplet and cannot be open in Core.
     *
     * @param fis FileInputStream of the second argument aFile
     * @param aFile Name of the powder diffraction file to be read
     * @return vector of DataSet objects
     */
    public static Vector<DataSet> createDataSetFromPowderFile(FileInputStream fis, File aFile) {
        Vector<DataSet> retval = new Vector<DataSet>();
        try {
            if (aFile.getName().endsWith("xye") || aFile.getName().endsWith("xy") || aFile.getName().endsWith("txt")) {
                retval.addElement(XYandXYE_Reader.read(fis, aFile));
                return retval;
            }
            if (aFile.getName().endsWith("cif")) {
                retval.addElement(Cif_Reader.read(fis, aFile));
                return retval;
            }
            if (aFile.getName().endsWith("gss")) {
                //retval.addElement(GSAS_Reader.read(aFile));
                //return retval;
//                 return GSAS_FXYE_Reader.read(aFile);
                return GSAS_FormatChecker.read(fis, aFile);
            }
            if (aFile.getName().endsWith("")) {
                retval.addElement(XYandXYE_Reader.read(fis, aFile));
                return retval;
            }

            return null;
        } catch (Exception ex) {
            System.out.println(ex);
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Can't process (3) file " + aFile.getPath());
        }
        return null;
    }

    /**
     * Checking whether file type is allowed
     * @param filenames
     **/
    public boolean checkAcceptedFileType(String filenames) {

        boolean result = true;
        for (int i = ACCEPTED_FILE_TYPE.length - 1; i >= 0; i--) {
            if (filenames.endsWith(ACCEPTED_FILE_TYPE[i])) {
                result = true;
                break;
            } else {
                result = false;
            }
        }//end for
        return result;
    }//end checkAcceptedFileType

    public void loadMultipleFiles(File file) {
        if (file.isFile()) {
            //loadFile(file);
        } else {
            File multFiles[] = file.listFiles();
            for (File subFile : multFiles) {
                loadMultipleFiles(subFile);
            }
        }
    }

    public ArrayList<String> allfilesName() {
        return m_filename;
    }

    public ArrayList<File> allfiles() {
        return m_file;
    }

    public static void main(String args[]) {
        PowderFileCabinet pdc = new PowderFileCabinet();

        javax.swing.JFrame frame = new javax.swing.JFrame("PowderFileCabinet Sample");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.add(pdc, java.awt.BorderLayout.NORTH);
        frame.setSize(300, 100);
        frame.setVisible(true);

        pdc.loadFiles();
    }
    //@return the last file inserted.

    public String getLastUpdateFileName() {
        return lastUpdateFileName;
    }

    public void setLastUpdateFileName(String fileName) {
        this.lastUpdateFileName = fileName;
    }
}

