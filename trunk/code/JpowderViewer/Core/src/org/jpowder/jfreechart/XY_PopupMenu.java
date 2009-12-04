/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import org.jpowder.util.VectorMiscUtil;

/**
 *@Class name:   XY_PopupMenu.java
@Author: Kreecha Puphaiboon
@Date: 21 May 2007, 09:53
@Modf:
@Description:
 * A general popupmenu which allows the user to enable/disable chart properties e.g.
turn off connecting line, turn off marker,

 * @see javax.swing.JPopupMenu

@Return:
 */
public class XY_PopupMenu extends javax.swing.JPopupMenu {

  private org.jfree.chart.JFreeChart chart;
  private javax.swing.JPopupMenu jFreeChartPopup;
  private org.jfree.chart.ChartPanel chartPanel;
  private org.jfree.chart.plot.XYPlot plot;
  //
  private javax.swing.JMenu changePlotStyle;
  private javax.swing.JCheckBoxMenuItem offConnectLine;
  private javax.swing.JCheckBoxMenuItem offMarker;
  private javax.swing.JMenuItem markPeakPosition;
  private javax.swing.JMenuItem scaleYdata;
  private javax.swing.JMenuItem Saveserialize;
  private javax.swing.JMenuItem Saveaspdf;

  public XY_PopupMenu(org.jfree.chart.ChartPanel chartPanel) {

    this.chartPanel = chartPanel;
    this.chart = this.chartPanel.getChart();
    this.jFreeChartPopup = this.chartPanel.getPopupMenu();
    this.plot = (org.jfree.chart.plot.XYPlot) this.chart.getPlot();

    //Change Plot Style
    changePlotStyle = new javax.swing.JMenu("Change plot style");
    changePlotStyle.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent event) {
        System.out.println("Change plot style");
      }
    });

    offConnectLine = new javax.swing.JCheckBoxMenuItem("turn off connecting line");
    offConnectLine.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent event) {
        //Get the plot, get renderer, set to what i want.
        //int seriesCount = getPlot().getSeriesCount();
        System.out.println("turn off/on connecting line activated");
        org.jfree.chart.renderer.xy.XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) getPlot().getRenderer();

        boolean status = renderer.getBaseLinesVisible();
        renderer.setBaseLinesVisible(!status);

      }
    });


    offMarker = new javax.swing.JCheckBoxMenuItem("turn on marker");
    offMarker.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent event) {
        //get the plot, get renderer, set to what i want.
        //int seriesCount = getPlot().getSeriesCount();
        System.out.println("turn off/on marker activated");
        org.jfree.chart.renderer.xy.XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) getPlot().getRenderer();

        boolean status = renderer.getBaseShapesVisible();
        renderer.setBaseShapesVisible(!status);
      }
    });

    //ADD THESE THREE ONTO changePlotStyle
    changePlotStyle.add(offConnectLine);
    changePlotStyle.add(offMarker);

    //ADD Mark Peak Position
    markPeakPosition = new javax.swing.JMenuItem("Mark peak position");
    markPeakPosition.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent event) {
        //turn off or on Markers.
        //boolean status = plot.getDomainMarkers(arg0);

        XYDataset dataset = getPlot().getDataset();

        List chartList = new Vector();
        Vector tX = new Vector();
        Vector tY = new Vector();

        //Multiple series in case of series in a chart.
        int seriescount = getPlot().getSeriesCount();
        for (int seriesindex = 0; seriesindex < seriescount; seriesindex++) {

          for (int itemindex = 0; itemindex < dataset.getItemCount(seriesindex); itemindex++) {
            tX.add(dataset.getXValue(seriesindex, itemindex));
            tY.add(dataset.getYValue(seriesindex, itemindex));
          }
          chartList.add(tX);
          chartList.add(tY);
        }

        //System.out.println("Value of chartList = " + chartList);
        //System.out.println("Value of tX = " + tX);
        //System.out.println("Value of tY = " + tY);

        //DRAW a Y line on the max value in Range.
        //Find the max value and find its position using v.indexOf("max value");
        //Find the x position of the X axis (Domain).
        Double maxRange = VectorMiscUtil.findMaxElementOf1DVector(tY);
        //System.out.println("Max Range value is " + maxRange);

        int pos = tY.indexOf(Double.parseDouble(maxRange.toString()));//not found
        //System.out.println("Max Range position is " + pos);

        //add Marker Higlight for the range.
        Marker peakRangeMarker = new ValueMarker(Double.parseDouble(
                maxRange.toString()));

        peakRangeMarker.setStroke(new BasicStroke(1.4f));
        peakRangeMarker.setPaint(Color.yellow);
        peakRangeMarker.setLabel("Peak Range");
        peakRangeMarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        peakRangeMarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        getPlot().addRangeMarker(peakRangeMarker);

        //System.out.println("tX.elementAt(pos) = " + tY.elementAt(pos));

        Double maxDomain = (Double) tX.elementAt(pos);

        Marker maxDomainTarget = new ValueMarker(maxDomain);
        maxDomainTarget.setStroke(new BasicStroke(1.4f));
        maxDomainTarget.setPaint(Color.yellow);
        maxDomainTarget.setLabelOffsetType(LengthAdjustmentType.EXPAND);
        maxDomainTarget.setLabel("Peak Domain");
        maxDomainTarget.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        maxDomainTarget.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
        getPlot().addDomainMarker(maxDomainTarget);

      }
    });
/**
 * escale Y data
 */
    //ADD Rescale Y data
    scaleYdata = new javax.swing.JMenuItem("Rescale Y data");
    scaleYdata.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent event) {
        System.out.println("rescale Y data");
      }
    });
    /**
     * Saves the data as a seriliazed object
     */

    //ADD serili
    Saveserialize = new javax.swing.JMenuItem("Save as Ser...");
    Saveserialize.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent event) {
        System.out.println("Save as serialize");
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".Ser ", "Ser");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(chooser);
        File fileName = chooser.getSelectedFile();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          try {
            FileOutputStream buffer = new FileOutputStream(fileName);
            final ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(chart);
          } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid file!",
                    "error", JOptionPane.ERROR_MESSAGE);
          }
        } else {
        }
      }
    });

    /**
     * This Part Save the plotted data as pdf and then open
     * the saved pdf file imdiately'
     */
    //Save As pdf
    Saveaspdf = new javax.swing.JMenuItem("Save as pdf");
    Saveaspdf.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(java.awt.event.ActionEvent event) {
        System.out.println("saved as pdf");

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".pdf ", "pdf");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showSaveDialog(chooser);
        File fileName = chooser.getSelectedFile();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          try {
            saveChartAsPDF(fileName, chart, 10000, 10000, new DefaultFontMapper());
            System.out.println(fileName.getPath());
            try //try statement
            {
              Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + fileName.getPath());   //open the file chart.pdf

            } catch (Exception e) //catch any exceptions here
            {
              System.out.println("Error" + e);  //print the error
              }
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
      }
/**
 * responsible for saving and writng the chart as pdf
 */
      public void saveChartAsPDF(File file,
              JFreeChart chart,
              int width,
              int height,
              FontMapper mapper) throws IOException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        writeChartAsPDF(out, chart, 1500, 2500, mapper);
        out.close();
      }
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
    });

    //
    this.jFreeChartPopup.addSeparator();
    this.jFreeChartPopup.add(changePlotStyle);
    //
    this.jFreeChartPopup.addSeparator();
    this.jFreeChartPopup.add(markPeakPosition);
    //
    this.jFreeChartPopup.addSeparator();
    this.jFreeChartPopup.add(scaleYdata);
    //
    this.jFreeChartPopup.addSeparator();
    this.jFreeChartPopup.add(Saveserialize);
    //
    this.jFreeChartPopup.addSeparator();
    this.jFreeChartPopup.add(Saveaspdf);

  }
/*
  public org.jfree.chart.JFreeChart getChart() {
    return chart;
  }

  public void setChart(org.jfree.chart.JFreeChart chart) {
    this.chart = chart;
  }

  public javax.swing.JPopupMenu getJFreeChartPopup() {
    return jFreeChartPopup;
  }

  public void setJFreeChartPopup(javax.swing.JPopupMenu jFreeChartPopup) {
    this.jFreeChartPopup = jFreeChartPopup;
  }

  public org.jfree.chart.ChartPanel getChartPanel() {
    return chartPanel;
  }

  public void setChartPanel(org.jfree.chart.ChartPanel chartPanel) {
    this.chartPanel = chartPanel;
  }
*/
  public org.jfree.chart.plot.XYPlot getPlot() {
    return plot;
  }

  public void setPlot(org.jfree.chart.plot.XYPlot plot) {
    this.plot = plot;
  }

  public javax.swing.JMenu getChangePlotStyle() {
    return changePlotStyle;
  }

  public void setChangePlotStyle(javax.swing.JMenu changePlotStyle) {
    this.changePlotStyle = changePlotStyle;
  }
/*
  public javax.swing.JCheckBoxMenuItem getOffConnectLine() {
    return offConnectLine;
  }

  public void setOffConnectLine(javax.swing.JCheckBoxMenuItem offConnectLine) {
    this.offConnectLine = offConnectLine;
  }

  public javax.swing.JCheckBoxMenuItem getOffMarker() {
    return offMarker;
  }

  public void setOffMarker(javax.swing.JCheckBoxMenuItem offMarker) {
    this.offMarker = offMarker;
  }

  public javax.swing.JMenuItem getMarkPeakPosition() {
    return markPeakPosition;
  }

  public void setMarkPeakPosition(javax.swing.JMenuItem markPeakPosition) {
    this.markPeakPosition = markPeakPosition;
  }

  public javax.swing.JMenuItem getScaleYdata() {
    return scaleYdata;
  }

  public void setScaleYdata(javax.swing.JMenuItem scaleYdata) {
    this.scaleYdata = scaleYdata;
  }
 * */
}
