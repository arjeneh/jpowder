package org.jpowder.TableHelper;

import java.util.*;

/**
 * @version 1.0 02/25/99
 */
public class TableSorter {

    private SortableTableModel model;

    public TableSorter(SortableTableModel model) {
        this.model = model;
    }

    //n2 selection
    public void sort(int column, boolean isAscent) {
        int n = model.getRowCount();
        int[] indexes = model.getIndexes();

        for (int i = 0; i < n - 1; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (isAscent) {
                    if (compare(column, j, k) < 0) {
                        k = j;
                    }
                } else {
                    if (compare(column, j, k) > 0) {
                        k = j;
                    }
                }
            }
            int tmp = indexes[i];
            indexes[i] = indexes[k];
            indexes[k] = tmp;
        }
    }

    // comparators
    public int compare(int column, int row1, int row2) {
        Object o1 = model.getValueAt(row1, column);
        Object o2 = model.getValueAt(row2, column);
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else {

            Class type = model.getColumnClass(column);
            if (type.getSuperclass() == Number.class) {
                return compare((Number) o1, (Number) o2);
            } else if (type == String.class) {
                //test natural order.
                return compare((String)o1, (String)o2);

                //original normal compare String.
                //return ((String) o1).compareTo((String) o2);
            } else if (type == Date.class) {
                return compare((Date) o1, (Date) o2);
            } else if (type == Boolean.class) {
                return compare((Boolean) o1, (Boolean) o2);
            } else {
                return ((String) o1).compareTo((String) o2);
            }
        }
    }

    public int compare(Number o1, Number o2) {
        double n1 = o1.doubleValue();
        double n2 = o2.doubleValue();
        if (n1 < n2) {
            return -1;
        } else if (n1 > n2) {
            return 1;
        } else {
            return 0;
        }
    }

    public int compare(Date o1, Date o2) {
        long n1 = o1.getTime();
        long n2 = o2.getTime();
        if (n1 < n2) {
            return -1;
        } else if (n1 > n2) {
            return 1;
        } else {
            return 0;
        }
    }

    public int compare(Boolean o1, Boolean o2) {
        boolean b1 = o1.booleanValue();
        boolean b2 = o2.booleanValue();
        if (b1 == b2) {
            return 0;
        } else if (b1) {
            return 1;
        } else {
            return -1;
        }
    }

    // this is used to compare string in natural order. CharAt() as well.
    public int compare(Object o1, Object o2)
    {
        String a = o1.toString();
        String b = o2.toString();

        int ia = 0, ib = 0;
        int nza = 0, nzb = 0;
        char ca, cb;
        int result;

        while (true)
        {
            // only count the number of zeroes leading the last number compared
            nza = nzb = 0;

            ca = charAt(a, ia);
            cb = charAt(b, ib);

            // skip over leading spaces or zeros
            while (Character.isSpaceChar(ca) || ca == '0')
            {
                if (ca == '0')
                {
                    nza++;
                }
                else
                {
                    // only count consecutive zeroes
                    nza = 0;
                }

                ca = charAt(a, ++ia);
            }

            while (Character.isSpaceChar(cb) || cb == '0')
            {
                if (cb == '0')
                {
                    nzb++;
                }
                else
                {
                    // only count consecutive zeroes
                    nzb = 0;
                }

                cb = charAt(b, ++ib);
            }

            // process run of digits
            if (Character.isDigit(ca) && Character.isDigit(cb))
            {
                if ((result = compareRight(a.substring(ia), b.substring(ib))) != 0)
                {
                    return result;
                }
            }

            if (ca == 0 && cb == 0)
            {
                // The strings compare the same. Perhaps the caller
                // will want to call strcmp to break the tie.
                return nza - nzb;
            }

            if (ca < cb)
            {
                return -1;
            }
            else if (ca > cb)
            {
                return +1;
            }

            ++ia;
            ++ib;
        }
    }

    public char charAt(String s, int i)
    {
        if (i >= s.length())
        {
            return 0;
        }
        else
        {
            return s.charAt(i);
        }
    }

    // compareRight uses charAt()
    public int compareRight(String a, String b)
    {
        int bias = 0;
        int ia = 0;
        int ib = 0;

        // The longest run of digits wins. That aside, the greatest
        // value wins, but we can't know that it will until we've scanned
        // both numbers to know that they have the same magnitude, so we
        // remember it in BIAS.
        for (;; ia++, ib++)
        {
            char ca = charAt(a, ia);
            char cb = charAt(b, ib);

            if (!Character.isDigit(ca) && !Character.isDigit(cb))
            {
                return bias;
            }
            else if (!Character.isDigit(ca))
            {
                return -1;
            }
            else if (!Character.isDigit(cb))
            {
                return +1;
            }
            else if (ca < cb)
            {
                if (bias == 0)
                {
                    bias = -1;
                }
            }
            else if (ca > cb)
            {
                if (bias == 0)
                    bias = +1;
            }
            else if (ca == 0 && cb == 0)
            {
                return bias;
            }
        }
    }
}
