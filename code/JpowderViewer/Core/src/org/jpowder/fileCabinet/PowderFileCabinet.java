package org.jpowder.fileCabinet;

import org.jpowder.*;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JFileChooser;
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
    private static final String[] ACCEPTED_FILE_TYPE = {"xy", "xye", "txt"};
    private Vector<PowderFileObserver> observers = new Vector<PowderFileObserver>();
    private HashMap<String, Vector<Vector<Double>>> data = new HashMap<String, Vector<Vector<Double>>>();
    private String lastUpdateFileName;
    private String filePath = null;

    public PowderFileCabinet() {
    }

    // @return data as a hashMap of fileName and data.
    public HashMap getData() {
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
    public void addFile(String fileName, Vector<Vector<Double>> vdata) {
        data.put(fileName, vdata);
        System.out.println("PowderFileCabinet.java has been added with: " + data.toString());
        notifyObservers();
    }

    //@param fileName to be deleted from data HashMap.
    public void deleteFile(String fileName) {
        data.remove(fileName);
        System.out.println("PowderFileCabinet.java has been deleted: " + data.toString());
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

        Vector localData = null;

        // Set the accepted powder diffraction file extensions
        // and open a file chooser window for the user to select powder
        // diffraction file
        fileChooser.addChoosableFileFilter(new AcceptFileFilter(ACCEPTED_FILE_TYPE, "ASCII file (*.xy, *.xye, *.txt)"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // get the selected files
            File selectedFiles[] = fileChooser.getSelectedFiles();

            // to time how long it takes to read file
            Stopwatch lStopwatch = new Stopwatch();

            // loop over the selected file
            for (int i = 0, n = selectedFiles.length; i < n; i++) {
                filePath = selectedFiles[i].getParent();
                lastUpdateFileName = selectedFiles[i].getName();

                lStopwatch.start();

                localData = getLocalFile(selectedFiles[i]);

                System.out.println("\nTime it took to load " + selectedFiles[i]);
                System.out.println(lStopwatch.getElapsedTime());

                lStopwatch.reset();
                if (checkAcceptedFileType(this.getLastUpdateFileName())) {
                    if (localData != null) {
                        this.addFile(this.getLastUpdateFileName(), localData);
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Only ASCII file please.");
                }//acceptable end if extension matched
            }//for
        }//if approve
    }//loadFiles

    /**
     *  Read a powder diffraction dataset from a powder diffraction file
     *
     * @param aFile Name of the powder diffraction file to be read
     */
    public Vector<Double> getLocalFile(File aFile) {
        String aLine;
        Vector localData = new Vector<Double>();
        File file = aFile;
        double filter = 0;
        double compare = 0; //make sure things are number.

        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            int lineNum = 0;

            while ((aLine = br.readLine()) != null) {
                lineNum++;
                // create a vector to hold the field values
                Vector<Double> newRow = new Vector<Double>();
                StringTokenizer st2 = new StringTokenizer(aLine);
                //
                int numToken = st2.countTokens();
                for (int i = 0; i < numToken; i++) {
                    //ignore the last STD by minusing 1.
                    String stoken = st2.nextToken();
                    compare = Double.parseDouble(stoken);//check number or not, if yes add element
                    if (compare >= filter) {
                        newRow.addElement(Double.parseDouble(stoken));
                    } else {
                        return null;
                    }
                } //for
                localData.addElement(newRow);
            }//while readLine
            
            System.out.print("Total LineNumber is: " + lineNum);

            fis.close();
            br.close();

            return localData;
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return null;
        } catch (IOException io) {
            System.out.println("IOException throws " + io);
            return null;
        } catch (java.lang.NumberFormatException nfe) {
            javax.swing.JOptionPane.showMessageDialog(null, "The file contains an alphabet, we can not process.");
            System.out.println("NumberFormatException throws " + nfe);
            return null;
        }
    }//end readLocalFile

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
