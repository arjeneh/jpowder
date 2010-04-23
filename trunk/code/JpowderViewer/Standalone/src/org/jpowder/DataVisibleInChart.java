/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder;

import org.jpowder.JCheckboxList.JCheckBoxJList;
import org.jpowder.JCheckboxList.FileNameListModel;
import java.awt.BorderLayout;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import org.jfree.chart.plot.XYPlot;
import org.jpowder.JCheckboxList.CheckableFileItem;
import org.jpowder.dataset.DataSet;

/**
 * @author Kreecha Puphaiboon
 *    Co_Author M Arjeneh.
 */
public class DataVisibleInChart extends JPanel {

    public FileNameListModel listModel;
    private JCheckBoxJList checkboxList;
    private javax.swing.JScrollPane file_sp;
    private int SP_WIDTH=270;
    private int SP_HEIGHT=270;

    public DataVisibleInChart() {


        listModel = new FileNameListModel();

        checkboxList = new JCheckBoxJList(listModel);
        checkboxList.setLayout(new BorderLayout());
        checkboxList.setFont(new java.awt.Font("Tahoma", 0, 12));


        file_sp = new JScrollPane(checkboxList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        file_sp.setPreferredSize(new java.awt.Dimension(SP_WIDTH, SP_HEIGHT));
        add(file_sp);



    }

    /**
     * clear the selection from the checkboxlist.
     */
    public void clear() {
        listModel.clear();
      
    }


    /**
     * To inform DataVisibleInChart that a new chart has been highlighted and
     * pass it information so that DataVisibleInChart can update its checkbox list
     * @param xyplot
     * @param datasets
     */
    public void newChartInFocus(XYPlot xyplot, Vector<DataSet> dataSets) {


        clear();
        for (int i = 0; i < dataSets.size(); i++) {
            CheckableFileItem checkableFileItem = new CheckableFileItem(dataSets.elementAt(i).getFileName());
            listModel.addCheckableFile(checkableFileItem);
           
            checkableFileItem.setSelected(xyplot.getRenderer(i).isSeriesVisible(0));

        }
        checkboxList.setm_plot(xyplot);

    }
}
