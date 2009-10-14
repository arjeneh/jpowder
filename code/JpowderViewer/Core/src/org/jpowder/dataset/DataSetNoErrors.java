package org.jpowder.dataset;

import java.util.Vector;

/**
 * Hold a powder diffraction dataset that contains no errors, i.e. a powder
 * diffraction dataset with the xy format
 */
public class DataSetNoErrors extends DataSet {
    
    //private Vector data;

    public DataSetNoErrors (Vector data, String fileName) {
        super(data, fileName);
        //this.data = data;
    }


 //   @Override
    /// Do we really need this method
    public String description () {
        return "DataSetNoErrors contains powder data with no errors";
    }

   
   // public Vector getData() {
   //     return this.data;
   // }

}

