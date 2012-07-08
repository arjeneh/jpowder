/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.jfreechart;

/**
 *
 * @author Toshiba
 */
public class PointAnno {

    private int pointName;
    private int mouseX, mouseY;
    private String internalFrameName;
    private String comment;
    private double x, y;

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
}
