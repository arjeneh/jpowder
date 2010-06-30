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
public class ReadTest {

 
   Vector<Vector<Double>> localData = new Vector<Vector<Double>>();
   
     public  ReadTest(File aFile) {
        String aLine;
      

        try {
            FileInputStream fileInputStream = new FileInputStream(aFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            try {
                while ((aLine = bufferedReader.readLine()) != null) {

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
                       System.out.println(localData);
                fileInputStream.close();
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Jpowder_Reader.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jpowder_Reader.class.getName()).log(Level.SEVERE, null, ex);

        }


    }
     public static void main(String[] args) {
        ReadTest readTest=new ReadTest(new File("C:/Users/Arjeneh/Desktop/Test.xy"));
    }
}
