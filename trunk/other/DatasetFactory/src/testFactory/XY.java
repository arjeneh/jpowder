package testFactory;

import java.util.Vector;

public class XY extends Dataset {

    Vector data;
    String fileName;

    public XY(Vector data, String fileName) {
        this.data = data;
        this.fileName = fileName;
    }


    @Override
    public DatasetPlotter createChart() {
        return new TwoColumnsPlotter(this);
    }

    public String description() {
        return "XY";
    }
}