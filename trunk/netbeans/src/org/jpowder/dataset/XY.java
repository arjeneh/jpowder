package org.jpowder.dataset;

import java.util.Vector;
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.27F91676-CFD9-0E53-99B0-F2A416ADDAAF]
// </editor-fold> 
public class XY extends DataSet {
    
    private Vector data;

    public XY (Vector data, String fileName) {
        super(data, fileName);
        this.data = data;
    }

    @Override
    public DatasetPlotter createDatasetPlotter () {
        return new TwoColumnsPlotter(this);
    }

    @Override
    public String description () {
        return "XY data contains = " + data;
    }

    @Override
    public Vector getData() {
        return this.data;
    }

}

