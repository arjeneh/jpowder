/*
 * This is just a preliminary data point container.
 * It is likely to be not fit the Jpowder purpose 
 * long term but was here just quickly added to test
 * the use of collection classes in XYEChartPanel.java
 */

package datasetfactory;

/**
 *
 * @author ajm64
 */
public class DataPoint {
    
  public double x;
  public double y;
  public double e;
  
  public DataPoint(double xIn, double yIn, double eIn)
  {
      x=xIn; y=yIn; e=eIn;
  }
  
}
