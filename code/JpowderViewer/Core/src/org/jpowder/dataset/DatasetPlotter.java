package org.jpowder.dataset;

import java.util.Vector;


/**
 * Base class for (temporarely) holding the content of a powder diffraction
 * files and then create JFreeChart plots of such data
 *
 *
 */
public abstract class DatasetPlotter {

    protected DataSet dataset;

    public DatasetPlotter(DataSet d) {
    }

    public DatasetPlotter(Vector<DataSet> d) {
    }

    public abstract String description();

    /**
     * create a panel containing a chart of data of one or more powder files
     */
    public abstract org.jfree.chart.ChartPanel createPowderChart();

    /**
     * create DatasetPlotter and store the data that are can then be plotted
     * using the method createPowderChart()
     * @param datasets Contains the data of one or more powder diffraction files
     */
    public static DatasetPlotter createDatasetPlotter(Vector<DataSet> datasets) {
        if (datasets.size() >= 1) {
            return new FilesPlotter(datasets);
        } else {
            System.out.println("Can't create DatasetPlotter from no data");
            return null;
        }
    }

    /**
     * create DatasetPlotter and store the data that are can then be plotted
     * using the method createPowderChart()
     * @param datasets Contains the data of one powder diffraction file
     */
    public static DatasetPlotter createDatasetPlotter(DataSet dataset) {
        if (dataset != null) {
            return new FilesPlotter(dataset);
        } else {
            System.out.println("Can't pass null in createDatasetPlotter");
            return null;
        }
    }
}

