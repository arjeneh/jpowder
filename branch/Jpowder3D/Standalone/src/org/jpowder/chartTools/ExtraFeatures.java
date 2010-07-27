/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtraFeatures.java
 *
 * Created on Jun 4, 2010, 2:58:44 PM
 */
package org.jpowder.chartTools;

import org.jpowder.chartTools.Markers.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jpowder.Analysis.ToolsIcon;
import org.jpowder.InfoPanel;
import org.jpowder.Jpowder;
import org.jpowder.InernalFrame.JpowderInternalframe2D;
import org.jpowder.jfreechart.JpowderXYDataset;
import org.jpowder.jfreechart.JpowderXYLineAndShapeRender;

/**
 *
 * @author Arjeneh
 */
public class ExtraFeatures extends javax.swing.JPanel implements InfoPanel {

    private ToolsIcon toolsIcon;
    private JpowderTickUnitSource jpowderTickUnitSource = new JpowderTickUnitSource();
    private static String decimal = "";
    private DefaultTableModel defaultTableModel;
    private String columnsName[] = {"Plot(s)"};
    private String[][] fileName;
    private String newLegend;
    MarkerArray array = new MarkerArray();
    MarkesIcons[] carray = array.createCountriesArray();

    /** Creates new form ExtraFeatures */
    public ExtraFeatures(ToolsIcon analysisIcon) {

        initComponents();
        this.toolsIcon = analysisIcon;
        legendTable.setVisible(false);



    }

    public void update() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        imageComboBoxTest.setRenderer(new MarkerComboBoxRenderer(null));
        if (JpowderInternalframe2D.getnumberOfJpowderInternalframe() == 0) {
            if (defaultTableModel != null) {
                defaultTableModel.getDataVector().removeAllElements();//remove all the rows from table

            }
            legendTable.updateUI();
            return;
        }
        jSpinner1.setValue(inFocus.getXYPlot().getAxisOffset().getBottom());
//          gridlineWidth.setValue(inFocus.getXYPlot().getDomainGridlineStroke());



        defaultTableModel = new DefaultTableModel(getFileName(), columnsName);
        legendTable.setModel(defaultTableModel);



        defaultTableModel.addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
                int size = inFocus.getXYPlot().getDatasetCount();
                for (int i = 0; i < size; i++) {

                    if (!defaultTableModel.getValueAt(i, 0).equals("")) {

                        newLegend = legendTable.getModel().getValueAt(i, 0).toString();
                        //Double.parseDouble(dataTable.getModel().getValueAt(i, 1).toString());
                        inFocus.getPowderDataSet().get(i).setFileName(newLegend);


                    }
                }


            }
        });
    }

    public String[][] getFileName() {

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        int size = inFocus.getXYPlot().getDatasetCount();
        fileName = new String[size][2];
        for (int i = 0; i < size; i++) {

            String[] row = new String[2];
            row[0] = inFocus.getPowderDataSet().elementAt(i).getFileName();


            fileName[i] = row;

        }
        return fileName;
    }

    public static String getDecimalPattern() {
//        decimal = decimalPlacesSpinner.getValue().toString();

        return "0." + decimal + "E0";
    }

    public void xAxisScientificNotation() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (xScientific.isSelected()) {
            inFocus.getXYPlot().getDomainAxis().setStandardTickUnits(jpowderTickUnitSource);
        }
        if (!xScientific.isSelected()) {
            inFocus.getXYPlot().getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        }

    }

    public void yAxisScientificNotation() {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (yScientific.isSelected()) {
            inFocus.getXYPlot().getRangeAxis().setStandardTickUnits(jpowderTickUnitSource);


        }
        if (!yScientific.isSelected()) {
            inFocus.getXYPlot().getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());
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

        jComboBox1 = new javax.swing.JComboBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        backButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        majorX = new javax.swing.JCheckBox();
        majorY = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        gridlineWidth = new javax.swing.JSpinner();
        minorX = new javax.swing.JCheckBox();
        minorY = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        xScientific = new javax.swing.JCheckBox();
        yScientific = new javax.swing.JCheckBox();
        decimalPlacesSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        legendCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        legendTable = new javax.swing.JTable();
        legendPositionButtom = new javax.swing.JRadioButton();
        legendPositionTop = new javax.swing.JRadioButton();
        legendPositionLeft = new javax.swing.JRadioButton();
        legendPositionRight = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        legendPositionTopRight = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jSpinner2 = new javax.swing.JSpinner();
        imageComboBoxTest = new javax.swing.JComboBox(carray);
        jPanel6 = new javax.swing.JPanel();
        chartBGImage = new javax.swing.JButton();
        plotBGImage = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jLabel1.setText("Jpowder Second Version Extera Features.");

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        majorX.setSelected(true);
        majorX.setText("Set Major X Grids");
        majorX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                majorXActionPerformed(evt);
            }
        });

        majorY.setSelected(true);
        majorY.setText("Set Major Y Grids");
        majorY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                majorYActionPerformed(evt);
            }
        });

        jButton1.setText("Pick Colour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Width:");

        gridlineWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gridlineWidthStateChanged(evt);
            }
        });

        minorX.setText("Set Minor X Grids");
        minorX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minorXActionPerformed(evt);
            }
        });

        minorY.setText("Set Minor Y Grids");
        minorY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minorYActionPerformed(evt);
            }
        });

        jButton4.setText("test 2d");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("test 3d");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(minorX)
                    .addComponent(majorY)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(majorX, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(gridlineWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(minorY)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                .addContainerGap(56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(majorX)
                    .addComponent(jLabel3)
                    .addComponent(gridlineWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(majorY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(minorX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minorY)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("GridLines", jPanel1);

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel2.setText("Set Axis Offset");

        jButton2.setText("Axis Colour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Color Panel Test");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(55, 55, 55)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton3)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Axis", jPanel2);

        xScientific.setText("X axis scientific format");
        xScientific.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xScientificActionPerformed(evt);
            }
        });

        yScientific.setText("Y axis scientific format");
        yScientific.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yScientificActionPerformed(evt);
            }
        });

        decimalPlacesSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                decimalPlacesSpinnerStateChanged(evt);
            }
        });

        jLabel4.setText("Decimal places:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(decimalPlacesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(xScientific)
                        .addComponent(yScientific)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decimalPlacesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xScientific)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yScientific)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Number Format", jPanel3);

        legendCheckBox.setText("Enable Legend");
        legendCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendCheckBoxActionPerformed(evt);
            }
        });

        legendTable.setModel(new DefaultTableModel());
        legendTable.setEnabled(false);
        jScrollPane1.setViewportView(legendTable);

        buttonGroup1.add(legendPositionButtom);
        legendPositionButtom.setSelected(true);
        legendPositionButtom.setText("Buttom");
        legendPositionButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionButtomActionPerformed(evt);
            }
        });

        buttonGroup1.add(legendPositionTop);
        legendPositionTop.setText("Top");
        legendPositionTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionTopActionPerformed(evt);
            }
        });

        buttonGroup1.add(legendPositionLeft);
        legendPositionLeft.setText("Left");
        legendPositionLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionLeftActionPerformed(evt);
            }
        });

        buttonGroup1.add(legendPositionRight);
        legendPositionRight.setText("Right");
        legendPositionRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionRightActionPerformed(evt);
            }
        });

        jLabel5.setText("Legend Position");

        buttonGroup1.add(legendPositionTopRight);
        legendPositionTopRight.setText("In Chart");
        legendPositionTopRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legendPositionTopRightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(legendCheckBox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(legendPositionTopRight)
                    .addComponent(legendPositionRight)
                    .addComponent(legendPositionLeft)
                    .addComponent(legendPositionTop)
                    .addComponent(legendPositionButtom)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(legendCheckBox)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(legendPositionButtom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(legendPositionTop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(legendPositionLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(legendPositionRight)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(legendPositionTopRight)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Legend", jPanel4);

        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        imageComboBoxTest.setAlignmentX(5.0F);
        imageComboBoxTest.setAlignmentY(5.0F);
        imageComboBoxTest.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(imageComboBoxTest, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(imageComboBoxTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Series", jPanel5);

        chartBGImage.setText("Set Chart BackGround Image");
        chartBGImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartBGImageActionPerformed(evt);
            }
        });

        plotBGImage.setText("Set Plot BackGround Image");
        plotBGImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotBGImageActionPerformed(evt);
            }
        });

        jLabel6.setText("Brightness:");

        jLabel7.setText("Brightness:");

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.5f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.01f)));
        jSpinner3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner3StateChanged(evt);
            }
        });

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.5f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.01f)));
        jSpinner4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner4StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(plotBGImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chartBGImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {chartBGImage, plotBGImage});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chartBGImage)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plotBGImage)
                    .addComponent(jLabel7)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chartBGImage, plotBGImage});

        jTabbedPane1.addTab("Back Ground Image", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        toolsIcon.add(this);
        toolsIcon.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void majorXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_majorXActionPerformed

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        boolean check = inFocus.getXYPlot().isDomainGridlinesVisible();
        inFocus.getXYPlot().setDomainGridlinesVisible(!check);

    }//GEN-LAST:event_majorXActionPerformed

    private void majorYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_majorYActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        boolean check = inFocus.getXYPlot().isRangeGridlinesVisible();
        inFocus.getXYPlot().setRangeGridlinesVisible(!check);
    }//GEN-LAST:event_majorYActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
//        JColorChooser colorChooser = new JColorChooser(Color.WHITE);
//        JDialog dialog = JColorChooser.createDialog(this, "Gridline Colour", true, colorChooser, null, null);

        Color c = JColorChooser.showDialog(
                this, "Gridline Colour", Color.white);
        if (c == null) {
            return;
        }

//        dialog.setVisible(true);
//        Color newColor = colorChooser.getColor();
        if (majorX.isSelected()) {
            inFocus.getXYPlot().setDomainGridlinePaint(c);
        }
        if (majorY.isSelected()) {
            inFocus.getXYPlot().setRangeGridlinePaint(c);
        } else {
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;


        double offset = Double.parseDouble(jSpinner1.getValue().toString());

        inFocus.getXYPlot().setAxisOffset(new RectangleInsets(offset, offset, offset, offset));

    }//GEN-LAST:event_jSpinner1StateChanged

    private void minorXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minorXActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        boolean check = inFocus.getXYPlot().isDomainMinorGridlinesVisible();
        inFocus.getXYPlot().setDomainMinorGridlinesVisible(!check);
    }//GEN-LAST:event_minorXActionPerformed

    private void minorYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minorYActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        boolean check = inFocus.getXYPlot().isRangeMinorGridlinesVisible();
        inFocus.getXYPlot().setRangeMinorGridlinesVisible(!check);
    }//GEN-LAST:event_minorYActionPerformed

    private void gridlineWidthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gridlineWidthStateChanged
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        float gridwidth = (float) Double.parseDouble(gridlineWidth.getValue().toString());
        if (majorX.isSelected()) {
            inFocus.getXYPlot().setDomainGridlineStroke(new BasicStroke(gridwidth));
        }
        if (majorY.isSelected()) {
            inFocus.getXYPlot().setRangeGridlineStroke(new BasicStroke(gridwidth));
        }
        if (minorX.isSelected()) {
            inFocus.getXYPlot().setDomainMinorGridlineStroke(new BasicStroke(gridwidth));
        }
        if (minorY.isSelected()) {
            inFocus.getXYPlot().setRangeMinorGridlineStroke(new BasicStroke(gridwidth));
        }
    }//GEN-LAST:event_gridlineWidthStateChanged

    private void xScientificActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xScientificActionPerformed
        xAxisScientificNotation();
    }//GEN-LAST:event_xScientificActionPerformed

    private void yScientificActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yScientificActionPerformed
        yAxisScientificNotation();
    }//GEN-LAST:event_yScientificActionPerformed

    private void decimalPlacesSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_decimalPlacesSpinnerStateChanged
        //        decimal = decimalPlacesSpinner.getValue().toString();
        decimal = "";
        int dec = (int) Integer.parseInt(decimalPlacesSpinner.getValue().toString());
        for (int i = 0; i < dec; i++) {
            decimal += "0";
        }
        if (xScientific.isSelected()) {
            xAxisScientificNotation();
        }
        if (yScientific.isSelected()) {
            yAxisScientificNotation();
        }

    }//GEN-LAST:event_decimalPlacesSpinnerStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        Color c = JColorChooser.showDialog(
                this, "Gridline Colour", Color.white);
        if (c == null) {
            return;
        }
        inFocus.getXYPlot().getDomainAxis().setAxisLinePaint(c);
        inFocus.getXYPlot().getRangeAxis().setAxisLinePaint(c);
        inFocus.getXYPlot().getDomainAxis().setTickMarkPaint(c);
        inFocus.getXYPlot().getRangeAxis().setTickMarkPaint(c);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        float seriesWidth = (float) Double.parseDouble(jSpinner2.getValue().toString());

        for (int i = 0; i < inFocus.getXYPlot().getDatasetCount(); i++) {
            inFocus.getXYPlot().getRenderer(i).setSeriesStroke(0, new BasicStroke(seriesWidth));
        }
    }//GEN-LAST:event_jSpinner2StateChanged

    private void legendCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendCheckBoxActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (legendCheckBox.isSelected()) {
            CreateLegend createLegend = new CreateLegend();
            createLegend.setLegend();
            legendTable.setEnabled(true);
            legendTable.setVisible(true);

        }
        if (!legendCheckBox.isSelected()) {
            inFocus.getchart().removeLegend();
            legendTable.setEnabled(false);
            legendTable.setVisible(false);
        }
    }//GEN-LAST:event_legendCheckBoxActionPerformed

    private void legendPositionButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionButtomActionPerformed

        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (legendPositionButtom.isSelected()) {

            inFocus.getchart().getLegend().setPosition(RectangleEdge.BOTTOM);

        }

    }//GEN-LAST:event_legendPositionButtomActionPerformed

    private void legendPositionTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionTopActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (legendPositionTop.isSelected()) {
            inFocus.getchart().getLegend().setPosition(RectangleEdge.TOP);

        }
    }//GEN-LAST:event_legendPositionTopActionPerformed

    private void legendPositionLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionLeftActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        if (legendPositionLeft.isSelected()) {
            inFocus.getchart().getLegend().setPosition(RectangleEdge.LEFT);
        }
    }//GEN-LAST:event_legendPositionLeftActionPerformed

    private void legendPositionRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionRightActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (legendPositionRight.isSelected()) {
            inFocus.getchart().getLegend().setPosition(RectangleEdge.RIGHT);

        }
    }//GEN-LAST:event_legendPositionRightActionPerformed

    private void legendPositionTopRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legendPositionTopRightActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        if (legendPositionTopRight.isSelected()) {
//            inFocus.getchart().getLegend().setPosition(RectangleEdge.RIGHT);
//               XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.78,  inFocus.getchart().getLegend(),
//                RectangleAnchor.BOTTOM_RIGHT);
//          ta.setMaxWidth(0.48);
//         inFocus.getXYPlot().addAnnotation(ta);

            LegendTitle legend = inFocus.getchart().getLegend();
            legend.setPosition(RectangleEdge.RIGHT); // cause items to wrap
            ((XYPlot) inFocus.getchart().getPlot()).addAnnotation(new XYTitleAnnotation(0.98, 0.78, legend, RectangleAnchor.CENTER));
        }
    }//GEN-LAST:event_legendPositionTopRightActionPerformed

    private void chartBGImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartBGImageActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File f) {
                String fileName = f.getName();
                if (fileName.endsWith(".png")) {
                    return true;
                }
                if (fileName.endsWith(".gif")) {
                    return true;
                }
                if (fileName.endsWith(".xy")) {
                    return true;
                }
                if (fileName.endsWith(".gss")) {
                    return true;
                }

                return false;

            }

            @Override
            public String getDescription() {
                return "File (*.xy, *.xye,*.cif,*.gss)";
            }
        };

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // get the selected files
            File selectedFiles = fileChooser.getSelectedFile();

            inFocus.getchart().setBackgroundImage(new ImageIcon(selectedFiles.getAbsolutePath()).getImage());

        }
    }//GEN-LAST:event_chartBGImageActionPerformed

    private void plotBGImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotBGImageActionPerformed
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // get the selected files
            File selectedFiles = fileChooser.getSelectedFile();

            inFocus.getXYPlot().setBackgroundImage(new ImageIcon(selectedFiles.getAbsolutePath()).getImage());

        }
    }//GEN-LAST:event_plotBGImageActionPerformed

    private void jSpinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner3StateChanged
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        float f = Float.parseFloat(jSpinner3.getValue().toString());
        inFocus.getchart().setBackgroundImageAlpha(f);
    }//GEN-LAST:event_jSpinner3StateChanged

    private void jSpinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner4StateChanged
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;

        float f = Float.parseFloat(jSpinner4.getValue().toString());
        inFocus.getXYPlot().setBackgroundImageAlpha(f);
    }//GEN-LAST:event_jSpinner4StateChanged

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
    ColorTable colorTable = new ColorTable();
    colorTable.setLocationRelativeTo(jButton3);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
            NumberAxis xAxis = new NumberAxis("");
            NumberAxis yAxis = new NumberAxis("");
            JpowderXYLineAndShapeRender r = new JpowderXYLineAndShapeRender();
            XYPlot plot = new XYPlot(
                    new JpowderXYDataset(inFocus.getPowderDataSet().elementAt(0)),
                    xAxis, yAxis, r);

            JFreeChart chart = new JFreeChart(null, null, plot, false);// for getting the chart header
            ChartPanel cp= new ChartPanel(chart);
            JFrame frame = new JFrame();
            frame.add(cp);
            frame.setVisible(true);
            frame.setSize(500,500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton chartBGImage;
    private static javax.swing.JSpinner decimalPlacesSpinner;
    private javax.swing.JSpinner gridlineWidth;
    private javax.swing.JComboBox imageComboBoxTest;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox legendCheckBox;
    private javax.swing.JRadioButton legendPositionButtom;
    private javax.swing.JRadioButton legendPositionLeft;
    private javax.swing.JRadioButton legendPositionRight;
    private javax.swing.JRadioButton legendPositionTop;
    private javax.swing.JRadioButton legendPositionTopRight;
    private javax.swing.JTable legendTable;
    private javax.swing.JCheckBox majorX;
    private javax.swing.JCheckBox majorY;
    private javax.swing.JCheckBox minorX;
    private javax.swing.JCheckBox minorY;
    private javax.swing.JButton plotBGImage;
    private javax.swing.JCheckBox xScientific;
    private javax.swing.JCheckBox yScientific;
    // End of variables declaration//GEN-END:variables
}
