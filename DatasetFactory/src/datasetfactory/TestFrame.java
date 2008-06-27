/*
 * TestFrame.java
 *
 * Created on 3 มิถุนายน 2551, 13:37 น.
 */
package datasetfactory;

import java.util.Vector;

/**
 *
 * @author  Kreecha
 */
public class TestFrame extends javax.swing.JFrame {

    private Vector all;//for containing data of XY and XYE

    /** Creates new form TestFrame */
    public TestFrame() {
        initComponents();

        Vector data1 = initXYData();//cant use VectorMiscutil

        DataSet xy = new XY(data1, "XY");
        DatasetPlotter plot = xy.createDatasetPlotter();
        System.out.println("\n " + plot.description());

        Vector data2 = initXYEData();//cant use VectorMiscutil has not got 4 columns
        DataSet xye = new XYE(data2, "XYE");
        DatasetPlotter plot2 = xye.createDatasetPlotter();
        System.out.println("\n " + plot2.description());

        all = new Vector();
        all.add(data1);//xy
        all.add(data2);//xye

        System.out.println("\n All has" + all);

        int fileNum = all.size();
        System.out.println("Contain " + fileNum + " file");
        
        int totalIndex= 0;
        
        file:
        for (int i = 0; i < fileNum; i++) {
            Vector file = (Vector) all.elementAt(i);
            row:
            for (int rowIndex = 0; rowIndex < file.size(); rowIndex++) {
                Vector row = (Vector) file.elementAt(rowIndex);
                column:
                for (int colIndex = 0; colIndex < row.size(); colIndex++) {
                    if (colIndex > 1) {
                        System.out.println("Plot XYE graph");
                        Double x = Double.parseDouble(row.elementAt(0).toString()); 
                        Double y = Double.parseDouble(row.elementAt(1).toString());  
                        Double minusY = Double.parseDouble(row.elementAt(2).toString());  
                        Double addY = Double.parseDouble(row.elementAt(3).toString()); 
                    }else {
                        System.out.println("Plot XY graph");
                        Double x = Double.parseDouble(row.elementAt(0).toString()); 
                        Double y = Double.parseDouble(row.elementAt(1).toString()); 
                    }
                     totalIndex++;
                }//end for column
            }//end for row

        //if 2 columns use TwoColumnsPlotter.
        //XYSeries series1 = new XYSeries(bottomTitle);
        //the name at the bottom of the chart.

        //if 3 columns use ThreeColumnsPlotter.
        //YIntervalSeries seriesX = new YIntervalSeries("Serie " + i);
        //seriesX.add(X, Y);//this can be formated as s1.add(x, y, minusY, addY);
        }//for file
        
         System.out.println("Total "  + totalIndex);
    }

    private Vector initXYEData() {

        Vector row1 = new Vector();
        row1.add(3.000);
        row1.add(171.863);
        row1.add(14.82657);
        row1.add(11.82657);

        Vector row2 = new Vector();
        row2.add(3.005);
        row2.add(163.572);
        row2.add(14.43149);
        row2.add(11.82657);

        Vector row3 = new Vector();
        row3.add(3.010);
        row3.add(211.015);
        row3.add(15.85440);
        row3.add(12.85440);

        Vector data = new Vector();
        data.add(row1);
        data.add(row2);
        data.add(row3);

        return data;
    }
    
    public Vector initXYData() {
        Vector row1 = new Vector();
        row1.add(3.000);
        row1.add(171.863);

        Vector row2 = new Vector();
        row2.add(3.005);
        row2.add(163.572);

        Vector row3 = new Vector();
        row3.add(3.010);
        row3.add(211.015);

        Vector data = new Vector();
        data.add(row1);
        data.add(row2);
        data.add(row3);
        return data;
    }
    
    private Vector initMultipleDataset() {
        Vector row1_1 = new Vector();
        row1_1.add(3.000);
        row1_1.add(171.863);
        row1_1.add(14.82657);

        Vector row1_2 = new Vector();
        row1_2.add(3.005);
        row1_2.add(163.572);
        row1_2.add(14.43149);

        Vector row1_3 = new Vector();
        row1_3.add(3.010);
        row1_3.add(211.015);
        row1_3.add(15.85440);

        Vector row2_1 = new Vector();
        row2_1.add(13.000);
        row2_1.add(151.863);
        row2_1.add(13.82657);

        Vector row2_2 = new Vector();
        row2_2.add(13.005);
        row2_2.add(173.572);
        row2_2.add(16.43149);

        Vector row2_3 = new Vector();
        row2_3.add(13.010);
        row2_3.add(241.015);
        row2_3.add(8.85440);

        Vector data1 = new Vector();
        data1.add(row1_1);
        data1.add(row1_2);
        data1.add(row1_3);

        Vector data2 = new Vector();
        data2.add(row2_1);
        data2.add(row2_2);
        data2.add(row2_3);

        Vector bigData = new Vector();
        bigData.add(data1);
        bigData.add(data2);

        return bigData;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TestFrame().setVisible(true);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
