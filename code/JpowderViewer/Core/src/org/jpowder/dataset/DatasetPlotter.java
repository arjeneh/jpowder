package org.jpowder.dataset;

import java.util.Vector;



public abstract class DatasetPlotter {

    
    protected DataSet dataset;

    public DatasetPlotter (DataSet d) { }

    public DatasetPlotter (Vector<DataSet> d) { }
    
    public abstract String description ();
    
    //create chart of all kinds.
    public abstract org.jfree.chart.ChartPanel createPowderChart();

    public static DatasetPlotter createDatasetPlotter(Vector<DataSet> datasets){
        if (datasets.size() >= 1)
          return new FilesPlotter(datasets);
        else
        {
          System.out.println("Can't create DatasetPlotter from no data");
          return null;
        }
    }

  public static DatasetPlotter createDatasetPlotter(DataSet dataset) {
     if (dataset != null)
     {
       return new FilesPlotter(dataset);
     }
     else
     {
        System.out.println("Can't pass null in createDatasetPlotter");
        return null;
     }
  }
}

