/**
 * @(#)ReadGraph.java
 *
 *
 * @author Kreecha Puphaiboon
 * @version 1.00 2006/12/21
 *
 * @This JAVA reads file data formated as
 * @44304,AKRON
 * @00000,MENTOR
 * @00000,CLEVELAND
 */
 
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ReadGraph {

    public ReadGraph() {}
    
    public static void main(String[] args) {
    	
    	BufferedReader zipCity = null;
    	String aString = null;
    	String[][] multiArray = new String[2][100];

    	String TXTString;
    	String zip;
    	String city;
    	String inputUpper;
    	String input;
    	
    	int stringLength;
    	int divider;
    	
    	try {
    		
    		zipCity = new BufferedReader(new FileReader("inputs.txt"));
    		input = JOptionPane.showInputDialog("Enter zip or city");
    		
    		while ((aString = zipCity.readLine()) != null){
    			divider = aString.indexOf(",");
    			zip = aString.substring(0,divider);
    			stringLength = aString.length();
    			city = aString.substring(6, stringLength);
    			inputUpper = input.toUpperCase();
    			
    			if(input.equals(zip)){
    				JOptionPane.showMessageDialog(null, city);
    			} else if (inputUpper.equals(city))
    				{
    					JOptionPane.showMessageDialog(null, zip);
    				}
    				multiArray[0][0] = zip;
    				multiArray[0][1] = city;
    		}
    		
    		zipCity.close();
    	} catch (IOException e) {
            e.printStackTrace();
    	}
    }
    
}
