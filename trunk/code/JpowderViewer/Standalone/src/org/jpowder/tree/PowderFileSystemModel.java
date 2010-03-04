


package org.jpowder.tree;

import javax.swing.tree.TreeModel;

/**
 * This class takes care of the event listener lists required by TreeModel.
 * It also adds "fire" methods that call the methods in TreeModelListener.
 * Look in FileSystemTreeModelSupport for all of the pertinent code.
 * 
 * @author Toshiba
 */
public abstract class PowderFileSystemModel extends FileSystemTreeModelSupport implements TreeModel {

}
