/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.Analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arjeneh
 */
public class Jpowder_Reader {

    private final static String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static Vector<String> label = new Vector<String>();
    private static Vector<Vector<Double>> localData = new Vector<Vector<Double>>();


    public static void read(File aFile) {
        String aLine;


        try {
            FileInputStream fileInputStream = new FileInputStream(aFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            try {
                while ((aLine = bufferedReader.readLine()) != null) {

                    aLine = aLine.trim();

                    boolean validLine = false;

                    for (int j = 0; j < digits.length && !validLine; j++) {

                        if (aLine.startsWith(digits[j])) {
                            validLine = true;
                        }
                    }





                    if (aLine.startsWith("Num")) {
                        System.out.println(aLine);


                        StringTokenizer stringTokenizer = new StringTokenizer(aLine);
                        //

                        int numToken = stringTokenizer.countTokens();
                        for (int i = 0; i < numToken; i++) {
                            String stringToken = stringTokenizer.nextToken();
                            label.add(stringToken);
                        } //for

                    }//if



                    if (validLine) {

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


                    }
                  


                }
                fileInputStream.close();
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Jpowder_Reader.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jpowder_Reader.class.getName()).log(Level.SEVERE, null, ex);

        }


    }

    public static Vector<String> getLabel() {
        return label;
    }

    public static Vector<Vector<Double>> getLocalData() {
        return localData;
    }

}
