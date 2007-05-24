/**
 * @(#)ReadFile.java
 *
 *
 * @author Kreecha Puphaiboon
 * @version 1.00 2006/12/21
 */

import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ReadFile {

    public ReadFile() {
    	
    }
    
    public static void main(String[] args) throws IOException {
    	
        FileInputStream in = null;
        FileOutputStream out = null;
        
        try {
            in = new FileInputStream("inputs.txt");
            out = new FileOutputStream("outputs.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);//write to a file.
         		Vector row = new Vector();// create a vector to hold the field values
         
         		/* tokenize line into field values
         		//StringTokenizer st = new StringTokenizer(c, "|");
         		//while (st.hasMoreTokens()) {
         			//add field to the row
         			//row.addElement(st.nextToken());
         		}*/
            }
            
        } catch (IOException ioExc){
        	ioExc.printStackTrace();
        } finally {
        	
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}

