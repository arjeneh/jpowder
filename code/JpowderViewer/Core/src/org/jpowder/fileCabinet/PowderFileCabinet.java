package org.jpowder.fileCabinet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpowder.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFileChooser;
import org.jpowder.dataset.Cif_Reader;
import org.jpowder.dataset.DataSet;
import org.jpowder.dataset.XYandXYE_Reader;
import org.jpowder.util.Stopwatch;

/**
 * File: PowderFileCabinet.java
 *
 * @description
 * THIS CLASS KEEPS ALL POWDER FILES IN A HASHMAP. IT LOADS AND
 * NOTIFIES ALL REGISTERED LISTENERS - 
 *      mPowderFileCabinet = new PowderFileCabinet();
 *      mPowderFileCabinet.registerObserver(this);
 *      mPowderFileCabinet.registerObserver((PowderFileObserver) listModel);
 * 
 * ALL LISTENER HAS TO IMPLEMENT POWDERFILEOBSERVER* 
 * 
 * @author kreecha_pu@yahoo.com Kreecha Pupahiboon
 * 
 * This code is copyright (c) 2008 Rutherford Appleton and Kasem Bundit University
 */
public class PowderFileCabinet extends javax.swing.JComponent implements Subject, Serializable {

    // if ACCEPTED_FILE_TYPE is modified also modify string in loadFiles()
    public static final String[] ACCEPTED_FILE_TYPE = {"xy", "xye", "txt", "cif"};
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
        System.out.println("PowderFileCabinet.java has been added with: " + data.toString());
        //notifyObservers();
    }

    //@param fileName to be deleted from data HashMap.
    public void deleteFile(String fileName) {
        data.remove(fileName);
        System.out.println("PowderFileCabinet.java has been deleted: " + data.toString());
        //notifyObservers();
    }

    /**
     *  Browse for files on user machine. User needs to hold Ctlr and select files.
     *  Create a list of files to be choosen and put data into
     *  this.addFile(this.eachFileName, localData);
     */
    public void loadFiles() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);

        //Vector<Vector<Double>> localData = null;
        DataSet oneDataset = null;

        // Set the accepted powder diffraction file extensions
        // and open a file chooser window for the user to select powder
        // diffraction file
        fileChooser.addChoosableFileFilter(new AcceptFileFilter(ACCEPTED_FILE_TYPE, "File (*.xy, *.xye, *.txt,*cif)"));
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

                oneDataset = null;
                oneDataset = createDataSetFromPowderFile(selectedFiles[i]);

                System.out.println("\nTime it took to load " + selectedFiles[i]);
                System.out.println(lStopwatch.getElapsedTime());
                lStopwatch.reset();

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
            }//for

        }//if open approved
        System.out.println("\nTotal time took to load and plot the all data:");
        System.out.println(totalStopwatch.getElapsedTime());
        totalStopwatch.reset();
    }//loadFiles

    /**
     *  Create a dataset object from a powder diffraction file
     *
     * @param aFile Name of the powder diffraction file to be read
     */
    public static DataSet createDataSetFromPowderFile(String filename) {
        return PowderFileCabinet.createDataSetFromPowderFile(new File(filename));
    }

    public static DataSet createDataSetFromPowderFile(File aFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(aFile);
            //BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            //int lineNum = 0;
            if (aFile.getName().endsWith("xye") || aFile.getName().endsWith("xy") || aFile.getName().endsWith("txt")) {
                return XYandXYE_Reader.read(aFile);
            }
            if (aFile.getName().endsWith("cif")) {
                return Cif_Reader.read(aFile);
            }
            return null;
        } catch (Exception ex) {
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(PowderFileCabinet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Checking whether file type is allowed
     * @param filenames
     **/
    public boolean checkAcceptedFileType(String filenames) {
        System.out.println("\nFile Type = " + filenames);
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

//  public static void main(String args[]) {
//    PowderFileCabinet pdc = new PowderFileCabinet();
//
//    javax.swing.JFrame frame = new javax.swing.JFrame("PowderFileCabinet Sample");
//    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//    frame.add(pdc, java.awt.BorderLayout.NORTH);
//    frame.setSize(300, 100);
//    frame.setVisible(true);
//
//    pdc.loadFiles();
//  }
    //@return the last file inserted.
    public String getLastUpdateFileName() {
        return lastUpdateFileName;
    }

    public void setLastUpdateFileName(String fileName) {
        this.lastUpdateFileName = fileName;
    }
}

