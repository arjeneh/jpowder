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
 * DatasetPlotter.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.dataset;

import org.jpowder.jfreechart.FilesPlotter;
import java.util.Vector;

/**
 * Base class for (temporarely) holding the content of a powder diffraction
 * files and then create JFreeChart plots of such data
 *
 *
 */
public abstract class DatasetPlotter {

    protected DataSet dataset;

    public DatasetPlotter(DataSet d) {
    }

    public DatasetPlotter(Vector<DataSet> d) {

    }

    public abstract String description();

    /**
     * create a panel containing a chart of data of one or more powder files
     */
    public abstract org.jfree.chart.ChartPanel createPowderChart();

    /**
     * create DatasetPlotter and store the data that are can then be plotted
     * using the method createPowderChart()
     * @param datasets Contains the data of one or more powder diffraction files
     */
    public static DatasetPlotter createDatasetPlotter(Vector<DataSet> datasets) {
        if (datasets.size() >= 1) {
            return new FilesPlotter(datasets);

        } else {
            return null;
        }
    }

    /**
     * create DatasetPlotter and store the data that are can then be plotted
     * using the method createPowderChart()
     * @param datasets Contains the data of one powder diffraction file
     */
    public static DatasetPlotter createDatasetPlotter(DataSet dataset) {
        if (dataset != null) {
            return new FilesPlotter(dataset);
        } else {
            return null;
        }
    }
}

