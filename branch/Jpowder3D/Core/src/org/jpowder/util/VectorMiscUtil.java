/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;

/**
 *
 * @author Kreecha
 */
public class VectorMiscUtil {

    /** 
     * Create XYE dataset for testing purpose.
     * */
    /**
     * Create XYE dataset for testing purpose.
     * */
    public static Vector initXYEData() {
        Vector row1 = new Vector();
        row1.add(1.000);
        row1.add(1.863);
        row1.add(1.82657);

        Vector row2 = new Vector();
        row2.add(2.005);
        row2.add(2.572);
        row2.add(2.43149);

        Vector row3 = new Vector();
        row3.add(3.010);
        row3.add(3.015);
        row3.add(3.85440);

        Vector row4 = new Vector();
        row4.add(4);
        row4.add(4.5);
        row4.add(4.6);

        Vector row5 = new Vector();
        row5.add(5);
        row5.add(5.5);
        row5.add(5.6);

        Vector row6 = new Vector();
        row6.add(6);
        row6.add(6.5);
        row6.add(6.6);

        Vector row7 = new Vector();
        row7.add(7);
        row7.add(7.5);
        row7.add(7.6);

        Vector data = new Vector();
        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        data.add(row5);
        data.add(row6);
        data.add(row7);

        return data;
    }

    public static void main(String[] args) {
        Vector files = VectorMiscUtil.loadFileData("C:\\Users\\Toshiba\\Desktop\\ZopDih");

        Vector<DataSet> datasets = null;

        for (int i = 0, n = files.size(); i < n; i++) {
            datasets = createDataSetFromPowderFile((String) files.get(i));
        }
    }

    public static Vector<DataSet> createDataSetFromPowderFile(String filename) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);

        } catch (Exception ex) {
            System.out.println(ex);
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Can't process (1) file " + filename);
        }
        return createDataSetFromPowderFile(fis, new File(filename));
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

    //Vector<Vector<DataSet>>
    public static Vector loadFileData(String aPath) {
        Vector dataSets = new Vector();

        String path = aPath;
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();
                dataSets.add(path + "\\" + files);
                System.out.println(files);
            }
        }

        return dataSets;
    }

    public static DataSet read(FileInputStream fileInputStream, File aFile) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("First");

        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String aLine;
        boolean TOF = false;
        Vector<Vector<Double>> localData = new Vector<Vector<Double>>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            int lineNum = 0;

            while ((aLine = bufferedReader.readLine()) != null) {
                if (!aLine.isEmpty()) {
                    aLine = aLine.trim();
                    boolean validLine = false;
                    for (int i = 0; i < digits.length && !validLine; i++) {
                        if (aLine.startsWith(digits[i])) {
                            validLine = true;
                        }
                    }

                    if (validLine) {
                        lineNum++;

                        Vector<Double> newRow = new Vector<Double>();
                        StringTokenizer stringTokenizer = new StringTokenizer(aLine);
                        //
                        int numToken = stringTokenizer.countTokens();
                        for (int i = 0; i < numToken; i++) {
                            String stringToken = stringTokenizer.nextToken();
                            //System.out.println(stringToken);
                            newRow.addElement(Double.parseDouble(stringToken));
                        } //for
                        if (numToken != 0) {
                            localData.addElement(newRow);
                        } else {
                            break;
                        }
//                          }//while
//                    }//if
                    }//if

                }//if
            }//while

            fileInputStream.close();
            bufferedReader.close();

            DataSet retVal = null;

            return retVal;

        } catch (MalformedURLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file format.");
            System.out.println("Malformed URL = " + e);
            return null;
        } catch (IOException io) {
            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file format.");
            System.out.println("IOException throws " + io);
            return null;
        } catch (java.lang.NumberFormatException nfe) {

            javax.swing.JOptionPane.showMessageDialog(null, "Invalid file format.");
            System.out.println("NumberFormatException throws " + nfe);
            return null;
        }
    }

    public static Vector<DataSet> createDataSetFromPowderFile(FileInputStream fis, File aFile) {
        Vector<DataSet> retval = new Vector<DataSet>();
        try {
            if (aFile.getName().endsWith("xye") || aFile.getName().endsWith("xy") || aFile.getName().endsWith("txt")) {
                retval.addElement(read(fis, aFile));
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

    /*
     * for unit testing purposes
     * */

     public static Vector initFilePaths() {
        Vector row1 = new Vector();
        row1.add("zopdih_dehy_RampNo1_1");
        row1.add(171.863);

        Vector row2 = new Vector();
        row2.add("zopdih_dehy_RampNo1_13");
        row2.add(163.572);

        Vector row3 = new Vector();
        row3.add("zopdih_dehy_RampNo1_11");
        row3.add(211.015);

        Vector row4 = new Vector();
        row4.add("zopdih_dehy_RampNo1_2");
        row4.add(4.5);


        Vector row5 = new Vector();
        row5.add(5);
        row5.add(5.5);


        Vector row6 = new Vector();
        row6.add(6);
        row6.add(6.5);


        Vector row7 = new Vector();
        row7.add(7);
        row7.add(7.5);


        Vector data = new Vector();
        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        data.add(row5);
        data.add(row6);
        data.add(row7);
        return data;
    }

    public static Vector initXYData() {
        Vector row1 = new Vector();
        row1.add(1.000);
        row1.add(171.863);

        Vector row2 = new Vector();
        row2.add(2.005);
        row2.add(163.572);

        Vector row3 = new Vector();
        row3.add(3.010);
        row3.add(211.015);

        Vector row4 = new Vector();
        row4.add(4);
        row4.add(4.5);


        Vector row5 = new Vector();
        row5.add(5);
        row5.add(5.5);


        Vector row6 = new Vector();
        row6.add(6);
        row6.add(6.5);


        Vector row7 = new Vector();
        row7.add(7);
        row7.add(7.5);


        Vector data = new Vector();
        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        data.add(row5);
        data.add(row6);
        data.add(row7);
        return data;
    }

    /**
     * Get a specific column
     * 
     * @param inVec must be a two dimensional vector.
     * @param indexPos Is the row index
     */
    public static Vector getColumn(Vector<Vector> inVec, int indexPos) {
        Vector a = new Vector();

        for (int rowIndex = 0; rowIndex < inVec.size(); rowIndex++) {
            Vector row = (Vector) inVec.elementAt(rowIndex);
            Object x = row.elementAt(indexPos);
            a.add(x);
        }

        return a;
    }

    /** 
     * Get the last column of 2D vector, in this case we have 3 columns dataset.
     * @param ori is the vector to be extracted
     * @return the last column.
     * */
    public static Vector getLastColumnOf2DVector(Vector<Vector> ori) {
        Vector output = new Vector();
        Vector vRow = new Vector();
        Vector vLastColumn = new Vector();
        //Get the last column oof Vector.
        for (int i = 0; i < ori.size(); i++) {
            vRow = (Vector) (ori.elementAt(i));
            for (int j = 0; j < vRow.size(); j++) {
                Object s = vRow.elementAt(j);
                if (j == vRow.size() - 1) {
                    vLastColumn.add(s);
                }
            }
            output.add(vRow);
        }
        return vLastColumn;
    }

    public static Vector getAllElementOf2DVector(Vector<Vector> ori) {
        Vector output = new Vector();
        Vector vRow = new Vector();

        //Get all elements of Vector.
        for (int i = 0; i < ori.size(); i++) {
            vRow = (Vector) (ori.elementAt(i));
            for (int j = 0; j < vRow.size(); j++) {
                Double s = (Double) vRow.elementAt(j);

            }
            output.add(vRow);
        }
        return output;
    }

    public static Double findMaxElementOf1DVector(Vector ori) {
        Double obj = (Double) Collections.max(ori);
        return obj;
    }

    public static Double findMinElementOf1DVector(Vector ori) {
        Double obj = (Double) Collections.min(ori);
        return obj;
    }

    public static int countColumnsOf2DVector(Vector<Vector> ori) {
        int col = 0;
        int all = ori.size();

        for (int i = 0; i < ori.size(); i++) {
            Vector vrow = (Vector) (ori.elementAt(i));
            //Vector vCopRow = new Vector();
            col = vrow.size();
            break;
        }


        return col;

    }

    public static Vector copyBeforeLastColumnsOf2DVector(Vector<Vector> ori) {
        //Parse the data from vectors which has differnt type of objects in it
        Vector vOriginalData = ori;
        Vector vCopyTwoColumns = new Vector();

        for (int i = 0; i < vOriginalData.size(); i++) {
            Vector vrow = (Vector) (vOriginalData.elementAt(i));
            Vector vCopRow = new Vector();
            for (int j = 0; j < vrow.size() - 1; j++) {
                Object vl = vrow.elementAt(j);
                vCopRow.add(vl);
            }
            vCopyTwoColumns.add(vCopRow);
        }
        return vCopyTwoColumns;
    }

    public static Vector do_Minus_Addition_Y(Vector ori, Vector scalar) {
        double sumAdd = 0.0;
        double sumMinus = 0.0;

        Vector output = new Vector();//minus and addition results.
        Vector originValue = ori;

        for (int i = 0; i < originValue.size(); i++) {
            Vector temp = new Vector();
            Vector vrow = (Vector) (originValue.elementAt(i));

            Double y = Double.parseDouble(vrow.get(1).toString());//the constant
            Double sca = Double.parseDouble(scalar.elementAt(i).toString());

            sumMinus = y - sca;
            sumAdd = y + sca;

            temp.add(sumMinus);
            temp.add(sumAdd);
            output.add(temp);
        }//for

        //x,y, errorX_Minus_Y, errorY_add_Y
        //System.out.println(" output in do_Minus_Addition_Y = " + output);
        return output;
    }

    /** 
     * Get the last column of 2D vector, in this case we have 3 columns dataset.
     * @param v1 is the vector of two column value
     * @param v2 is the output of minus and add
     * @return vector with this format (x,y, errorX_Minus_Y, errorY_add_Y).
     * */
    public static Vector getResultOfAddingTwoVectors(Vector v1, Vector v2) {
        Vector output = new Vector();
        Vector vrow = new Vector();

        for (int i = 0; i < v1.size(); i++) {
            vrow = (Vector) (v1.elementAt(i));
            Vector vCopRowV2 = (Vector) (v2.elementAt(i));

            for (int j = 0; j < vCopRowV2.size(); j++) {
                double x = (Double) vCopRowV2.elementAt(j);//the value of output
                vrow.add(x);
            }
            output.add(vrow);
        }
        return output;
    }

    /** Check if duplicated
    @param v is the vector to be checked
     * @return true or false
     * 
     * */
    public static boolean hasDuplicates(Vector v) {
        int i = 0;
        int j = 0;
        boolean duplicates = false;

        for (i = 0; i < v.size() - 1; i++) {
            for (j = (i + 1); j < v.size(); j++) {
                if (v.elementAt(i).toString().equalsIgnoreCase(
                        v.elementAt(j).toString())) {
                    duplicates = true;
                }

            }

        }

        return duplicates;
    }

    public static Vector removeDuplicates(Vector s) {
        int i = 0;
        int j = 0;
        boolean duplicates = false;

        Vector v = new Vector();

        for (i = 0; i < s.size(); i++) {
            duplicates = false;
            for (j = (i + 1); j < s.size(); j++) {
                if (s.elementAt(i).toString().equalsIgnoreCase(
                        s.elementAt(j).toString())) {
                    duplicates = true;
                }

            }
            if (duplicates == false) {
                v.addElement(s.elementAt(i).toString().trim());
            }

        }

        return v;
    }

    public static Vector removeDuplicateDomains(Vector s) {
        int i = 0;
        int j = 0;
        boolean duplicates = false;
        String str1 = "";
        String str2 = "";

        Vector v = new Vector();

        for (i = 0; i < s.size(); i++) {
            duplicates = false;
            for (j = (i + 1); j < s.size(); j++) {
                str1 = "";
                str2 = "";
                str1 = s.elementAt(i).toString().trim();
                str2 = s.elementAt(j).toString().trim();
                if (str1.indexOf('@') > -1) {
                    str1 = str1.substring(str1.indexOf('@'));
                }
                if (str2.indexOf('@') > -1) {
                    str2 = str2.substring(str2.indexOf('@'));
                }

                if (str1.equalsIgnoreCase(str2)) {
                    duplicates = true;
                }

            }
            if (duplicates == false) {
                v.addElement(s.elementAt(i).toString().trim());
            }

        }

        return v;
    }

    public static boolean areVectorsEqual(Vector a, Vector b) {
        if (a.size() != b.size()) {
            return false;
        }

        int i = 0;
        int vectorSize = a.size();
        boolean identical = true;

        for (i = 0; i < vectorSize; i++) {
            if (!(a.elementAt(i).toString().equalsIgnoreCase(b.elementAt(i).toString()))) {
                identical = false;
            }
        }

        return identical;
    }

    public static Vector removeDuplicates(Vector a, Vector b) {

        int i = 0;
        int j = 0;
        boolean present = true;
        Vector v = new Vector();

        for (i = 0; i < a.size(); i++) {
            present = false;
            for (j = 0; j < b.size(); j++) {
                if (a.elementAt(i).toString().equalsIgnoreCase(
                        b.elementAt(j).toString())) {
                    present = true;
                }
            }
            if (!(present)) {
                v.addElement(a.elementAt(i));
            }
        }

        return v;
    }
}

  

