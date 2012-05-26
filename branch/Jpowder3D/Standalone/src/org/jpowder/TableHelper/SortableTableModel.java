package org.jpowder.TableHelper;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.*;
import org.jpowder.util.StringUtil;

// TODO: Implement ComboboxModel so Combobox can use not just Table.
public class SortableTableModel extends DefaultTableModel implements ComboBoxModel {

    private int[] indexes = null;
    private TableSorter sorter;
    private ComboBoxModel comboBoxModel;

    public SortableTableModel() {
        super();
    }

    // TODO: Select only Name columns 
    // and the rest and give to ComboBoxModel i.e. exclude Path only
    public SortableTableModel(Vector rowData, Vector columnNames) {
        super(rowData, columnNames);
        //columnNames.removeElement("Path");
        // TODO: This can be an inner class with a specified field to be deleted.
        comboBoxModel = new DefaultComboBoxModel(columnNames);
    }

    public SortableTableModel(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
        // TODO: This can be an inner class with a specified field to be deleted.
        String[] stringArray = Arrays.copyOf(columnNames, columnNames.length, String[].class);
        comboBoxModel = new DefaultComboBoxModel(StringUtil.removeItemFromArray(stringArray, "Path"));
    }

    public Vector getColumnIdentifiers() {
        return columnIdentifiers;
    }

    // additional method to remove Column of the model.
    public void removeColumn(int columnIndex) {
        for (int r = 0; r < this.getRowCount(); r++) {
            Vector row = (Vector) dataVector.elementAt(r);
            row.removeElementAt(columnIndex);
        }
        columnIdentifiers.removeElementAt(columnIndex);
        fireTableStructureChanged();
    }

    @Override
    public Object getValueAt(int row, int col) {
        int rowIndex = row;
        if (getIndexes() != null) {
            rowIndex = getIndexes()[row];
        }
        return super.getValueAt(rowIndex, col);
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        int rowIndex = row;
        if (getIndexes() != null) {
            rowIndex = getIndexes()[row];
        }
        super.setValueAt(value, rowIndex, col);
        fireTableDataChanged();
    }

    // TODO: This should sort or reorder the model right away after click.
    public void sortByColumn(int column, boolean isAscent) {
        if (sorter == null) {
            sorter = new TableSorter(this);
        }
        sorter.sort(column, isAscent);
        fireTableDataChanged();

    }

    public int[] getIndexes() {
        int n = this.getRowCount();
        if (this.indexes != null) {
            if (this.indexes.length == n) {
                return this.indexes;
            }
        }

        this.indexes = new int[n];
        for (int i = 0; i < n; i++) {
            this.indexes[i] = i;
//            System.out.println("In getIndexes() SortableTableModel, index " + i
//                   + " data are: " + this.getValueAt(i, 0).toString());
        }

        return this.indexes;
    }

    /**
     * @param indexes the indexes to set
     */
    public void setIndexes(int[] indexes) {
        this.indexes = indexes;
    }

    /**
     * @return the sorter
     */
    public TableSorter getSorter() {
        return sorter;
    }

    /**
     * @param sorter the sorter to set
     */
    public void setSorter(TableSorter sorter) {
        this.sorter = sorter;
    }

    // ComboboxModel
    @Override
    public void setSelectedItem(Object anItem) {
        getComboBoxModel().setSelectedItem(anItem);
    }

    @Override
    public Object getSelectedItem() {
        return getComboBoxModel().getSelectedItem();
    }

    @Override
    public int getSize() {
        return getComboBoxModel().getSize();
    }

    @Override
    public Object getElementAt(int index) {
        return getComboBoxModel().getElementAt(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        getComboBoxModel().addListDataListener(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        getComboBoxModel().removeListDataListener(l);
    }

    /**
     * @return the comboBoxModel
     */
    public ComboBoxModel getComboBoxModel() {
        return comboBoxModel;
    }

    /**
     * @param comboBoxModel the comboBoxModel to set
     */
    public void setComboBoxModel(ComboBoxModel comboBoxModel) {
        this.comboBoxModel = comboBoxModel;
    }

    public void betterMoveRow(int start, int end, int dest) {
        int count = end - start;
        if (count <= 0) {
            return;
        }
        if (dest > start) {
            dest = Math.max(start, dest - count);
        }
        end--;
        this.moveRow(start, end, dest);
    }
//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        if (columnIndex == Filters.DOWNLOADED_COLUMN || columnIndex == Filters.SEEN_COLUMN) {
//            return Boolean.class;
//        } else if (columnIndex == Filters.EPISODE_NUMBER_COLUMN) {
//            return Integer.class;
//        } else if (columnIndex == Filters.SUBS_COLUMN) {
//            return Language.class;
//        } else {
//            return super.getColumnClass(columnIndex);
//        }
//    }
}
