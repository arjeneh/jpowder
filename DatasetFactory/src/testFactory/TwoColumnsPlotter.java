package testFactory;

public class TwoColumnsPlotter extends DatasetPlotter {

    /**
     * Construct a new basic plotter.
     */
    public TwoColumnsPlotter(Dataset data) {
        super(data);
    }

    @Override
    public String description() {
        return "Plotter of two columns data.";
    }
    
}