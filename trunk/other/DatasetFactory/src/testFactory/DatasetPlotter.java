package testFactory;

public abstract class DatasetPlotter {

    protected Dataset dataset;

    /**
     * Construct a new planner for the provided machine.
     *
     * @param Dataset the machine to plan for
     */
    public DatasetPlotter(Dataset data) {
        this.dataset = data;
    }

    public abstract String description();
    
}