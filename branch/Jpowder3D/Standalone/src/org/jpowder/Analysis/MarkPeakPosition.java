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
 * MarkPeakPosition.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *             Anders Markvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.Analysis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;


import javax.swing.TransferHandler;
import org.jdesktop.jxlayer.JXLayer;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.ui.TextAnchor;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.InternalFrame.JpowderInternalframe2D;
import org.jpowder.chartTools.Magnifier;

/**
 * Selecting the peak positions
 * 
 */
public class MarkPeakPosition extends javax.swing.JPanel implements InfoPanel, ChartMouseListener {

    private ToolsIcon2D toolsIcon;
    private String[] string;
    private double x;
    private double y;
    private DefaultListModel model = new DefaultListModel();

    /** Creates new form Peack */
    public MarkPeakPosition(ToolsIcon2D analysisIcon) {
        initComponents();
        this.toolsIcon = analysisIcon;
    }

    public void update() {

        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            getPeakCheckBox.setEnabled(false);
            removeButt.setEnabled(false);
            model.clear();
            return;

        }else {
            getPeakCheckBox.setEnabled(true);          
        }

        model.clear();

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        getPeakCheckBox.setSelected(inFocus.getChartPanel().getHorizontalAxisTrace());

        for (int i = 0; i < inFocus.getMarkedPeakPosition().size(); i++) {
            model.addElement(inFocus.getMarkedPeakPosition().elementAt(i));

        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        peakListPopMenu = new javax.swing.JPopupMenu();
        copy = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        backButton = new javax.swing.JButton();
        peakLabel = new javax.swing.JLabel();
        removeAllButt = new javax.swing.JButton();
        removeButt = new javax.swing.JButton();
        getPeakCheckBox = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        peakList = new javax.swing.JList(model);

        copy.setText("Copy");
        copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyActionPerformed(evt);
            }
        });
        peakListPopMenu.add(copy);
        peakListPopMenu.add(new JPopupMenu.Separator());

        setMaximumSize(new java.awt.Dimension(274, 362));
        setPreferredSize(new java.awt.Dimension(320, 420));

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Back.PNG"))); // NOI18N
        backButton.setText("Back");
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        backButton.setIconTextGap(2);
        backButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        peakLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Peaks_Large.png"))); // NOI18N

        removeAllButt.setText("Remove All");
        removeAllButt.setToolTipText("Clear All The Peaks.");
        removeAllButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllButtActionPerformed(evt);
            }
        });

        removeButt.setText("Remove");
        removeButt.setToolTipText("Remove Selected Item.");
        removeButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtActionPerformed(evt);
            }
        });

        getPeakCheckBox.setText("Get Peak.");
        getPeakCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPeakCheckBoxActionPerformed(evt);
            }
        });

        peakList.setToolTipText("To Copy Right Click.");
        peakList.setDragEnabled(true);
        peakList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                peakListMouseClicked(evt);
            }
        });
        peakList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                peakListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(peakList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(peakLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(removeAllButt)
                        .addGap(0, 0, 0)
                        .addComponent(removeButt))
                    .addComponent(getPeakCheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {backButton, removeAllButt, removeButt});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(peakLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(getPeakCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeAllButt)
                    .addComponent(removeButt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {backButton, removeAllButt, removeButt});

    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
            
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() > 0) {
            JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
            JXLayer layer = new JXLayer(inFocus.getChartPanel());
            Magnifier magnifier = new Magnifier();
            magnifier.setRadius(0);
            layer.setUI(magnifier);
            inFocus.setContentPane(layer);           
        }

      // To force the tick box to be 'not' ticked from start
         if (getPeakCheckBox.isSelected()) {             
             getPeakCheckBox.doClick();
         }

        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed
    /**
     * this method converts vectors of doubles to strings
     * @param peak
     * @return string
     */
    private String[] convertDoubleToString(Vector<Double> peak) {
        string = new String[peak.size()];
        for (int i = 0; i < peak.size(); i++) {
            string[i] = Double.toString(peak.get(i));

        }
        return string;
    }
    private void copyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyActionPerformed

        TransferHandler transferHandler = peakList.getTransferHandler();
        if (transferHandler != null) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            transferHandler.exportToClipboard(peakList, clipboard, TransferHandler.COPY);
        }

    }//GEN-LAST:event_copyActionPerformed
    /**
     *
     * @param arg0
     */
    public void chartMouseClicked(ChartMouseEvent arg0) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
                inFocus.getXYPlot().clearAnnotations();

                x = inFocus.getXYPlot().getDomainCrosshairValue();
                y = inFocus.getXYPlot().getRangeCrosshairValue();

                // if domain marker position has been selected previously don't add
                // a new marker
                Iterator itr = inFocus.getMarkedPeakPosition().iterator();
                while(itr.hasNext()) {
                    Object element = itr.next();

                  if (element.equals(x))
                  {
                    return;
                  }
                }

                inFocus.addMarkedPeakPosition(x);
                final String[] strings = convertDoubleToString(inFocus.getMarkedPeakPosition());

                model.clear();
                for (int i = 0; i < strings.length; i++) {
                    model.addElement(strings[i]);
                }
                updatePeaksInPlot();

            }
        });
    }

    public void updatePeaksInPlot() {

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        XYTextAnnotation annotation = new XYTextAnnotation("(" + x + ", " + y + ")", x, y);
        annotation.setTextAnchor(TextAnchor.BOTTOM_CENTER);
        inFocus.getXYPlot().addAnnotation(annotation);

//        ValueMarker rangeMarker = new ValueMarker(y);
//        rangeMarker.setStroke(new BasicStroke(1.4f));
//        rangeMarker.setPaint(Color.yellow);

        ValueMarker domainMarker = new ValueMarker(x);
        domainMarker.setStroke(new BasicStroke(1.4f));
        domainMarker.setPaint(Color.yellow);

        inFocus.getPeakDomainMarker().add(domainMarker);
        inFocus.getXYPlot().addDomainMarker(domainMarker);
    }

    /**
     *
     * @param arg0
     */
    public void chartMouseMoved(ChartMouseEvent arg0) {
    }


    private void removeAllButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllButtActionPerformed
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            removeAllButt.setSelected(false);
            return;
        }else if(peakList.getModel().getSize()==0){
            return;
        }

        removePeaksPosition();
    }//GEN-LAST:event_removeAllButtActionPerformed

    private void removeButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtActionPerformed

        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            removeButt.setSelected(false);
            return;
        }
        else if(peakList.getModel().getSize()==0){
            return;
        }



        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        inFocus.getXYPlot().removeDomainMarker(inFocus.getPeakDomainMarker().get(peakList.getSelectedIndex()));

        inFocus.getMarkedPeakPosition().removeElementAt(peakList.getSelectedIndex());
        inFocus.getPeakDomainMarker().remove(peakList.getSelectedIndex());


        inFocus.getXYPlot().clearAnnotations();

        model.removeElementAt(peakList.getSelectedIndex());


    }//GEN-LAST:event_removeButtActionPerformed

    private void getPeakCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getPeakCheckBoxActionPerformed

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            getPeakCheckBox.setSelected(false);
            return;
        }
        if (getPeakCheckBox.isSelected()) {



            inFocus.getChartPanel().addChartMouseListener(this);
            inFocus.getChartPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            //Domain

            inFocus.getChartPanel().setHorizontalAxisTrace(true);

            //Range

            inFocus.getChartPanel().setVerticalAxisTrace(true);



            JXLayer layer = new JXLayer(inFocus.getChartPanel());
            Magnifier magnifier = new Magnifier();
            magnifier.setMagnifyingFactor(4);
            magnifier.setRadius(20);
            layer.setUI(magnifier);
            inFocus.setContentPane(layer);

        }
        if (!getPeakCheckBox.isSelected()) {
            inFocus.getChartPanel().removeChartMouseListener(this);
            inFocus.getChartPanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            //Domain

            inFocus.getChartPanel().setHorizontalAxisTrace(false);


//            for (int i = 0; i < inFocus.getPeakDomainMarker().size(); i++) {
////                inFocus.getXYPlot().removeRangeMarker(peakRangeMarker.get(i));
//                inFocus.getXYPlot().removeDomainMarker(inFocus.getPeakDomainMarker().get(i));
//            }
            //Range

            inFocus.getChartPanel().setVerticalAxisTrace(false);

            // Anotation
            inFocus.getXYPlot().clearAnnotations();
            // magnifier
            JXLayer layer = new JXLayer(inFocus.getChartPanel());
            Magnifier magnifier = new Magnifier();
            magnifier.setRadius(0);
            layer.setUI(magnifier);
            inFocus.setContentPane(layer);

        }
    }//GEN-LAST:event_getPeakCheckBoxActionPerformed

    private void peakListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peakListMouseClicked
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            getPeakCheckBox.setSelected(false);
            return;
        }
        for (int i = 0; i < inFocus.getPeakDomainMarker().size(); i++) {
            inFocus.getPeakDomainMarker().get(i).setPaint(Color.YELLOW);
            inFocus.getPeakDomainMarker().get(i).setStroke(new BasicStroke(1.4f));
        }
        int s = peakList.getSelectedIndex();

        inFocus.getPeakDomainMarker().get(s).setPaint(Color.RED);
        inFocus.getPeakDomainMarker().get(s).setStroke(new BasicStroke(1.8f));

        if (SwingUtilities.isRightMouseButton(evt)) {
            peakListPopMenu.show(peakList, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_peakListMouseClicked

    private void peakListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_peakListValueChanged
        if (evt.getValueIsAdjusting() == false) {

            if (peakList.getSelectedIndex() == -1) {
                //No selection, disable remove button.
                removeButt.setEnabled(false);

            } else {
                //Selection, enable the remove button.
                removeButt.setEnabled(true);
            }
        }
    }//GEN-LAST:event_peakListValueChanged

    public void removePeaksPosition() {


        model.clear();
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        inFocus.removeAllMarkedPeakPosition();
        inFocus.getChartPanel().removeChartMouseListener(this);
        inFocus.getChartPanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        //Domain
        inFocus.getChartPanel().setHorizontalAxisTrace(false);
        inFocus.getChartPanel().setVerticalAxisTrace(false);


        for (int i = 0; i < inFocus.getPeakDomainMarker().size(); i++) {
            inFocus.getXYPlot().removeDomainMarker(inFocus.getPeakDomainMarker().get(i));
        }
        //Range
        inFocus.getPeakDomainMarker().clear();

        inFocus.getChartPanel().setVerticalAxisTrace(false);

        //Anotation
        inFocus.getXYPlot().clearAnnotations();
        //magnifier
        JXLayer layer = new JXLayer(inFocus.getChartPanel());
        Magnifier magnifier = new Magnifier();
        magnifier.setRadius(0);
        layer.setUI(magnifier);
        inFocus.setContentPane(layer);

        getPeakCheckBox.setSelected(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JMenuItem copy;
    private javax.swing.JCheckBox getPeakCheckBox;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel peakLabel;
    private javax.swing.JList peakList;
    private javax.swing.JPopupMenu peakListPopMenu;
    private javax.swing.JButton removeAllButt;
    private javax.swing.JButton removeButt;
    // End of variables declaration//GEN-END:variables

    
}

