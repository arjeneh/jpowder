/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datasetfactory;

import java.util.Vector;

/**
 *
 * @author Kreecha
 */
public class VectorMiscUtil {

    /** 
     * Create XYE dataset for testing purpose.
     * */
    public static Vector initXYEData() {
        Vector row1 = new Vector();
        row1.add(3.000);
        row1.add(171.863);
        row1.add(14.82657);

        Vector row2 = new Vector();
        row2.add(3.005);
        row2.add(163.572);
        row2.add(14.43149);

        Vector row3 = new Vector();
        row3.add(3.010);
        row3.add(211.015);
        row3.add(15.85440);

        Vector data = new Vector();
        data.add(row1);
        data.add(row2);
        data.add(row3);

        return data;
    }

    public static Vector initXYData() {
        Vector row1 = new Vector();
        row1.add(3.000);
        row1.add(171.863);

        Vector row2 = new Vector();
        row2.add(3.005);
        row2.add(163.572);

        Vector row3 = new Vector();
        row3.add(3.010);
        row3.add(211.015);

        Vector data = new Vector();
        data.add(row1);
        data.add(row2);
        data.add(row3);
        return data;
    }

    /** 
     * Get the last column of 2D vector, in this case we have 3 columns dataset.
     * @param ori is the vector to be extracted
     * @return the last column.
     * */
    public static Vector getLastColumnOf2DVector(Vector ori) {
        Vector output = new Vector();
        Vector vRow = new Vector();
        Vector vLastColumn = new Vector();
        //Get the last column of Vector.
        for (int i = 0; i < ori.size(); i++) {
            vRow = (Vector) (ori.elementAt(i));
            for (int j = 0; j < vRow.size(); j++) {
                Double s = (Double) vRow.elementAt(j);
                if (j == vRow.size() - 1) {
                    vLastColumn.add(s);
                }
            }
            output.add(vRow);
        }
        return vLastColumn;
    }

    public static Vector getAllElementOf2DVector(Vector ori) {
        Vector output = new Vector();
        Vector vRow = new Vector();

        //Get all elements of Vector.
        for (int i = 0; i < ori.size(); i++) {
            vRow = (Vector) (ori.elementAt(i));
            for (int j = 0; j < vRow.size(); j++) {
                Double s = (Double) vRow.elementAt(j);
                System.out.println("Values are: " + s);
            }
            output.add(vRow);
        }
        return output;
    }

    public static Vector copyBeforeLastColumnsOf2DVector(Vector ori) {
        //Parse the data from vectors which has differnt type of objects in it
        Vector vOriginalData = ori;
        Vector vRow = new Vector();
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

            double y = (Double) vrow.get(1);//the constant
            double sca = (Double) scalar.elementAt(i);

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

  

