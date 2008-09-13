package org.jpowder.dataset;

import org.jfree.chart.ChartPanel;

public class MultiFilesPlotter extends DatasetPlotter {

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

