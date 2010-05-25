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
import javax.swing.JApplet;
import javax.swing.UIManager;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author M Arjeneh
 */
public class JpowderApplet extends JApplet {

    private JFreeChart createChart() {

        JFreeChart serializedChart = null;
//        JFileChooser chooser = new JFileChooser();
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                ".Ser ", "Ser");
//        chooser.setFileFilter(filter);
//        int returnVal = chooser.showSaveDialog(this);
//        File fileName = chooser.getSelectedFile();

//        if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
//                FileInputStream f = new FileInputStream("AppletTest1.ser");
            URL source = new URL(getCodeBase(), "ZoomTest.ser");
//                       BufferedReader br =
//                    new BufferedReader
//                    (new InputStreamReader(source.openStream()));
            ObjectInputStream charts = new ObjectInputStream(source.openStream());
            serializedChart = (JFreeChart) charts.readObject();
        } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e.toString());
            e.printStackTrace();
        }
//        } else {
//            return null;
//        }
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
