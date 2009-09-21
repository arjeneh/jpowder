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
        if (datasets.size() > 1)
          return new MultiFilesPlotter(datasets);
        if (datasets.size() == 1)
        {
          // does the dataset contain 2 or 3 columns
          DataSet lDataset = datasets.elementAt(0);

          //if (lDataset instanceof XY)
          if (lDataset.hasErrorbars)
            return new ThreeColumnsPlotter(lDataset);
          else
            return new TwoColumnsPlotter(lDataset);
          //if (lDataset instanceof XYE)

          //else
          //{
          //  System.out.println("Not a recognised dataSet type");
          //  return null;
         // }
        }
        else
        {
          System.out.println("Can't create DatasetPlotter from no data");
          return null;
        }
    }

  public static DatasetPlotter createDatasetPlotter(DataSet dataset) {
    if (dataset instanceof XY) {
      return new TwoColumnsPlotter(dataset);
    }
    if (dataset instanceof XYE) {
      return new ThreeColumnsPlotter(dataset);
    } else {
      System.out.println("Not a recognised dataset type");
      return null;
    }
  }
}

