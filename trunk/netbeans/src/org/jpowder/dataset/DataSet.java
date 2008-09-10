package org.jpowder.dataset;

import java.util.Vector;
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.003716EA-7431-660C-E6ED-A19156BA2BF9]
// </editor-fold> 
public abstract class DataSet {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.37E21C90-620F-1D10-58FE-B2AA803BD3FE]
    // </editor-fold> 
    private Vector x;    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3143988D-916D-A211-9A55-1068521B1839]
    // </editor-fold> 
    private Vector y;    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2E9728AE-AE9C-26D3-3A34-5DF85D5CD953]
    // </editor-fold> 
    private String fileName;
    private Vector data;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8B592675-9A00-0983-0DB1-96B4799F5A7B]
    // </editor-fold> 
    public DataSet(Vector data, String filename) {
        this.fileName = filename;
        this.data = data;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BCD03A9C-A6EE-9630-1597-E2AAB1B183C2]
    // </editor-fold> 
    public abstract DatasetPlotter createDatasetPlotter();

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.371FDA94-F181-89D1-D3A1-E38C54779C32]
    // </editor-fold> 
    public abstract String description();
    
    public Vector getData(){
        return this.data;
    }
    public String getFileName(){
        return this.fileName;
    }
}

