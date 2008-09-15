package datasetfactory;

import java.util.Vector;
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.59608045-5375-79A6-111B-2C9EFCB017FE]
// </editor-fold> 
public class ThreeColumnsPlotter extends DatasetPlotter {

    private DataSet d;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9BCDAAA5-48C4-DA54-051D-F87E256B8859]
    // </editor-fold> 
    public ThreeColumnsPlotter(DataSet d) {
        super(d);
        this.d = d;
    }

    public String description() {
        return "Three Columns Plotter" +
                " data is " + this.d.description();
        //this.d.description();
    }
}

