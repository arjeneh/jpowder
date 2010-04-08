/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChangePlotStyle.java
 *
 * Created on 10-Dec-2009, 14:13:58
 */
package org.jpowder.chartTools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.util.ShapeUtilities;
import org.jpowder.Analysis.ToolsIcon;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.JpowderInternalframe;
import org.jpowder.dataset.DataSetNoErrors;
import org.jpowder.dataset.DataSetWithErrors;
import org.jpowder.jfreechart.FilesPlotter;

/**
 *
 * @author qyt21516
 */
public class ChangePlotStyle extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon toolsIcon;
    /*Array of string which contains the all the file names that have been plotted. */
    private String[] string;

    /**
     *
     * @param chartToolsIcon
     */
    public ChangePlotStyle(ToolsIcon analysisIcon) {
        initComponents();
        this.toolsIcon = analysisIcon;

    }

    /**
     *
     * @return changePlotStyleLabel.
     */
    public JLabel getChangePlotStyleLabel() {
        return changePlotStyleLabel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        conectingLines = new javax.swing.JCheckBox();
        errorBars = new javax.swing.JCheckBox();
        backButton = new javax.swing.JButton();
        changePlotStyleLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        markersTypePanel = new javax.swing.JPanel();
        dataSetComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        markerSizeField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        shapesComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        automaticRadioButton = new javax.swing.JRadioButton();
        noneRadioButton = new javax.swing.JRadioButton();
        buitInRadioButton = new javax.swing.JRadioButton();

        jLabel2.setText("jLabel2");

        conectingLines.setSelected(true);
        conectingLines.setText("Turn on the connecting lines");
        conectingLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectingLinesActionPerformed(evt);
            }
        });

        errorBars.setSelected(true);
        errorBars.setText("Turn on the error bars");
        errorBars.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorBarsActionPerformed(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Back.PNG"))); // NOI18N
        backButton.setText("Back");
        backButton.setAlignmentY(0.0F);
        backButton.setBorderPainted(false);
        backButton.setFocusable(false);
        backButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        backButton.setIconTextGap(2);
        backButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        changePlotStyleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Appearance_Large.png"))); // NOI18N
        changePlotStyleLabel.setPreferredSize(new java.awt.Dimension(300, 144));

        jLabel1.setText("Change appearance of plots in a chart.");

        markersTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Marker Type"));

        dataSetComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSetComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Markers:");

        markerSizeField.setText("3");

        jLabel5.setText("Size:");

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        shapesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "Fill Square", "Square", "Fill Circle", "Circle", "Fill Oval", "Oval", "Fill Diamond", "Diamon", "Fill DownTriangle", "DownTriangle", "Fill UpTriangle", "UpTriangle", "Fill Rectangle", "Rectangle", " " }));

        jLabel3.setText("Plot(s):");

        buttonGroup1.add(automaticRadioButton);
        automaticRadioButton.setText("Automatic");
        automaticRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                automaticRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(noneRadioButton);
        noneRadioButton.setText("None");
        noneRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noneRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(buitInRadioButton);
        buitInRadioButton.setText("Built In");
        buitInRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buitInRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout markersTypePanelLayout = new javax.swing.GroupLayout(markersTypePanel);
        markersTypePanel.setLayout(markersTypePanelLayout);
        markersTypePanelLayout.setHorizontalGroup(
            markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, markersTypePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buitInRadioButton)
                    .addComponent(noneRadioButton)
                    .addComponent(automaticRadioButton)
                    .addComponent(dataSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(markersTypePanelLayout.createSequentialGroup()
                        .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shapesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(applyButton))
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(markerSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        markersTypePanelLayout.setVerticalGroup(
            markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, markersTypePanelLayout.createSequentialGroup()
                .addComponent(automaticRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noneRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buitInRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dataSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shapesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(markerSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(applyButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changePlotStyleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(conectingLines)
                            .addComponent(errorBars)
                            .addComponent(markersTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changePlotStyleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conectingLines)
                .addGap(3, 3, 3)
                .addComponent(errorBars)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(markersTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * this is an update method which updates this info Panel.
     */
    public void update() {

     JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        setMarkersBuidInEnableFalse();
 
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() != 0) {
            dataSetComboBox.setModel(new javax.swing.DefaultComboBoxModel(addDataSet()));
        }
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            String labels[] = {"No Chart Added"};
            dataSetComboBox.setModel(new javax.swing.DefaultComboBoxModel(labels));
            return;
        }
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer();

   
        if (inFocus.getPowderDataSet().get(0) instanceof DataSetWithErrors) {
            XYErrorRenderer renderer1 = (XYErrorRenderer) inFocus.getXYPlot().getRenderer();
            errorBars.setSelected(renderer1.getDrawYError());


        }
        conectingLines.setSelected(renderer.getBaseLinesVisible());

        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            errorBars.setSelected(false);
            conectingLines.setSelected(false);

        }
        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {

            if (inFocus.getPowderDataSet().get(i) instanceof DataSetNoErrors) {
                errorBars.setEnabled(false);
            }
            if (inFocus.getPowderDataSet().get(i) instanceof DataSetWithErrors) {
                errorBars.setEnabled(true);

            }
        }
        if(renderer.getBaseShapesVisible()){
             automaticRadioButton.setSelected(true);
//            if(automaticRadioButton.isSelected()){
//           automaticRadioButton.setSelected(true);
//
//            }
//            if(buitInRadioButton.isSelected()){
//                buitInRadioButton.setSelected(true);
//            }
        }else if(!renderer.getBaseShapesVisible()){
            noneRadioButton.setSelected(true);
        }

    }

    /**
     * this method get all the plotted files name and add them to the comboBox  and
     * also, render to the comboBox so each indivdual file names has the series color
     * wich was plotted in.
     *
     * @return array of strings which contains all the files which has been plotted.
     */
    public String[] addDataSet() {
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        int size = inFocus.getXYPlot().getDatasetCount();
        string = new String[size];
//        string[0] = "Every";
        for (int i = 0; i < size; i++) {

            string[i] = inFocus.getPowderDataSet().elementAt(i).getFileName();

            dataSetComboBox.setRenderer(new ListCellRenderer() {

                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {


                    JLabel j = new JLabel(value.toString());
                    j.setOpaque(isSelected);
                    if (index >= 0) {
                        j.setForeground((Color) FilesPlotter.allSeriescolors[index]);
                    }
                    if (isSelected) {
                        j.setBorder(LineBorder.createBlackLineBorder());

                    }
                    return j;

                }
            });
        }
        return string;
    }

    /**
     * 
     */
    public void setMarkersBuidInEnableFalse() {
        applyButton.setEnabled(false);
        markerSizeField.setEnabled(false);
        shapesComboBox.setEnabled(false);
        dataSetComboBox.setEnabled(false);
    }

    public void setMarkersBuidInEnableTrue() {
        applyButton.setEnabled(true);
        markerSizeField.setEnabled(true);
        shapesComboBox.setEnabled(true);
        dataSetComboBox.setEnabled(true);
    }

    /**
     * this methods turns the the connecting lines in the plotted data on and off.
     * @param evt
     */
    private void conectingLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectingLinesActionPerformed
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
            boolean status = renderer.getBaseLinesVisible();
            renderer.setBaseLinesVisible(!status);
        }
    }//GEN-LAST:event_conectingLinesActionPerformed
    /**
     * this method turns the error bars int he cif and xye data on and off.
     * @param evt
     */
    private void errorBarsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorBarsActionPerformed
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {

            if (inFocus.getPowderDataSet().get(i) instanceof DataSetWithErrors) {
                XYErrorRenderer renderer = (XYErrorRenderer) inFocus.getXYPlot().getRenderer(i);
                boolean status = renderer.getDrawYError();
                renderer.setDrawYError(!status);//show opposite Y error bar.
                renderer.setDrawXError(!status);
                renderer.setErrorStroke(new BasicStroke(1f));
            }
        }
    }//GEN-LAST:event_errorBarsActionPerformed
    /**
     * return to the chartToolsPanel.
     * @param evt
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        toolsIcon.add(this);
        toolsIcon.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_backButtonActionPerformed

    private void dataSetComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSetComboBoxActionPerformed
}//GEN-LAST:event_dataSetComboBoxActionPerformed
    /**
     * this method responsible for gettign action of the comboBoxes and textFied and
     * and appling to the plotted data in the internalframe whcih is in focus.
     * @param evt
     */
    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
//        if (dataSetComboBox.getSelectedItem().toString().equals("ALL") && shapesComboBox.getSelectedItem().toString().equals("None")) {
//            for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {
//                XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
//                renderer.setBaseShapesVisible(false);
//
//            }
//        }

        int seriescount = inFocus.getXYPlot().getDatasetCount();
        for (int i = 0; i < seriescount; i++) {

            if (inFocus.getPowderDataSet().elementAt(i).getFileName().equals(
                    dataSetComboBox.getSelectedItem())) {
                try {
                    double dble = Double.parseDouble(markerSizeField.getText());
                    float flot = Float.valueOf(markerSizeField.getText());

                    XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
                    renderer.setBaseShapesVisible(true);

                    if (shapesComboBox.getSelectedItem().toString().equals("None")) {
                        renderer.setBaseShapesVisible(false);

                    }

                    if (shapesComboBox.getSelectedItem().toString().equals("Square")) {
                        renderer.setSeriesShape(0, new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble));
                        renderer.setBaseShapesFilled(false);

                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Square")) {
                        renderer.setSeriesShape(0, new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble));
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Circle")) {
                        renderer.setSeriesShape(0, new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble));
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Circle")) {
                        renderer.setSeriesShape(0, new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Oval")) {
                        renderer.setSeriesShape(0, new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble / 2));
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Oval")) {
                        renderer.setSeriesShape(0, new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble / 2));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Diamond")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createDiamond(flot));
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Diamond")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createDiamond(flot));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill DownTriangle")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createDownTriangle(flot));
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("DownTriangle")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createDownTriangle(flot));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill UpTriangle")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createUpTriangle(flot));
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("UpTriangle")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createUpTriangle(flot));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Rectangle")) {
                        renderer.setSeriesShape(0, new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble / 2));
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Rectangle")) {
                        renderer.setSeriesShape(0, new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble / 2));
                        renderer.setBaseShapesFilled(false);
                    }
                } catch (NumberFormatException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Enter Valid Number.");
                    markerSizeField.setText("");
                }
            }
        }
}//GEN-LAST:event_applyButtonActionPerformed

    private void buitInRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buitInRadioButtonActionPerformed
        if (!buitInRadioButton.isSelected()) {

            setMarkersBuidInEnableFalse();
        }
        if (buitInRadioButton.isSelected()) {
            applyButton.setEnabled(true);
            markerSizeField.setEnabled(true);
            shapesComboBox.setEnabled(true);
            dataSetComboBox.setEnabled(true);
        }
    }//GEN-LAST:event_buitInRadioButtonActionPerformed

    private void noneRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noneRadioButtonActionPerformed
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
            renderer.setBaseShapesVisible(false);

        }
        if (noneRadioButton.isSelected()) {
            setMarkersBuidInEnableFalse();
        }
    }//GEN-LAST:event_noneRadioButtonActionPerformed

    private void automaticRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_automaticRadioButtonActionPerformed

        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
            renderer.setBaseShapesVisible(true);

        }

        if (automaticRadioButton.isSelected()) {
            setMarkersBuidInEnableFalse();
        }
    }//GEN-LAST:event_automaticRadioButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JRadioButton automaticRadioButton;
    private javax.swing.JButton backButton;
    private javax.swing.JRadioButton buitInRadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel changePlotStyleLabel;
    private javax.swing.JCheckBox conectingLines;
    private javax.swing.JComboBox dataSetComboBox;
    private javax.swing.JCheckBox errorBars;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField markerSizeField;
    private javax.swing.JPanel markersTypePanel;
    private javax.swing.JRadioButton noneRadioButton;
    private javax.swing.JComboBox shapesComboBox;
    // End of variables declaration//GEN-END:variables
}
