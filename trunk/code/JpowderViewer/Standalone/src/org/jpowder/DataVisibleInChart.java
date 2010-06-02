/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * DataVisibleInChart.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *             Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
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
 * Create Jlist which contains the JcheckBox.
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

        file_sp = new JScrollPane(checkboxList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        file_sp.setPreferredSize(new java.awt.Dimension(SP_WIDTH, SP_HEIGHT));
        this.setLayout(new BorderLayout());
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
