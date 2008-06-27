package testFactory;

import java.util.Vector;

public abstract class Dataset {

    private DatasetPlotter plotter;
    private Vector x;
    private Vector y;

    public abstract DatasetPlotter createChart();

    public abstract String description();
}