/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.tree;

import java.io.File;
import java.util.Comparator;

/**
 * Purpose: A class to sort File with different sorting types.
 * @author Kreecha Puphaiboon
 */
public class FileComparator implements Comparator {

    private SORTBY property;
    private Comparator comparator;

    public FileComparator(SORTBY t) {
        this.property = t;
    }

    public FileComparator() {
        comparator = new FileNameComparator();
    }

    public int compare(Object o1, Object o2) {

        // Get the file of the objct value.
        File value1 = (File) o1;
        File value2 = (File) o2;

        switch (this.property) {
            case FILE_NAME:
                FileNameComparator fnc = new FileNameComparator();
                return fnc.compare(value1, value2);
            case FILE_SIZE:
                FileSizeComparator fsc = new FileSizeComparator();
                return fsc.compare(value1, value2);
            default:
                comparator = new FileNameComparator();
                return comparator.compare(value1, value2);
        }

        //return comparator.compare(value1, value2);
    }
}
