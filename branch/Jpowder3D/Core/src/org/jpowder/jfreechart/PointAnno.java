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
 * BalloonFrame.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s):  Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
 * Created on 30-Jun-2012, 13:16:21
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.jfreechart;

import org.jpowder.Annotation.BalloonFrame;


public class PointAnno {

    private int pointName;
    private int mouseX, mouseY;
    private String internalFrameName;
    private String comment;
    private double x, y;
    private BalloonFrame ballonFrame;

    public PointAnno(double x_value, double y_value) {
        this.x = x_value;
        this.y = y_value;

        }

    public PointAnno(double x_value, double y_value, String frameName) {
        this.x = x_value;
        this.y = y_value;
        internalFrameName = frameName;
    }

    public PointAnno(int name, int mousX, int mousY, double x_value, double y_value, String frameName) {
        this.pointName = name;
        this.mouseX = mousX;
        this.mouseY = mousY;
        this.x = x_value;
        this.y = y_value;
        this.internalFrameName = frameName;
        this.comment = "";
        //System.out.println("X and y values are: " + this.x + " and " + this.y + " ------------- ");
    }

    /**
     * <P>Here, clear the contents of a specific annotation.
     */
    public void clear() {
        this.setX(0);
        this.setY(0);
        this.comment = null;
    }

    public double distance(PointAnno that) {
        double xDiff = getX() - that.getX();
        double yDiff = getY() - that.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
        //display the data to the BalloonFrame.
        if (this.ballonFrame != null){
            ballonFrame.setCommentText(this.comment);
        }
                
    }

    /**
     * Intended only for debugging.
     *
     * <P>Here, the contents of every field are placed into the result, with
     * one field per line.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append(this.getClass().getName() + " Object {" + NEW_LINE);
        result.append(" Name: " + this.getPointName() + NEW_LINE);
        result.append(" Mouse X: " + this.getMouseX() + NEW_LINE);
        result.append(" Mouse Y: " + this.getMouseY() + NEW_LINE);
        result.append(" FrameName: " + getInternalFrameName() + NEW_LINE);
        result.append(" Data on X: " + this.getX() + NEW_LINE);
        result.append(" Data on Y: " + this.getY() + NEW_LINE);
        result.append(" comment: " + this.getComment() + NEW_LINE);
        result.append("}");

        return result.toString();
    }

    /**
     * @return the internalFrameName
     */
    public String getInternalFrameName() {
        return internalFrameName;
    }

    /**
     * @param internalFrameName the internalFrameName to set
     */
    public void setInternalFrameName(String internalFrameName) {
        this.internalFrameName = internalFrameName;
    }

    /**
     * @return the pointName
     */
    public int getPointName() {
        return pointName;
    }

    /**
     * @param pointName the pointName to set
     */
    public void setPointName(int pointName) {
        this.pointName = pointName;
    }

    /**
     * @return the mouseX
     */
    public int getMouseX() {
        return this.mouseX;
    }

    /**
     * @param mouseX the mouseX to set
     */
    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    /**
     * @return the mouseY
     */
    public int getMouseY() {
        return this.mouseY;
    }

    /**
     * @param mouseY the mouseY to set
     */
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    /**
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the ballonFrame
     */
    public BalloonFrame getBallonFrame() {
        return ballonFrame;
    }

    /**
     * @param ballonFrame the ballonFrame to set
     */
    public void setBallonFrame(BalloonFrame ballonFrame) {
        this.ballonFrame = ballonFrame;
    }
}
