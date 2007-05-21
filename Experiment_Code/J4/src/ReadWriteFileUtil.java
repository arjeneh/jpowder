import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;
/*
 * ReadWriteFileUtil.java
 *
 * Created on 05 May 2007, 09:16
 *
 * This class is used to read file data from the local machine and URL.
 */

/**
 *
 * @author Kreecha Puphaiboon
 */
public class ReadWriteFileUtil {
    
    /**
     * Creates a new instance of ReadWriteFileUtil
     */
    public ReadWriteFileUtil() {
    }
    
    //Process the file information items into the table
    //return a vector which can be parsed as multi dimensional array .
    
    public static Vector getLocalFileToTable(File aFile,JPowder theframe) {
        String aLine;
        Vector data = new Vector();
        File file = aFile;
        double filter = 0;
        double compare = 0; //make sure things are number.
        JPowder frame = theframe;
        
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new  BufferedReader(new  InputStreamReader(fis));
            
            while ((aLine = br.readLine()) != null) {
                // create a vector to hold the field values
                Vector newRow = new Vector();
                StringTokenizer st2 =  new StringTokenizer(aLine);
                int numToken = st2.countTokens();
                for (int i = 0 ; i < numToken-1; i++){//ignore the last STD by minusing 1.
                    String stoken = st2.nextToken();
                    compare = Double.parseDouble(stoken);//check number or not, if yes add element
                    if(compare >= filter) {
                        newRow.addElement(stoken);
                    } else{
                        return null;
                    }
                } //for
                data.addElement(newRow);
            }//while readLine
            
            fis.close();
            br.close();
            
            return data;
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return null;
        } catch(IOException io){
            System.out.println("IOException throws " + io);
            return null;
        } catch (java.lang.NumberFormatException nfe){
            //TODO: prevent - java.lang.NumberFormatException: For input string: "xxx"
            javax.swing.JOptionPane.showMessageDialog((Component)frame, "The file contains an alphabet, we can not process.");
            System.out.println("NumberFormatException throws " + nfe);
            return null;
        }
    }//end readLocalFileToTable
    
    //For users, the example is given. It read from our net url.
    public void getURLFileToTable(java.net.URL codeURL) {
        String aLine;
        Vector data = new Vector();
        java.net.URL source = codeURL;
        
        try {
            BufferedReader br = new  BufferedReader(new  InputStreamReader(source.openStream()));
            while ((aLine = br.readLine()) != null) {
                // create a vector to hold the field values
                Vector newRow = new Vector();
                StringTokenizer st2 =  new StringTokenizer(aLine);
                int numToken = st2.countTokens();
                for (int i = 0 ; i < numToken-1; i++){//ignore the last STD by minusing 1.
                    newRow.addElement(st2.nextToken());
                } //for
                data.addElement(newRow);
            }//while readLine
            
            br.close();
            
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return;
        } catch(IOException io){
            System.out.println("IOException throws " + io);
            return;
        }
    }// readURLFileToTable()
    
    /*
     * Creates a vector fileToReadVector which holds the file names
     * recieved from JSP and applet param and value. used in init() before readURLFileToTable()
     */
    /*public void readAppletParam(){
        fileToReadVector = new java.util.Vector();
        String value;
        int i = 0;
        while ((value = getParameter("file" + i))!= null) {
            fileToReadVector.addElement(value);
            i++;
        }//end while
    }//end  readAppletParam()*/
    
    /* Use first to initialise model for table, xye data and xye files GOOD FOR EXAMPLE ONLY*/
    /*private void initURLSource(){
        fileToReadVector = new Vector();
        fileToReadVector.addElement("BM16_C6-H6-N6-O4_H2O.xye");
        try {
            //read the first xye file's data and put it in the table DataTablePanel.
            source = new java.net.URL(getCodeBase(), "data/" + (String)fileToReadVector.firstElement());
            readURLFileToTable(source);//put the vector into data vector.
            setCurrentFile(source.toString());
            model = new DataFileTableModel(data);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL = " + e);
            return;
        } //end try
    }//initURLSource*/
    
    /**
     * Change the contents of text file in its entirety, overwriting any
     * existing text.
     *
     * This style of implementation throws all exceptions to the caller.
     *
     * @param aFile is an existing file which can be written to.
     * @param aContents 
     * 
     * @throws IllegalArgumentException if param does not comply.
     * @throws FileNotFoundException if the file does not exist.
     * @throws IOException if problem encountered during write.
     */
    public static void setContents(File aFile, String aContents) throws FileNotFoundException, IOException {
        if (aFile == null) {
            throw new IllegalArgumentException("File should not be null.");
        }
        if (!aFile.exists()) { 
            throw new FileNotFoundException("File does not exist: " + aFile);
        }
        if (!aFile.isFile()) {
            throw new IllegalArgumentException("Should not be a directory: " + aFile);
        }
        if (!aFile.canWrite()) {
            throw new IllegalArgumentException("File cannot be written: " + aFile);
        }
        
        //declared here only to make visible to finally clause; generic reference
        java.io.Writer output = null;
        
        try {
            //use buffering
            //FileWriter always assumes default encoding is OK!
            output = new java.io.BufferedWriter( new java.io.FileWriter(aFile) );
            output.write( aContents );
            System.out.println("Write file successfully");
        } finally {
            //flush and close both "output" and its underlying FileWriter
            if (output != null) output.close();
        }
    }
    
    /**
     * Fetch the entire contents of a text file, and return it in a String.
     * This style of implementation does not throw Exceptions to the caller.
     *
     * @param aFile is a file which already exists and can be read.
     * @return the entire contents of a text file.
     */
    public static String getContents(File aFile) {
        //...checks on aFile are elided
        StringBuffer contents = new StringBuffer();
        
        //declared here only to make visible to finally clause
        BufferedReader input = null;
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            input = new BufferedReader( new FileReader(aFile) );
            String line = null; //not declared within while loop
      /*
       * readLine is a bit quirky :
       * it returns the content of a line MINUS the newline.
       * it returns null only for the END of the stream.
       * it returns an empty String if two newlines appear in a row.
       */
            while (( line = input.readLine()) != null){
                contents.append(line);
                contents.append(System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {
                if (input!= null) {
                    //flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return contents.toString();
    }
    
}//class

