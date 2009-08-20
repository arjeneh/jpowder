package org.jpowder.dataset;

import java.util.Vector;
import org.jpowder.util.VectorMiscUtil;

/**
 * Hold a powder diffraction dataset that contains errors, i.e. a powder
 * diffraction dataset with the xye format
 */
public class XYE extends DataSet {

    private Vector e;
    private Vector<Double> yLower;
    private Vector<Double> yUpper;
    private Vector data;

    public XYE(Vector data, String fileName) {
        super(data, fileName);

        this.data = data;
       this.e = VectorMiscUtil.getColumn(this.data, 2);

        this.yLower = new Vector<Double>();
        this.yUpper = new Vector<Double>();

        Vector twoColumn = VectorMiscUtil.copyBeforeLastColumnsOf2DVector(this.data);
        Vector outputOfMinusAdd = VectorMiscUtil.do_Minus_Addition_Y(twoColumn, this.e);
        Vector result = VectorMiscUtil.getResultOfAddingTwoVectors(twoColumn, outputOfMinusAdd);

        for (int rowIndex = 0; rowIndex < result.size(); rowIndex++) {
            Vector row = (Vector) result.elementAt(rowIndex);
            Double minusY = Double.parseDouble(row.elementAt(2).toString());
            yLower.add(minusY);
            Double plusY = Double.parseDouble(row.elementAt(3).toString());
            yUpper.add(plusY);
        }//end for 1

      // System.out.println(" In XYE.java yLower is " + yLower);
       // System.out.println(" In XYE.java y      is " + super.getY());
        //System.out.println(" In XYE.java yUpper is " + yUpper);
    }


    public String description() {
        return "XYE data contains " + this.data;
    }

    public Vector<Double> getE() {
        return e;
    }

    public void setE(Vector<Double> e) {
        this.e = e;
    }

    public Vector<Double> getYLower() {
        return yLower;
    }

    public void setYLower(Vector<Double> yLower) {
        this.yLower = yLower;
    }

    public Vector<Double> getYUpper() {
        return yUpper;
    }

    public void setYUpper(Vector yUpper) {
        this.yUpper = yUpper;
    }
    
  
 //   public Vector getData() {
    //    return this.data;
   // }
}

