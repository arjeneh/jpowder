package org.jpowder.tree;

import java.io.File;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;

/**
 * This class takes care of listing files' properties into a JTable.
 * It extends AbstractTableModel which handles JTable implementation.
 * But any thing that requires 2D information representation can utilise this model.
 *
 * Usage:
 * JpowderFileSystemTreeModel model = new JpowderFileSystemTreeModel();
 * DirectoryModel directoryModel = new DirectoryModel( (File)model.getRoot() );
 * JTable table = new JTable( directoryModel );
 *
 * ----------------------
 * DirectoryModel.java
 * ----------------------
 *
 * Changes
 * -------
 * 03-March-2010 : Version 1 (KP);
 *
 *
 * @author Kreecha Puphaiboon (KP)
 */
public class DirectoryModel extends AbstractTableModel {
    
    protected File directory;
    protected String[] children;
    protected int rowCount;
    protected Object dirIcon;
    protected Object fileIcon;

    public DirectoryModel() {
        init();
    }

    public DirectoryModel( File dir ) {
        init();
        directory = dir;
        children = dir.list();
        rowCount = children.length;
    }

    protected void init() {
        dirIcon = UIManager.get( "DirectoryPane.directoryIcon" );
        fileIcon = UIManager.get( "DirectoryPane.fileIcon" );
    }

    public void setDirectory( File dir ) {
        if ( dir != null ) {
            directory = dir;
            children = dir.list();
            rowCount = children.length;
        }
        else {
            directory = null;
            children = null;
            rowCount = 0;
        }
        
        fireTableDataChanged();
    }

    public int getRowCount() {
        return children != null ? rowCount : 0;
    }

    public int getColumnCount() {
        return children != null ? 3 :0;
    }

    public Object getValueAt(int row, int column){
        if ( directory == null || children == null ) {
            return null;
        }

        File fileSysEntity = new File( directory, children[row] );

        switch ( column ) {
        case 0:
            return fileSysEntity.isDirectory() ? dirIcon : fileIcon;
        case 1:
            return fileSysEntity.getName();
        case 2:
            if ( fileSysEntity.isDirectory() ) {
                return "--";
            }
            else {
                return new Long( fileSysEntity.length() );
            }
        default:
            return "";
        }
    }

    @Override
    public String getColumnName( int column ) {
        switch ( column ) {
        case 0:
            return "Type";
        case 1:
            return "Name";
        case 2:
            return "Bytes";
        default:
            return "unknown";
        }
    }

    @Override
    public Class getColumnClass( int column ) {
        if ( column == 0 ) {
            return getValueAt( 0, column).getClass();
        }
        else {
            return super.getColumnClass( column );
        }
    }
}                   

