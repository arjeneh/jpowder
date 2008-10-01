package org.jpowder.dataset;

import java.util.ArrayList;
import java.util.Vector;
import org.jpowder.util.VectorMiscUtil;

public class XY_XYE extends DataSet {

    private Vector data;
    private final String fileTitle;
    private Vector<Vector> eachFileData;
    private Vector<String> eachFileName;
 
    
    //filenames = "[pd_0010.xy, SNBL_zeolite_VPI-9.xye]"
    //@param data = multiples files' data
    public XY_XYE(Vector data, String fileName) {
        super(data, fileName);
        this.data = data;
        this.fileTitle = fileName;
        System.out.println("File name: " + fileName);

        for (int f = 0; f < this.data.size(); f++) {
            Vector<Vector> file = new Vector<Vector>();
            file.add((Vector) this.data.elementAt(f));

            for (int r = 0; r < file.size(); r++) {
                Vector rowData = file.elementAt(r);
                System.out.println("Data of file:" + r + " contains" + rowData);
            }
        }
    }

    public DatasetPlotter createDatasetPlotter() {
        return new MultiFilesPlotter(this);
    }

    public String description() {
        return "Mix of XY and XYE datasets.";
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        Vector xy_1 = new Vector();
        Vector xy_row1 = new Vector();
        xy_row1.add(6.000);
        xy_row1.add(43.863);
        Vector xy_row2 = new Vector();
        xy_row2.add(3.005);
        xy_row2.add(163.572);

        xy_1.add(xy_row1);
        xy_1.add(xy_row2);

        Vector xye_1 = new Vector();
        Vector xye_row1 = new Vector();
        xye_row1.add(3.000);
        xye_row1.add(171.863);
        xye_row1.add(14.82657);

        Vector xye_row2 = new Vector();
        xye_row2.add(3.010);
        xye_row2.add(211.015);
        xye_row2.add(15.85440);

        xye_1.add(xye_row1);
        xye_1.add(xye_row2);

        Vector all = new Vector();
        all.add(xy_1);
        all.add(xye_1);
        System.out.println("all: " + all);

        Vector x = new Vector();
        Vector y = new Vector();

        for (int f = 0; f < all.size(); f++) {
            Vector<Vector> file = new Vector<Vector>();
            file.add((Vector) all.elementAt(f));

            for (int r = 0; r < file.size(); r++) {
                Vector row = file.elementAt(r);
                //System.out.println("row: " + row + " row size " + row.size());

                x.add(VectorMiscUtil.getColumn(row, 0));
                y.add(VectorMiscUtil.getColumn(row, 1));
            }
        //System.out.println("file: " + file + " file size " + file.size());
        }

        //System.out.println("x: " + x + " x size " + x.size());

        //XY_XYE demo = new XY_XYE(all, "pd_0010.xy, SNBL_zeolite_VPI-9.xye");

        String[] filenames = parseFileNameList("[pd_0010.xy, SNBL_zeolite_VPI-9.xye]");
        System.out.println(filenames);
        for (int i = 0; i < filenames.length; i++) {
            System.out.println("Name: " + filenames[i].trim());
        }



    }

    public static String[] parseFileNameList(String longName) {

        int namesChars = longName.length();
        //remove '[' and ']'
        String newString = longName.substring(1, namesChars - 1);

        String[] files = newString.split(",");

        for (int i = 0; i < files.length; i++) {
            System.out.println("Name: " + files[i].trim());
        }
        return files;

    }

    @Override
    public Vector getData() {
        return this.data;
    }


}

