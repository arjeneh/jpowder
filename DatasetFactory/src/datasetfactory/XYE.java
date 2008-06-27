package datasetfactory;

import java.util.Vector;
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D48C3641-7DCA-878C-C8B1-8761BA038DD2]
// </editor-fold> 
public class XYE extends DataSet {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D3857DC5-63F0-201E-701F-2ADCF08B922F]
    // </editor-fold> 
    private Vector e;    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.122193B7-02D0-5B76-E671-5FCEAF235530]
    // </editor-fold> 
    private Vector yLower;    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.37C4E71E-C50E-3A5A-8A4B-A174F3272425]
    // </editor-fold> 
    private Vector yUpper;
    
    private Vector data;
    

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FCAD2B57-198A-5333-28D5-52FBBE520F13]
    // </editor-fold> 
    public XYE(Vector data, String fileName) {
        super(data, fileName);
        this.data = data;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D44916A1-62A1-ECF7-AC99-C42843DC1747]
    // </editor-fold> 
    public DatasetPlotter createDatasetPlotter() {
        return new ThreeColumnsPlotter(this);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C1D8AEE7-9292-A49B-B4F2-BF61A0AE2774]
    // </editor-fold> 
    public String description() {
        return "XYE" + transformErrorOnY(this.data).toString();
    }

    public Vector transformErrorOnY(Vector xyeData) {
        Vector last = VectorMiscUtil.getLastColumnOf2DVector(xyeData);

        Vector twoColumn =
                VectorMiscUtil.copyBeforeLastColumnsOf2DVector(xyeData);
        Vector outputOfMinusAdd =
                VectorMiscUtil.do_Minus_Addition_Y(twoColumn, last);
        Vector result =
                VectorMiscUtil.getResultOfAddingTwoVectors(twoColumn, 
                outputOfMinusAdd);

        return result;
    }
}

