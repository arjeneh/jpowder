/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.TableHelper;

/**
 *
 * @author Toshiba
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;

/* Provides a RowHeader implementation for a JTable by using a JList.

Provides an additional capability of modifying table rows and columns through a
JPopupMenu, as well as Cut/Copy/Paste functionality for JTables.

The modifications include row and column removal and addition. This
feature may be disabled using the setUsePopupTableModifiers(..) method. The popup
menu may be completely removed using the setUsePopupMenu(..) method.

An ActionListener may be added for the RowHeader for listening to the Cut/Copy/Paste
menu items.
 */
public class ListRowHeader extends JList {

    JTable table;
    RowPopupMenu popup;
    int initialIndex = 1;
    boolean usesPopupMenu = true;
    MouseAdapter popupAdapter;

    public ListRowHeader(JTable t) {
        super();
        this.table = t;
        setModel(new TableListModel(table));

        setFixedCellWidth(30);
        setFixedCellHeight(table.getRowHeight());
        setCellRenderer(new RowHeaderRenderer(table));
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        popup = new RowPopupMenu(table);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                initialIndex = locationToIndex(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //int index = locationToIndex( e.getPoint() );

                //if(e.getButton() == MouseEvent.BUTTON1) {
                //if(e.isPopupTrigger()){
                //	setSelectedIndex(locationToIndex( e.getPoint() ));
                //	popup.show( (ListRowHeader)e.getSource() , e.getX(), e.getY() );
                //} else {
                int min = getMinSelectionIndex(), max = getMaxSelectionIndex();
                table.setRowSelectionInterval(min, max);
                table.requestFocus();
                //}
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int currentIndex = locationToIndex(e.getPoint());

                setSelectionInterval(initialIndex, currentIndex);
            }
        });

        popupAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (getSelectedIndex() != table.getSelectedRow()) {
                    clearSelection();
                    table.setColumnSelectionAllowed(true);
                    if (!table.getRowSelectionAllowed()) {
                        table.setRowSelectionAllowed(true);
                    }
                    //getParent().repaint();
                }

                if (e.getButton() != MouseEvent.BUTTON1) {
                    //int index = locationToIndex( e.getPoint() );
                    boolean rowSel = false, colSel = false;
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    int[] selRows = table.getSelectedRows();
                    int[] selCols = table.getSelectedColumns();

                    for (int i = 0; i < selRows.length; i++) {
                        for (int j = 0; j < selCols.length; j++) {
                            if (selRows[i] == row) {
                                rowSel = true;
                            }

                            if (selCols[j] == col) {
                                colSel = true;
                            }

                            if (colSel && rowSel) {
                                break;
                            }
                        }
                    }
                    if (!rowSel) {
                        table.setRowSelectionInterval(row, row);
                    }

                    if (!colSel) {
                        table.setColumnSelectionInterval(col, col);
                    }

                    table.requestFocus();
                    popup.show(table, e.getX(), e.getY());
                }
            }
        };

        //if(usePopupMenu())
        //	table.addMouseListener( popupAdapter );
        setUsePopupMenu(true);

        table.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                //if(getParent() != null) {
                //	updateUI();
                //}
                if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE) {
                    revalidate();
                    repaint();
                }
            }
        });

    }

    public ListRowHeader(JTable table, boolean usePopupMenu) {
        this(table);
        setUsePopupMenu(usePopupMenu);
    }

    /**
     * Creates a new ListRowHeader, initialises the popup menu and assigns an
     * ActionListener for the cut, copy, and paste functions of the the popup
     * menu.
     **/
    public ListRowHeader(JTable table, ActionListener pop) {
        this(table);
        //popup.addCutCopyPasteListener(pop);
        popup.setCutCopyPasteListener(pop);
    }

    public void setUsePopupMenu(boolean use) {
        usesPopupMenu = use;
        if (!usesPopupMenu) {
            table.removeMouseListener(popupAdapter);
        } else {
            boolean hasListener = false;
            MouseListener[] listeners = table.getMouseListeners();
            for (int i = 0; i < listeners.length; i++) {
                if (listeners[i] == popupAdapter) {
                    hasListener = true;
                }
            }

            if (!hasListener) {
                table.addMouseListener(popupAdapter);
            }
        }
    }

    public boolean usesPopupMenu() {
        return usesPopupMenu;
    }

    public void addCopyOptionsInPopupMenu(boolean b) {
        popup.setUseCopyActions(b);
        rebuildPopupMenu();
    }

    public void addCutOptionInPopupMenu(boolean b) {
        popup.setAddCutOption(b);
        rebuildPopupMenu();
    }

    public void setUsePopupTableModifiers(boolean b) {
        popup.setUseTableModifiers(b);
        rebuildPopupMenu();
    }

    public void rebuildPopupMenu() {
        popup.removeAll();
        System.gc();
        popup.initializeMenus();
        popup.validate();
    }

    public JPopupMenu getPopupMenu() {
        return popup;
    }

    class TableListModel extends AbstractListModel {

        JTable table;

        public TableListModel(JTable table) {
            this.table = table;
        }

        @Override
        public int getSize() {
            return table.getModel().getRowCount();
        }

        @Override
        public Object getElementAt(int index) {
            return "" + (index + 1) + " ";
        }
    }

    class RowHeaderRenderer extends JLabel implements ListCellRenderer {

        /**
         * Constructor creates all cells the same
         * To change look for individual cells put code in getListCellRendererComponent
         * method
         **/
        JTable table;
        JTableHeader header;

        RowHeaderRenderer(JTable table) {
            this.table = table;
            header = table.getTableHeader();

            setOpaque(true);
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setHorizontalAlignment(RIGHT);
            setForeground(header.getForeground());
            setBackground(header.getBackground());
            //setFont(header.getFont());
            setFont(new Font("Verdana", Font.BOLD, 10));
        }

        /**
         * Returns the JLabel after setting the text of the cell
         **/
        public Component getListCellRendererComponent(JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            setText((value == null) ? "" : value.toString());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
                setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                if (table.getColumnSelectionAllowed()) {
                    table.setColumnSelectionAllowed(false);
                }
            } else {
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            }

            setPreferredSize(new Dimension(30, table.getRowHeight(index)));


            return this;
        }
    }

    class RowPopupMenu extends JPopupMenu implements ActionListener {

        JMenuItem addCol, insCol, remCol, addRow, insRow, remRow, cut, copy, paste;
        JTable table;
        ActionListener popupListener;
        boolean addCutOption = true;
        boolean useTableModifiers = true, useCopyActions = true;

        public RowPopupMenu(JTable table) {
            super();
            this.table = table;
            initializeMenus();
        }

        public void initializeMenus() {
            addCol = customMenuItem("Column", this);
            insCol = customMenuItem("Column", this);
            remCol = customMenuItem("Column", this);
            addRow = customMenuItem("Row", this);
            insRow = customMenuItem("Row", this);
            remRow = customMenuItem("Row", this);

            JMenu append = customMenu("Append");
            append.add(addCol);
            append.add(addRow);

            JMenu insert = customMenu("Insert");
            insert.add(insCol);
            insert.add(insRow);

            JMenu delete = customMenu("Delete");
            delete.add(remCol);
            delete.add(remRow);



            if (getUseCopyActions()) {
                cut = customMenuItem("Cut", null, KeyStroke.getKeyStroke("ctrl X"));

                if (getAddCutOption()) {
                    add(cut);
                }

                add(copy = customMenuItem("Copy", null, KeyStroke.getKeyStroke("ctrl C")));
                add(paste = customMenuItem("Paste", null, KeyStroke.getKeyStroke("ctrl P")));
            }


            if (useTableModifiers()) {
                addSeparator();
                add(append);
                add(insert);
                add(delete);
            }

            if (popupListener != null) {
                addCutCopyPasteListener(popupListener);
            }
        }

        public JMenuItem customMenuItem(String label, ActionListener a) {
            JMenuItem custom = new JMenuItem(label);
            custom.setFont(new Font("Verdana", Font.PLAIN, 10));
            custom.setActionCommand(label);
            if (a != null) {
                custom.addActionListener(a);
            }

            return custom;
        }

        public JMenuItem customMenuItem(String label, ActionListener a, KeyStroke shortcut) {
            JMenuItem custom = customMenuItem(label, a);
            custom.setAccelerator(shortcut);

            return custom;
        }

        public JMenu customMenu(String label) {
            JMenu custom = new JMenu(label);
            custom.setFont(new Font("Verdana", Font.PLAIN, 10));

            return custom;
        }

        public void addRow(JTable table) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            model.addRow(new Vector<Object>());
        }

        public void insertRow(JTable table) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            int[] index = table.getSelectedRows();
            for (int i = 0; i < index.length; i++) {
                model.insertRow(index[i], new Vector<Object>());
            }
        }

        public void removeRow(JTable table) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            int[] index = table.getSelectedRows();
            //int[] interval = table.getRowSelectionInterval();

            for (int i = index.length - 1; i >= 0; i--) {
                if (table.getRowCount() != 1) {
                    model.removeRow(index[i]);
                } else {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        table.setValueAt("", i, j);
                    }
                }
            }

            clearSelection();
        }

        public void insertColumn(JTable table, int index) {
            addColumn(table);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            DefaultTableColumnModel cmodel = (DefaultTableColumnModel) table.getColumnModel();

            for (int i = model.getColumnCount() - 1; i > index; i--) {
                for (int j = 0; j < model.getRowCount(); j++) {
                    model.setValueAt(table.getValueAt(j, i - 1), j, i);

                    if (i - 1 == index) {
                        model.setValueAt("", j, index);
                    }
                }
            }

            for (int i = cmodel.getColumnCount() - 1; i > index; i--) {
                cmodel.getColumn(i).setHeaderValue(cmodel.getColumn(i - 1).getHeaderValue());

                if (i - 1 == index) {
                    cmodel.getColumn(index).setHeaderValue("Column");
                }
            }
        }

        public void addColumn(JTable table) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addColumn("Column");
        }

        public void removeColumn(JTable table, int index) {
            DefaultTableColumnModel model = (DefaultTableColumnModel) table.getColumnModel();
            model.addColumnModelListener(table);
            model.removeColumn(model.getColumn(index));

            DefaultTableModel tmodel = (DefaultTableModel) table.getModel();

            if (index != tmodel.getColumnCount() - 1) {
                for (int i = 0; i < tmodel.getRowCount(); i++) {
                    for (int j = index; j < tmodel.getColumnCount() - 1; j++) {
                        tmodel.setValueAt(tmodel.getValueAt(i, j + 1), i, j);
                    }
                }
            }

            if (model.getColumnCount() != 0) {
                tmodel.setColumnCount(model.getColumnCount());
            } else {
                tmodel.setColumnCount(1);
            }
        }

        public void setAddCutOption(boolean b) {
            addCutOption = b;
        }

        public boolean getAddCutOption() {
            return addCutOption;
        }

        public void addCutCopyPasteListener(ActionListener a) {
            if (getAddCutOption()) {
                cut.addActionListener(a);
            }

            copy.addActionListener(a);
            paste.addActionListener(a);
        }

        public void setCutCopyPasteListener(ActionListener pop) {
            popupListener = pop;
            removeAll();
            System.gc();
            initializeMenus();
            validate();
        }

        public void setUseTableModifiers(boolean b) {
            useTableModifiers = b;
        }

        public boolean useTableModifiers() {
            return useTableModifiers;
        }

        public void setUseCopyActions(boolean b) {
            useCopyActions = b;
        }

        public boolean getUseCopyActions() {
            return useCopyActions;
        }

        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == addRow) {
                addRow(table);
            }

            if (source == insRow) {
                insertRow(table);
            }

            if (source == remRow) {
                removeRow(table);
            }

            if (source == addCol) {
                addColumn(table);
            }

            if (source == insCol) {
                insertColumn(table, table.getSelectedColumn());
            }

            if (source == remCol) {
                int[] cols = table.getSelectedColumns();
                for (int i = cols.length - 1; i >= 0; i--) {
                    removeColumn(table, cols[i]);
                }
            }
        }
    }
}
