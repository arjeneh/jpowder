

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
 * JpowderApplet.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Milad Arjeneh, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Vector;
import javax.swing.JApplet;
import javax.swing.UIManager;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jpowder.dataset.DataSet;
import org.jpowder.fileCabinet.PowderFileCabinet;
import org.jpowder.jfreechart.FilesPlotter;

/**
 *
 * @author M Arjeneh
 */
public class JpowderApplet extends JApplet {

    private JFreeChart createChart() {

        JFreeChart serializedChart = null;

        try {

            String fileName = this.getParameter("PATH");
            //fileName = "/home/anders/Desktop/Jpowder/branch/Jpowder3D/Applet/build/classes/BM16_C6-H6-N6-O4_H2O.xye"; //"milad.ser";

            if ( fileName.endsWith(".ser") )
            {
              URL source = new URL(getCodeBase(), fileName);
              ObjectInputStream charts = new ObjectInputStream(source.openStream());
              serializedChart = (JFreeChart) charts.readObject();
            }
            else
            {
                //URL source = new URL(getCodeBase(), fileName);
                Vector<DataSet> data = PowderFileCabinet.createDataSetFromPowderFile(fileName);
                FilesPlotter fileplotter = new FilesPlotter(data);
                serializedChart = fileplotter.createChart();
            }
        } catch (Exception e) {


            e.printStackTrace();
        }

        return serializedChart;
    }

    @Override
    public void init() {
        try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());

        } catch (Exception j) {
            j.printStackTrace();
        }
        JFreeChart chart = createChart();
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(300, 300);
        add(chartPanel);
        setVisible(true);

    }
}
