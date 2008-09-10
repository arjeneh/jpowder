package org.jpowder.dataset;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.9789A94C-A49D-16BD-8F04-BBCA545F0367]
// </editor-fold> 
public abstract class DatasetPlotter {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6B64DDC3-0BC8-8DCF-1B39-EABFF36FA9AE]
    // </editor-fold> 
    protected DataSet dataset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.77F2C89B-DB68-1547-E8BC-7F54B27EBCD6]
    // </editor-fold> 
    public DatasetPlotter (DataSet d) { }
    
    public abstract String description ();
    
    //create chart of all kinds.
    public abstract org.jfree.chart.ChartPanel createPowderChart();

}

