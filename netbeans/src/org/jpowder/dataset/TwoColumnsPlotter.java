package org.jpowder.dataset;

//

import org.jfree.chart.ChartPanel;

public class TwoColumnsPlotter extends DatasetPlotter {
    private DataSet d;

    public TwoColumnsPlotter (DataSet d) {
        super(d);
        this.d = d;
    }
    
    public String description () {
        return "Two Columns Plotter" +
                " data is " + this.d.description();
        //this.d.description();
    }

    @Override
    public ChartPanel createPowderChart() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 
}

