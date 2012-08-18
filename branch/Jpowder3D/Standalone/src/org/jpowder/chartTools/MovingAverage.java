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
 * MovingAverage.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 *             Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.chartTools;

/**
 * Seperate this class out just in case one day we want different implementation for Smoothing
 * or large dataset and we have issues.
 *
 * @author Kreecha
 */
public class MovingAverage {

    private double average;
    private long count;

    public MovingAverage() {
        count = 0;
        average = 0;
    }

    public MovingAverage(long count, double average) {
        this.count = count;
        this.average = average;
    }

    public void add(double value) {
        if (count == 0) {
            average = value;
            count++;
        } else {
            average = ((average * count) + value) / ++count;
        }
    }

    public void remove(double value) {
        if (count == 0) {
            throw new AssertionError("no value to remove");
        } else {
            average = ((average * count) - value) / --count;
        }
    }

    public double getAverage() {
        return average;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return Double.toString(average);
    }
}
