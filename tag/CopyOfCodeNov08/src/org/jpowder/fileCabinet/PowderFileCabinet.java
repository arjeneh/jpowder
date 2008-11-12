
// File:         PowderFileCabinet.java
// Created:      2008/08/09
// Last Changed: $Date: 2008/08/09 15:15:25 $
// Author:      Kreecha Puphaiboon
//
// This code is copyright (c) 2008 Ratherford Appleton and Kasem Bundit University
// 
// History:
//  $Log: 
//  Revision 0.1  2008/08/09 15:15:25  Kreecha Puphaiboon
//  Revision 0.2  2008/08/13 14:38:16  Kreecha Puphaiboon
//              can read multiple files.
//
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

/** THIS CLASS KEEPS ALL POWDER FILES IN A HASHMAP. IT LOADS AND 
 * NOTIFIES ALL REGISTERED LISTENERS - 
 *      mPowderFileCabinet = new PowderFileCabinet();
 *      mPowderFileCabinet.registerObserver(this);
 *      mPowderFileCabinet.registerObserver((PowderFileObserver) listModel);
 * 
 * ALL LISTENER HAS TO IMPLEMENT POWDERFILEOBSERVER* 
 * 
 * @author kreecha_pu@yahoo.com Kreecha Pupahiboon
 * @version $Revision: 0.1 $ $Date: 2008/08/08 16:15:25 $ 
 * 
 * @see ACCEPTED_FILE_TYPE: List of acceptable file types 
 * @see observers: observer list
 * @see data: contain many files act as a cabinet of powder files.
 *              HashMap<String, Vector> fileName and data of the the file.
 * 
 * @see mPowderFileCabinet.loadFiles():is used outside to load the powder data.


 */
public class PowderFileCabinet extends javax.swing.JComponent implements Subject, Serializable {

    private static final String[] ACCEPTED_FILE_TYPE = {"xy", "xye", "txt"};
    private Vector<PowderFileObserver> observers = new Vector<PowderFileObserver>();
    private HashMap<String, Vector<Vector<Double>>> data = new HashMap<String, Vector<Vector<Double>>>();
    private String lastUpdateFileName;
    // TODO: to be used with the open dialog where it was last opened.
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

    /* @see  Browse for files on user machine. User needs to click Ctlr and select files.
     *  Create a list of files to be choosen and put data through
     *  this.addFile(this.eachFileName, localData);.
     *  it restricts file types to .xye */
    public void loadFiles() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);

        Vector localData = null;
        //this.lastUpdateFileName = null;
        //
        fileChooser.addChoosableFileFilter(new AcceptFileFilter(ACCEPTED_FILE_TYPE, "ASCII file (*.xye, *.txt)"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFiles[] = fileChooser.getSelectedFiles();
            for (int i = 0, n = selectedFiles.length; i < n; i++) {
                this.filePath = selectedFiles[i].getParent();
                this.lastUpdateFileName = selectedFiles[i].getName();
                localData = this.getLocalFile(selectedFiles[i], null);

                // restricts to 'xye'.
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

    //@return file data as a 2d Vector when user selected a acceptable file. 
    //@see loadFile().
    public Vector<Double> getLocalFile(File aFile, Component frame) {
        String aLine;
        Vector localData = new Vector<Double>();
        File file = aFile;
        double filter = 0;
        double compare = 0; //make sure things are number.

        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            int lineNum = 0;
            //LineNumberReader lineCounter = new LineNumberReader(br);

            //System.out.print("LineCounter.getLineNumber = " + lineCounter.getLineNumber());


            while ((aLine = br.readLine()) != null) {
                lineNum++;
                // create a vector to hold the field values
                Vector<Double> newRow = new Vector<Double>();
                StringTokenizer st2 = new StringTokenizer(aLine);
                //
                int numToken = st2.countTokens();
                //System.out.println("This file has " + numToken + " columns.");
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
            //TODO: prevent - java.lang.NumberFormatException: For input string: "xxx"//(Component) frame
            javax.swing.JOptionPane.showMessageDialog(null, "The file contains an alphabet, we can not process.");
            System.out.println("NumberFormatException throws " + nfe);
            return null;
        }
    }//end readLocalFile

    /*Checking whether file type is allowed
     *@ param filenames
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
