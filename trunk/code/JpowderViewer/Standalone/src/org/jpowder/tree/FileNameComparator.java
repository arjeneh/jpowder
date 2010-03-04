/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.tree;

import java.io.File;
import java.text.Collator;
import java.util.Comparator;

/**
 *
 * @author Toshiba
 */
public class FileNameComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        File a = (File)o1;
        File b = (File)o2;

        Collator collator = Collator.getInstance();
            if (a.isDirectory() && b.isFile()) return -1;
            else if (a.isFile() && b.isDirectory()) return +1;

            int result = collator.compare(a.getName(), b.getName());

            System.out.println("Result is: " + result
                    + " File a: " +  a.getName()
                    + " File b: " +  b.getName());

            if (result != 0) return result;

            result = collator.compare(
                    a.getAbsolutePath(), b.getAbsolutePath());
            return result;
    }

}
