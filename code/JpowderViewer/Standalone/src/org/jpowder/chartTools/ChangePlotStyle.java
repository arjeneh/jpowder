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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.util.ShapeUtilities;
import org.jpowder.Analysis.ToolsIcon;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.JpowderInternalframe;
import org.jpowder.dataset.DataSetNoErrors;
import org.jpowder.dataset.DataSetWithErrors;

/**
 *Change the appearnce of each plots within the chart.
 * @author M Arjeneh
 */
public class ChangePlotStyle extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon toolsIcon;
    /*Array of string which contains the all the file names that have been plotted. */
    private String[] string;
    /* Using Multi line jLabel.*/
    private String labelText =
            "<html><FONT COLOR=RED>Red</FONT> and " +
            "<FONT COLOR=BLUE>Blue</FONT> Text</html>";

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
    public static JComboBox getSeriesColourComboBox(){
        return seriesColourComboBox;
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
        seriesPaintPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dataSetComboBox1 = new javax.swing.JComboBox();
        conectingLines = new javax.swing.JCheckBox();
        errorBars = new javax.swing.JCheckBox();
        backButton = new javax.swing.JButton();
        changePlotStyleLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        markersTypePanel = new javax.swing.JPanel();
        markerComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        markerSizeField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        shapesComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        automaticRadioButton = new javax.swing.JRadioButton();
        noneRadioButton = new javax.swing.JRadioButton();
        buitInRadioButton = new javax.swing.JRadioButton();
        seriesColourPanel = new javax.swing.JPanel();
        seriesColourComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        colourPickerButton = new javax.swing.JToggleButton();
        labelText =
        "<html> Select a plot from list and then  " +
        "pick a series colour by clicking on the " +
        "button."+
        "<P>";
        jLabel8 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        seriesPaintPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Series Colour"));

        jLabel6.setText("Plot(s):");

        dataSetComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataSetComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout seriesPaintPanelLayout = new javax.swing.GroupLayout(seriesPaintPanel);
        seriesPaintPanel.setLayout(seriesPaintPanelLayout);
        seriesPaintPanelLayout.setHorizontalGroup(
            seriesPaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seriesPaintPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataSetComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        seriesPaintPanelLayout.setVerticalGroup(
            seriesPaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seriesPaintPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(seriesPaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dataSetComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        conectingLines.setSelected(true);
        conectingLines.setText("Turn on the connecting lines");
        conectingLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectingLinesActionPerformed(evt);
            }
        });

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

        markerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markerComboBoxActionPerformed(evt);
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
            .addGroup(markersTypePanelLayout.createSequentialGroup()
                .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buitInRadioButton)
                    .addComponent(noneRadioButton)
                    .addComponent(automaticRadioButton)
                    .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(applyButton)
                        .addGroup(markersTypePanelLayout.createSequentialGroup()
                            .addComponent(shapesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(markerSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(markerComboBox, 0, 163, Short.MAX_VALUE))
                .addContainerGap())
        );
        markersTypePanelLayout.setVerticalGroup(
            markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(markersTypePanelLayout.createSequentialGroup()
                .addComponent(automaticRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noneRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buitInRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(markerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(markersTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shapesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(markerSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(applyButton)
                .addGap(53, 53, 53))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(markersTypePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(markersTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Marker Type", jPanel1);

        seriesColourPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Series Colour"));

        seriesColourComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seriesColourComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setText("Plot(s):");

        colourPickerButton.setText("Pick Series Colour");
        colourPickerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colourPickerButtonActionPerformed(evt);
            }
        });

        jLabel8.setText(labelText);

        javax.swing.GroupLayout seriesColourPanelLayout = new javax.swing.GroupLayout(seriesColourPanel);
        seriesColourPanel.setLayout(seriesColourPanelLayout);
        seriesColourPanelLayout.setHorizontalGroup(
            seriesColourPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, seriesColourPanelLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(seriesColourPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seriesColourComboBox, 0, 166, Short.MAX_VALUE)
                    .addComponent(colourPickerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(seriesColourPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );
        seriesColourPanelLayout.setVerticalGroup(
            seriesColourPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, seriesColourPanelLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(seriesColourPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(seriesColourComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(colourPickerButton)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Plot Colour", seriesColourPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changePlotStyleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(conectingLines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(errorBars)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            markerComboBox.setModel(new javax.swing.DefaultComboBoxModel(addDataSet()));
            seriesColourComboBox.setModel(new javax.swing.DefaultComboBoxModel(addDataSet()));
        }
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            String labels[] = {"No Chart Added"};
            markerComboBox.setModel(new javax.swing.DefaultComboBoxModel(labels));
            seriesColourComboBox.setModel(new javax.swing.DefaultComboBoxModel(labels));
            colourPickerButton.setSelected(false);
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
        if (renderer.getBaseShapesVisible()) {
            automaticRadioButton.setSelected(true);
//            if(automaticRadioButton.isSelected()){
//           automaticRadioButton.setSelected(true);
//
//            }
//            if(buitInRadioButton.isSelected()){
//                buitInRadioButton.setSelected(true);
//            }
        } else if (!renderer.getBaseShapesVisible()) {
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
            ComboBoxRenderer boxRenderer = new ComboBoxRenderer();
            markerComboBox.setRenderer(boxRenderer);
            seriesColourComboBox.setRenderer(boxRenderer);
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
        markerComboBox.setEnabled(false);
    }

    public void setMarkersBuidInEnableTrue() {
        applyButton.setEnabled(true);
        markerSizeField.setEnabled(true);
        shapesComboBox.setEnabled(true);
        markerComboBox.setEnabled(true);
    }

    public void errorMesage() {
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "There Is No Chart.");
            return;
        }
    }

    /**
     * this methods turns the the connecting lines in the plotted data on and off.
     * @param evt
     */
    private void conectingLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectingLinesActionPerformed
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            conectingLines.setSelected(true);
            return;
        }
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
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            errorBars.setSelected(true);
            return;
        }
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

    private void markerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markerComboBoxActionPerformed
}//GEN-LAST:event_markerComboBoxActionPerformed
    /**
     * this method responsible for gettign action of the comboBoxes and textFied and
     * and appling to the plotted data in the internalframe whcih is in focus.
     * @param evt
     */
    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "There Is No Chart.");
            applyButton.setSelected(false);
            return;
        }

        int seriescount = inFocus.getXYPlot().getDatasetCount();
        for (int i = 0; i < seriescount; i++) {

            if (inFocus.getPowderDataSet().elementAt(i).getFileName().equals(
                    markerComboBox.getSelectedItem())) {
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
                        renderer.setBaseShapesFilled(true);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Circle")) {
                        renderer.setSeriesShape(0, new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble));
                        renderer.setBaseShapesFilled(true);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Circle")) {
                        renderer.setSeriesShape(0, new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Oval")) {
                        renderer.setSeriesShape(0, new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble / 2));
                        renderer.setBaseShapesFilled(true);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Oval")) {
                        renderer.setSeriesShape(0, new Ellipse2D.Double(-dble / 2, -dble / 2, dble, dble / 2));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Diamond")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createDiamond(flot));
                        renderer.setBaseShapesFilled(true);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Diamond")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createDiamond(flot));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill DownTriangle")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createDownTriangle(flot));
                        renderer.setBaseShapesFilled(true);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("DownTriangle")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createDownTriangle(flot));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill UpTriangle")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createUpTriangle(flot));
                        renderer.setBaseShapesFilled(true);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("UpTriangle")) {
                        renderer.setSeriesShape(0, ShapeUtilities.createUpTriangle(flot));
                        renderer.setBaseShapesFilled(false);
                    }
                    if (shapesComboBox.getSelectedItem().toString().equals("Fill Rectangle")) {
                        renderer.setSeriesShape(0, new Rectangle2D.Double(-dble / 2, -dble / 2, dble, dble / 2));
                        renderer.setBaseShapesFilled(true);
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
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            return;
        }
        if (!buitInRadioButton.isSelected()) {

            setMarkersBuidInEnableFalse();
        }
        if (buitInRadioButton.isSelected()) {
            applyButton.setEnabled(true);
            markerSizeField.setEnabled(true);
            shapesComboBox.setEnabled(true);
            markerComboBox.setEnabled(true);
        }
    }//GEN-LAST:event_buitInRadioButtonActionPerformed

    private void noneRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noneRadioButtonActionPerformed
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            return;
        }
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
        if (JpowderInternalframe.getnumberOfJpowderInternalframe() == 0) {
            return;
        }
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        for (int i = 0; i < inFocus.getPowderDataSet().size(); i++) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) inFocus.getXYPlot().getRenderer(i);
            renderer.setBaseShapesVisible(true);
        }

        if (automaticRadioButton.isSelected()) {
            setMarkersBuidInEnableFalse();
        }

    }//GEN-LAST:event_automaticRadioButtonActionPerformed

    private void dataSetComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataSetComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataSetComboBox1ActionPerformed

    private void seriesColourComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seriesColourComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seriesColourComboBoxActionPerformed

    private void colourPickerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colourPickerButtonActionPerformed
        JpowderInternalframe inFocus = Jpowder.internalFrameInFocus;
        SeriesColourPicker colourPicker=new SeriesColourPicker();
        colourPickerButton.setSelected(false);
        Jpowder.jpowderInternalFrameUpdate(inFocus);
    }//GEN-LAST:event_colourPickerButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JRadioButton automaticRadioButton;
    private javax.swing.JButton backButton;
    private javax.swing.JRadioButton buitInRadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel changePlotStyleLabel;
    private javax.swing.JToggleButton colourPickerButton;
    private javax.swing.JCheckBox conectingLines;
    private javax.swing.JComboBox dataSetComboBox1;
    private javax.swing.JCheckBox errorBars;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox markerComboBox;
    private javax.swing.JTextField markerSizeField;
    private javax.swing.JPanel markersTypePanel;
    private javax.swing.JRadioButton noneRadioButton;
    private static javax.swing.JComboBox seriesColourComboBox;
    private javax.swing.JPanel seriesColourPanel;
    private javax.swing.JPanel seriesPaintPanel;
    private javax.swing.JComboBox shapesComboBox;
    // End of variables declaration//GEN-END:variables
}
