package datasetfactory;

import java.util.Vector;
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.27F91676-CFD9-0E53-99B0-F2A416ADDAAF]
// </editor-fold> 
public class XY extends DataSet {
    
    private Vector data;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.038D4ACA-0188-72C5-3B65-93499A486101]
    // </editor-fold> 
    public XY (Vector data, String fileName) {
        super(data, fileName);
        this.data = data;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D0F54369-466A-FCC8-720C-9E246C3FDA78]
    // </editor-fold> 
    public DatasetPlotter createDatasetPlotter () {
        return new TwoColumnsPlotter(this);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0913E10F-C83D-544D-44EB-86D21DEBDB9C]
    // </editor-fold> 
    public String description () {
        return "XY" + data;
    }

}

