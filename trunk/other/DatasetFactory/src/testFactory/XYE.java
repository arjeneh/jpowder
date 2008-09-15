package testFactory;

import java.util.Vector;

public class XYE extends Dataset {

    private Vector e;
    private Vector yLower;
    private Vector yUpper;

    /**
     * Return a Three Columns Plotter, since our XYE are pretty
     * predictable 3 columns.
     * 
     * @return a Three Columns Plotter
     */
    @Override
    public DatasetPlotter createChart() {
        return new ThreeColumnsPlotter(this);
    }

    public String description() {
        return "XYE";
    }
}