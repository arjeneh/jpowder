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
 * JpowderPopupMenu.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder;

import org.jfree.chart.ChartMouseEvent;
import org.jpowder.InternalFrame.JpowderInternalframe2D;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.ExtensionFileFilter;
import org.jfree.ui.RectangleEdge;
import org.jpowder.Annotation.EditAnnotationFrame;

/**
 * This class creates some new popupMenu and also modifies some of the
 * JFreeChart default menu options
 */
public class JpowderPopupMenu extends JPopupMenu implements ActionListener, ChartMouseListener {

    private JFreeChart chart;
    private JPopupMenu popupMenu;
    private ChartPanel chartPanel;
    private XYPlot plot;  // used to pick up whether 2D or 3D plot
    private double X = -1;
    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources =
            ResourceBundle.getBundle("org/jpowder/JpowderPopUpMenu");
    //    protected static ResourceBundle localizationResources =
    //        ResourceBundle.getBundle("org/jpowder/JpowderPopUpMenuDanish");
    public static final String PDF_CAMAND = "PDF";
    public static final String PDFForPublicatio_CAMAND = "PDFForPublication";
    public static final String JPOWDER_APPLET_CAMAND = "JPOWDER_APPLET";
    public static final String PRINT_FOR_PUBLICATION_CAMAND = "PRINT_FOR_PUBLICATION";
    public static final String ADD_ANNOTATION_CAMAND = "ADD_ANNOTATION";
    private JMenu saveAs, printAs, zoomIn, zoomOut;
    private JMenuItem menuItem, addAnnotation;
    /** A flag that controls whether or not file extensions are enforced. */
    private boolean enforceFileExtensions = true;

    public JpowderPopupMenu(final ChartPanel chartPanel) {
        popupMenu = new JPopupMenu();
        this.chartPanel = chartPanel;
        chart = this.chartPanel.getChart();
        chartPanel.setPopupMenu(popupMenu);
        initComponents();
        this.plot = (XYPlot) chart.getPlot();
    }

    public boolean isEnforceFileExtensions() {
        return this.enforceFileExtensions;
    }

    /**
     * all the popUpMenu Action.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JpowderInternalframe2D inFocus = Jpowder.internalFrameInFocus2D;
        String command = e.getActionCommand();
        if (command.equals(ChartPanel.PROPERTIES_COMMAND)) {
            chartPanel.doEditChartProperties();

        } else if (command.equals(ADD_ANNOTATION_CAMAND)) {
//            int mouseX = chartMouseEvent.getTrigger().getX();
//            int mouseY = chartMouseEvent.getTrigger().getY();
//            System.out.println("Co-ordination x = " + mouseX + ", y = " + mouseY);
//            Point2D p = chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
//            XYPlot plot = (XYPlot) chartPanel.getChart().getPlot();
//            Rectangle2D plotArea = chartPanel.getScreenDataArea();
//            ValueAxis domainAxis = plot.getDomainAxis();
//            RectangleEdge domainAxisEdge = plot.getDomainAxisEdge();
//            ValueAxis rangeAxis = plot.getRangeAxis();
//            RectangleEdge rangeAxisEdge = plot.getRangeAxisEdge();
//
//            double chartX = domainAxis.java2DToValue(p.getX(), plotArea, domainAxisEdge);
//            double chartY = rangeAxis.java2DToValue(p.getY(), plotArea, rangeAxisEdge);
//            System.out.println("Value of dataset in the Chart: x = " + chartX + ", y = " + chartY);
//
//            EditAnnotationFrame enf = new EditAnnotationFrame(mouseX, mouseY, chartX, chartY);
//            enf.setVisible(true);
        } else if (command.equals(ChartPanel.COPY_COMMAND)) {
            chartPanel.doCopy();
        } else if (command.equals(ChartPanel.SAVE_COMMAND)) {
            try {
                chartPanel.doSaveAs();
            } catch (IOException ex) {
                Logger.getLogger(JpowderPopupMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (command.equals(ChartPanel.PRINT_COMMAND)) {
            JpowderPrint jpowderPrint = new JpowderPrint(chartPanel);
            jpowderPrint.basicPrint();
        } else if (command.equals(ChartPanel.ZOOM_IN_BOTH_COMMAND)) {
            chartPanel.zoomInBoth(X, X);
        } else if (command.equals(ChartPanel.ZOOM_IN_DOMAIN_COMMAND)) {
            chartPanel.zoomInDomain(X, X);
        } else if (command.equals(ChartPanel.ZOOM_IN_RANGE_COMMAND)) {
            chartPanel.zoomInRange(X, X);
        } else if (command.equals(ChartPanel.ZOOM_OUT_BOTH_COMMAND)) {
            chartPanel.zoomOutBoth(X, X);
        } else if (command.equals(ChartPanel.ZOOM_OUT_DOMAIN_COMMAND)) {
            chartPanel.zoomOutDomain(X, X);
        } else if (command.equals(ChartPanel.ZOOM_OUT_RANGE_COMMAND)) {
            chartPanel.zoomOutRange(X, X);
        } else if (command.equals(ChartPanel.ZOOM_RESET_BOTH_COMMAND)) {
            chartPanel.restoreAutoBounds();
        } else if (command.equals(ChartPanel.ZOOM_RESET_DOMAIN_COMMAND)) {
            chartPanel.restoreAutoDomainBounds();
        } else if (command.equals(ChartPanel.ZOOM_RESET_RANGE_COMMAND)) {
            chartPanel.restoreAutoRangeBounds();
        } else if (command.equals(PRINT_FOR_PUBLICATION_CAMAND)) {
            JpowderPrint jpowderPrint = new JpowderPrint(chartPanel);
            jpowderPrint.printForPublication();
        } else if (command.equals(JPOWDER_APPLET_CAMAND)) {
            saveAsJpowderApplet();
        } else if (command.equals(PDF_CAMAND)) {
            pDF();   //e.getSource()   
        } else if (command.equals(PDFForPublicatio_CAMAND)) {
            inFocus.getXYPlot().setBackgroundPaint(ChartColor.white);
            inFocus.getXYPlot().setOutlinePaint(ChartColor.white);
            pDF();
            inFocus.getXYPlot().setBackgroundPaint(ChartColor.LIGHT_GRAY);
            inFocus.getXYPlot().setOutlinePaint(ChartColor.LIGHT_GRAY);
        }

    }

    /**
     * All the popUpMenu created in the init mehtod.
     */
    public void initComponents() {

        popupMenu.add(addAnnotation = new JMenuItem(localizationResources.getString("Add_Anno")));

        popupMenu.addSeparator();
        popupMenu.add(menuItem = new JMenuItem(localizationResources.getString("Properties...")));
        menuItem.setActionCommand(ChartPanel.PROPERTIES_COMMAND);
        menuItem.addActionListener(this);
        popupMenu.addSeparator();

        popupMenu.add(menuItem = new JMenuItem(localizationResources.getString("Copy")));
        menuItem.setActionCommand(ChartPanel.COPY_COMMAND);
        menuItem.addActionListener(this);

        popupMenu.add(saveAs = new JMenu(localizationResources.getString("Save_as...")));
        saveAs.add(menuItem = new JMenuItem(localizationResources.getString("Applet_Files")));
        menuItem.setActionCommand(JPOWDER_APPLET_CAMAND);
        menuItem.addActionListener(this);
        saveAs.add(menuItem = new JMenuItem(localizationResources.getString("PNG_Image_Files")));
        menuItem.setActionCommand(ChartPanel.SAVE_COMMAND);
        menuItem.addActionListener(this);
        saveAs.add(menuItem = new JMenuItem(localizationResources.getString("PDF_Files")));
        menuItem.setActionCommand(PDF_CAMAND);
        menuItem.addActionListener(this);
        saveAs.add(menuItem = new JMenuItem(localizationResources.getString("PDF_FilesForPublication")));
        menuItem.setActionCommand(PDFForPublicatio_CAMAND);
        menuItem.addActionListener(this);

        popupMenu.addSeparator();
        popupMenu.add(printAs = new JMenu(localizationResources.getString("Print_as...")));
        printAs.add(menuItem = new JMenuItem(localizationResources.getString("Print_Normal")));
        menuItem.setActionCommand(ChartPanel.PRINT_COMMAND);
        menuItem.addActionListener(this);
        printAs.add(menuItem = new JMenuItem(localizationResources.getString("Print_Publication")));
        menuItem.setActionCommand(PRINT_FOR_PUBLICATION_CAMAND);
        menuItem.addActionListener(this);

        popupMenu.addSeparator();
        popupMenu.add(zoomIn = new JMenu(localizationResources.getString("Zoom_In")));
        zoomIn.add(menuItem = new JMenuItem(localizationResources.getString("All_Axes")));
        menuItem.setActionCommand(ChartPanel.ZOOM_IN_BOTH_COMMAND);
        menuItem.addActionListener(this);
        zoomIn.addSeparator();
        zoomIn.add(menuItem = new JMenuItem(localizationResources.getString("Domain_Axis")));
        menuItem.setActionCommand(ChartPanel.ZOOM_IN_DOMAIN_COMMAND);
        menuItem.addActionListener(this);
        zoomIn.add(menuItem = new JMenuItem(localizationResources.getString("Range_Axis")));
        menuItem.setActionCommand(ChartPanel.ZOOM_IN_RANGE_COMMAND);
        menuItem.addActionListener(this);


        popupMenu.add(zoomOut = new JMenu(localizationResources.getString("Zoom_Out")));
//        popupMenu.addSeparator();
        zoomOut.add(menuItem = new JMenuItem(localizationResources.getString("All_Axes")));
        menuItem.setActionCommand(ChartPanel.ZOOM_OUT_BOTH_COMMAND);
        menuItem.addActionListener(this);
        zoomOut.addSeparator();
        zoomOut.add(menuItem = new JMenuItem(localizationResources.getString("Domain_Axis")));
        menuItem.setActionCommand(ChartPanel.ZOOM_OUT_DOMAIN_COMMAND);
        menuItem.addActionListener(this);
        zoomOut.add(menuItem = new JMenuItem(localizationResources.getString("Range_Axis")));
        menuItem.setActionCommand(ChartPanel.ZOOM_OUT_RANGE_COMMAND);
        menuItem.addActionListener(this);

//        popupMenu.add(autoRange = new JMenu("Auto Range"));
//        autoRange.add(menuItem = new JMenuItem("Both Axes"));
//        menuItem.setActionCommand(ChartPanel.ZOOM_RESET_BOTH_COMMAND);
//        menuItem.addActionListener(this);
//        autoRange.addSeparator();
//        autoRange.add(menuItem = new JMenuItem("X Axis"));
//        menuItem.setActionCommand(ChartPanel.ZOOM_RESET_DOMAIN_COMMAND);
//        menuItem.addActionListener(this);
//        autoRange.add(menuItem = new JMenuItem("Y Axis"));
//        menuItem.setActionCommand(ChartPanel.ZOOM_RESET_RANGE_COMMAND);
//        menuItem.addActionListener(this);
    }

    /**
     * saving the file as serialazble so that can be saved and retrived into
     * to Applet for web.
     */
    public void saveAsJpowderApplet() {
        JFileChooser chooser = new JFileChooser();
        ExtensionFileFilter filter = new ExtensionFileFilter(
                "Jpowder-Applet Format (*.ser)", ".ser");
        chooser.addChoosableFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        File fileName = chooser.getSelectedFile();

        if (fileName == null) {
            return;
        }
        if (fileName.exists()) {

            Object[] options = {"Yes",
                "No"};
            int n = JOptionPane.showOptionDialog(null,
                    "Do you want to overwrite existing file?",
                    "Confirm Save As",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[1]); //default button title
            if (n == 1) {
                return;
            }
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {


            try {

                String filename = chooser.getSelectedFile().getPath();
                if (isEnforceFileExtensions()) {
                    if (!filename.endsWith(".ser")) {
                        filename = filename + ".ser";
                    }
                }
                FileOutputStream buffer = new FileOutputStream(filename);
                final ObjectOutput out = new ObjectOutputStream(buffer);

                out.writeObject(chart);

                out.flush();
                out.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Invalid file!",
                        "error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        }
    }

    /**
     * pdf file final saving.
     */
    public void pDF() {

        JFileChooser chooser = new JFileChooser();
        ExtensionFileFilter filter = new ExtensionFileFilter(
                "PDF (*.pdf) ", ".pdf");
        chooser.addChoosableFileFilter(filter);



        int returnVal = chooser.showSaveDialog(chooser);

        File fileName = chooser.getSelectedFile();
        if (fileName == null) {
            return;
        }
        if (fileName.exists()) {
            Object[] options = {"Yes",
                "No"};
            int n = JOptionPane.showOptionDialog(null,
                    "Do you want to overwrite existing file?",
                    "Confirm Save As",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[1]); //default button title

            if (n == 1) {
                return;
            }
        }

//        try {
//            ChartUtilities.saveChartAsJPEG(fileName, chart, 500, 500);
//        } catch (IOException ex) {
//            Logger.getLogger(JpowderPopupMenu.class.getName()).log(Level.SEVERE, null, ex);
//        }

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            try {


                chart.getXYPlot().getDomainAxis().setLabel("X");

                saveChartAsPDF(fileName, chart, 800, 600, new DefaultFontMapper());


//                try //try statement
//                {
//                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + fileName.getPath());   //open the file chart.pdf
//
//                } catch (Exception e) //catch any exceptions here
//                {

//                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * responsible for saving and writng the chart as pdf
     * @param file
     * @param chart
     * @param width
     * @param height
     * @param mapper
     * @throws IOException
     */
    public void saveChartAsPDF(File file,
            JFreeChart chart,
            int width,
            int height,
            FontMapper mapper) throws IOException {
        String filename = file.getPath();
        if (isEnforceFileExtensions()) {
            if (!filename.endsWith(".pdf")) {
                filename = filename + ".pdf";
            }
        }
        OutputStream out = new BufferedOutputStream(new FileOutputStream(filename));
        writeChartAsPDF(out, chart, chartPanel.getWidth(), chartPanel.getHeight(), mapper);
        out.close();
    }

    /**
     *
     * responsible for saving and writng the chart as pdf
     * @param out
     * @param chart
     * @param width
     * @param height
     * @param mapper
     * @throws IOException
     */
    public void writeChartAsPDF(OutputStream out,
            JFreeChart chart,
            int width,
            int height,
            FontMapper mapper) throws IOException {
        Rectangle pagesize = new Rectangle(width, height);
        Document document = new Document(pagesize, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(width, height);
            Graphics2D g2 = tp.createGraphics(width, height, mapper);
            Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
            chart.draw(g2, r2D);
            g2.dispose();
            cb.addTemplate(tp, 0, 0);
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent chartMouseEvent) {
        int mouseX = chartMouseEvent.getTrigger().getX();
        int mouseY = chartMouseEvent.getTrigger().getY();
        System.out.println("Co-ordination x = " + mouseX + ", y = " + mouseY);
        Point2D p = chartPanel.translateScreenToJava2D(new Point(mouseX, mouseY));
        XYPlot plot = (XYPlot) chartPanel.getChart().getPlot();
        Rectangle2D plotArea = chartPanel.getScreenDataArea();
        ValueAxis domainAxis = plot.getDomainAxis();
        RectangleEdge domainAxisEdge = plot.getDomainAxisEdge();
        ValueAxis rangeAxis = plot.getRangeAxis();
        RectangleEdge rangeAxisEdge = plot.getRangeAxisEdge();

        double chartX = domainAxis.java2DToValue(p.getX(), plotArea, domainAxisEdge);
        double chartY = rangeAxis.java2DToValue(p.getY(), plotArea, rangeAxisEdge);
        System.out.println("Value of dataset in the Chart: x = " + chartX + ", y = " + chartY);

//        EditAnnotationFrame enf = new EditAnnotationFrame(mouseX, mouseY, chartX, chartY);
//        enf.setVisible(true);
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
