package org.jpowder.dataset;

import java.util.Vector;
import org.jpowder.util.VectorMiscUtil;

/* 
 * Creates a chart with (x,y, -y, +y).
 */

public class XYE extends DataSet {

    private Vector e;
    private Vector yLower;
    private Vector yUpper;
    private Vector data;

    public XYE(Vector data, String fileName) {
        super(data, fileName);
        
        this.data = data;
        this.e = VectorMiscUtil.getColumn(this.data, 2);

        this.yLower = new Vector();
        this.yUpper = new Vector();

        Vector twoColumn = VectorMiscUtil.copyBeforeLastColumnsOf2DVector(this.data);
        Vector outputOfMinusAdd = VectorMiscUtil.do_Minus_Addition_Y(twoColumn, this.e);
        Vector result = VectorMiscUtil.getResultOfAddingTwoVectors(twoColumn, outputOfMinusAdd);

        for (int rowIndex = 0; rowIndex < result.size(); rowIndex++) {
            Vector row = (Vector) result.elementAt(rowIndex);
            for (int colIndex = 0; colIndex < row.size(); colIndex++) {
                
                Double minusY = Double.parseDouble(row.elementAt(2).toString());  
                yLower.add(minusY);
                Double plusY = Double.parseDouble(row.elementAt(3).toString());  
                yUpper.add(plusY);

            }//end for 2
        }//end for 1

        System.out.println(" yLower is " + yLower);
        System.out.println(" y      is " + super.getY());
        System.out.println(" yUpper is " + yUpper);
    }

    public DatasetPlotter createDatasetPlotter() {
        return new ThreeColumnsPlotter(this);
    }

    public String description() {
        return "XYE data contains " + this.data;
    }

    public Vector getE() {
        return e;
    }

    public void setE(Vector e) {
        this.e = e;
    }

    public Vector getYLower() {
        return yLower;
    }

    public void setYLower(Vector yLower) {
        this.yLower = yLower;
    }

    public Vector getYUpper() {
        return yUpper;
    }

    public void setYUpper(Vector yUpper) {
        this.yUpper = yUpper;
    }
}

