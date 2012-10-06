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
 * SmoothingComboBoxModel.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.chartTools;

import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Toshiba
 */
public class SmoothingComboBoxModel extends AbstractListModel implements ComboBoxModel {

    //in case future the model requires dynamic in and out.
    private Vector<Integer> inputs = new Vector<Integer>();

    // today 18/08/2012 just array of 9 elements, as Anders wanted.
    private int[] comboInputs = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private String selection = null;

    @Override
    public Object getElementAt(int index) {
        return comboInputs[index];
    }

    @Override
    public int getSize() {
        return comboInputs.length;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (String) anItem.toString();
    } 

    // Methods implemented from the interface ComboBoxModel
    @Override
    public Object getSelectedItem() {
        return selection; // to add the selection to the combo box
    }

    /**
     *
     * @param value
     * @return
     */
    private Vector<Integer> getAllValues(Vector<Integer> value) {
        Vector<Integer> retVal = new Vector<Integer>();
        for (int i = 0; i < value.size(); i++) {
            Integer bob = value.get(i);
            retVal.add(value.get(i));

        }
        return retVal;
    }

    public static void main(String[] a) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox cbox = new JComboBox(new SmoothingComboBoxModel());
        cbox.setMaximumRowCount(5);
        frame.add(cbox);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
