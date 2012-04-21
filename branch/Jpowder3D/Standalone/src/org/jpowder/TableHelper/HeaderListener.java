/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.TableHelper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Toshiba
 */
public class HeaderListener extends MouseAdapter {

    private JTableHeader header;
    private SortButtonRenderer renderer;
    private String ignoreColumnName = null;

    public HeaderListener(JTableHeader header, SortButtonRenderer renderer) {
        this.header = header;
        this.renderer = renderer;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // we dont want to sort a "Path" column
        int col = header.columnAtPoint(e.getPoint());
        //
        Object selectedColumn = header.getColumnModel().getColumn(col).getHeaderValue();

        System.out.println("String name is: " + selectedColumn.toString());
        //if (selectedColumn.toString().equalsIgnoreCase("path")) {
        if (selectedColumn.toString().equalsIgnoreCase(ignoreColumnName)) {
            System.out.println(" ---- EQUAL -------- ");
            return;
        }

        //end ignore Path column.
        int sortCol = header.getTable().convertColumnIndexToModel(col);
        renderer.setPressedColumn(col);
        renderer.setSelectedColumn(col);
        header.repaint();

        if (header.getTable().isEditing()) {
            header.getTable().getCellEditor().stopCellEditing();
        }

        boolean isAscent;
        if (SortButtonRenderer.DOWN == renderer.getState(col)) {
            isAscent = true;
        } else {
            isAscent = false;
        }
        ((SortableTableModel) header.getTable().getModel()).sortByColumn(sortCol, isAscent);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = header.columnAtPoint(e.getPoint());
        renderer.setPressedColumn(-1);                // clear
        header.repaint();
    }

    /**
     * @return the ignoreColumnName
     */
    public String getIgnoreColumnName() {
        return ignoreColumnName;
    }

    /**
     * @param ignoreColumnName the ignoreColumnName to set
     */
    public void setIgnoreColumnName(String ignoreColumnName) {
        this.ignoreColumnName = ignoreColumnName;
    }
}
