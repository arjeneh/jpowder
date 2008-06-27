package testFactory;

public class ThreeColumnsPlotter extends DatasetPlotter {

    /**
     * Construct a new planner for a shell assembler.
     */
    public ThreeColumnsPlotter(Dataset data) {
        super(data);
    }

    @Override
    public String description() {
        return "Plotter of three columns data.";
    }

  
}