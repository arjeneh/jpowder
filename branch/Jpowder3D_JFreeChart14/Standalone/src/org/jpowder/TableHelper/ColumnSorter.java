/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.TableHelper;

import java.util.Comparator;

/**
 *
 * @author Toshiba
 */
public class ColumnSorter implements Comparator {
    boolean ascending;
    public ColumnSorter(boolean ascending) {
        this.ascending = ascending;
    }
    public int compare(Object a, Object b) {
        // Treat empty strains like nulls
        if (a instanceof String && ((String)a).length() == 0) {
            a = null;
        }
        if (b instanceof String && ((String)b).length() == 0) {
            b = null;
        }

        // Sort nulls so they appear last, regardless
        // of sort order
        if (a == null && b == null) {
            return 0;
        } else if (a == null) {
            return 1;
        } else if (b == null) {
            return -1;
        } else if (a instanceof Comparable) {
            if (ascending) {
                return ((Comparable)a).compareTo(b);
            } else {
                return ((Comparable)b).compareTo(a);
            }
        } else {
            if (ascending) {
                return a.toString().compareTo(b.toString());
            } else {
                return b.toString().compareTo(a.toString());
            }
        }
    }
}

