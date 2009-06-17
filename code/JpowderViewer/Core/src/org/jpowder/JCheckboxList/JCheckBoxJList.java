/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.JCheckboxList;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.*;
import java.io.Serializable;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**

Class name: JCheckBoxJList.java
Author: Kreecha Puphaiboon
Date: 20-Sep-2007, 13:38:48
Modf:
Description:
    Used in the FileChooserPanel.java to display a tickbox of JList which does not support.
    It presents as a list model. It also allow user to select multiple files,
    and plot a chart to display multiple dataset of files.
 * TODO: Add zebra colour to help users to see file (odd/even) .

Interface: ListSelectionListener
 * @see ListSelectionListener
 * @see JList

Return:
*/
public class JCheckBoxJList extends JList implements Serializable {
    //
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    private String sampleProperty;
    private PropertyChangeSupport propertySupport;
    //
    private DefaultListModel model;
    //Properties for striping blue and white on the list.
    private CheckFileListRenderer wrapper = null;
    //private RendererWrapper wrapper = null;
    //
    private java.awt.Color rowColors[] = new java.awt.Color[2];
    private boolean drawStripes = false;
    //
    public JCheckBoxJList(DefaultListModel model) {
        super(model);
        this.model = model;

        setCellRenderer(new CheckFileListRenderer());
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                int index = list.locationToIndex(e.getPoint());
                //Check which items are clicked. 
                CheckableFileItem item = (CheckableFileItem) list.getModel().getElementAt(index);

                item.setSelected(!item.isSelected());
                Rectangle rect = list.getCellBounds(index, index);
                list.repaint(rect);

                System.out.println("Item " + item.toString());
            }
        });

        propertySupport = new PropertyChangeSupport(this);
    }

    /*
    //Compute zebra background stripe colors. 
    private void updateZebraColors() {
        if ((rowColors[0] = getBackground()) == null) {
            rowColors[0] = rowColors[1] = java.awt.Color.white;
            return;
        }
        final java.awt.Color sel = getSelectionBackground();
        if (sel == null) {
            rowColors[1] = rowColors[0];
            return;
        }
        
        final float[] bgHSB = java.awt.Color.RGBtoHSB(
                rowColors[0].getRed(), rowColors[0].getGreen(),
                rowColors[0].getBlue(), null);
        
        final float[] selHSB = java.awt.Color.RGBtoHSB(
                sel.getRed(), sel.getGreen(), sel.getBlue(), null);
        
        rowColors[1] = java.awt.Color.getHSBColor(
                (selHSB[1] == 0.0 || selHSB[2] == 0.0) ? bgHSB[0] : selHSB[0],
                0.1f * selHSB[1] + 0.9f * bgHSB[1],
                bgHSB[2] + ((bgHSB[2] < 0.5f) ? 0.05f : -0.05f));
    }
    
    //Wrap a cell renderer to add zebra stripes behind list cells. 
    private class RendererWrapper implements javax.swing.ListCellRenderer {

        public javax.swing.ListCellRenderer ren = null;

        public java.awt.Component getListCellRendererComponent( javax.swing.JList list, Object value, 
                int index, boolean isSelected, boolean cellHasFocus) {
            
            final java.awt.Component c = ren.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            if (!isSelected && drawStripes) {
                c.setBackground(rowColors[index & 1]);
            }
            return c;
        }
    }//RendererWrapper
    
    // Return the wrapped cell renderer. 
    /*
    @Override
    public javax.swing.ListCellRenderer getCellRenderer() {
        final javax.swing.ListCellRenderer ren = super.getCellRenderer();
  
        if (ren == null) {
            return null;
        }
 
        if (wrapper == null) {
            //wrapper = new RendererWrapper();
            wrapper = new CheckFileListRenderer();
        }
        wrapper.ren = ren;
        return wrapper;
    }
    //Add zebra stripes to the background.
    @Override
    public void paintComponent(java.awt.Graphics g) {
        drawStripes = (getLayoutOrientation() == VERTICAL) && isOpaque();
        if (!drawStripes) {
            super.paintComponent(g);
            return;
        }

        // Paint zebra background stripes
        updateZebraColors();
        //
        final java.awt.Insets insets = getInsets();
        final int w = getWidth() - insets.left - insets.right;
        final int h = getHeight() - insets.top - insets.bottom;
        final int x = insets.left;
        //
        int y = insets.top;
        int nRows = 0;
        int startRow = 0;
        int rowHeight = getFixedCellHeight();
        //
        if (rowHeight > 0) {
            nRows = h / rowHeight;
        } else {
            // Paint non-uniform height rows first
            final int nItems = getModel().getSize();
            rowHeight = 17; // A default for empty lists
            for (int i = 0; i < nItems; i++, y += rowHeight) {
                rowHeight = getCellBounds(i, i).height;
                g.setColor(rowColors[i & 1]);
                g.fillRect(x, y, w, rowHeight);
            }
            // Use last row height for remainder of list area
            nRows = nItems + (insets.top + h - y) / rowHeight;
            startRow = nItems;
        }
        //
        for (int i = startRow; i < nRows; i++, y += rowHeight) {
            g.setColor(rowColors[i & 1]);
            g.fillRect(x, y, w, rowHeight);
        }
        //
        final int remainder = insets.top + h - y;
        
        if (remainder > 0) {
            g.setColor(rowColors[nRows & 1]);
            g.fillRect(x, y, w, remainder);
        }

        // Paint component
        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);
    }
    */

    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        //propertySupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        //propertySupport.removePropertyChangeListener(listener);
    }
}
