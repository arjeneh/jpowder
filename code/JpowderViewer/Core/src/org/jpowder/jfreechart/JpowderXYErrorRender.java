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
 * Axis.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Original Author:  M Arjeneh
 * Contributor(s):   Dan Badham;
 *                   
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import org.jfree.chart.renderer.xy.XYErrorRenderer;

/**
 *
 * @author M Arjeneh
 */
/**
 * A line and shape renderer that can also display x and/or y-error values.
 */
public class JpowderXYErrorRender extends XYErrorRenderer {

    public JpowderXYErrorRender() {
        this.setBaseLinesVisible();
        this.setBaseShapesVisible();
        this.setDrawXError();
        this.setDrawYError();
    }

    /**
     * this method sets the line visible
     */
    public void setBaseLinesVisible() {
        super.setBaseLinesVisible(true);
    }

    /**
     * this method sets the marker visible
     */
    public void setBaseShapesVisible() {
        super.setBaseShapesVisible(false);
    }

    /**
     * set Y Error bar off.
     */
    public void setDrawYError() {
        super.setDrawYError(false);
    }

    /**
     * set X Error bar off.
     */
    public void setDrawXError() {
        super.setDrawXError(false);
    }
}


