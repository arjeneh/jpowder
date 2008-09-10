package org.jpowder.dataset;

import org.jfree.chart.ChartPanel;
// #[regen=yes,id=DCE.59608045-5375-79A6-111B-2C9EFCB017FE]
// </editor-fold> 
public class MultiFilesPlotter extends DatasetPlotter {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9BCDAAA5-48C4-DA54-051D-F87E256B8859]
    // </editor-fold> 
    public MultiFilesPlotter (DataSet d) {
        super(d);
    }
    
    public String description () {
        return "Multiple Files Plotter";
    }

    @Override
    public ChartPanel createPowderChart() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

