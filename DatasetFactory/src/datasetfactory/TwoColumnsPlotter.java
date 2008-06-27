package datasetfactory;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.4404B4D2-543D-98CE-D101-4955912CA3E8]
// </editor-fold> 
public class TwoColumnsPlotter extends DatasetPlotter {
    private DataSet d;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.14467CC1-3224-F4DA-0E8F-F362F45F4DA8]
    // </editor-fold> 
    public TwoColumnsPlotter (DataSet d) {
        super(d);
        this.d = d;
    }
    
    public String description () {
        return "Two Columns Plotter" +
                " data is " + this.d.description();
        //this.d.description();
    }
}

