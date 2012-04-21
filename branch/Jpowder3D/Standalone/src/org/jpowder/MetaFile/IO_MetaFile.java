/* ===========================================================
 * This file is part of Jpowder, see <http://www.jpowder.org/>
 * ===========================================================
 *
 * Jpowder is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jpowder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ---------
 * IO_MetaFile.java
 * ---------
 * (C) Copyright 2009-2010 STFC Rutherford Appleton Laboratories and
 * Kasem Bundit University.
 *
 * Author(s): Kreecha Puphaiboon, Computer Science Lecturer, Kasem Bundit University
M Arjeneh, ISIS, Rutherford Appleton Laboratory
 *            Anders Marvardsen, ISIS, Rutherford Appleton Laboratory
 *
 * File change history is stored at: <http://code.google.com/p/jpowder/source/browse>
 *
 */
package org.jpowder.MetaFile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;



import org.jpowder.TableHelper.HeaderListener;
import org.jpowder.TableHelper.SortButtonRenderer;
import org.jpowder.TableHelper.SortableTableModel;
import org.xml.sax.SAXException;

/**
 *
 * This class is for reading and writing data the 3d plot table into
 * a xml file.
 *
 * @author Kreecha
 * 14 April 2012.
 */
public class IO_MetaFile {

    private SortableTableModel sortableTableModel = null;
    private Vector<String> columnNames = null;
    private Vector<String> row = null;
    private JTable table = null;
    private HeaderListener headerListener = null;

    public IO_MetaFile(SortableTableModel defaultTableModel, Vector<String> columnNames, Vector<String> row) {
        this.sortableTableModel = defaultTableModel;
        this.columnNames = columnNames;
        this.row = row;
    }

    public IO_MetaFile(JTable table, Vector<String> columnNames, Vector<String> row) {
        this.table = table;
        this.sortableTableModel = (SortableTableModel) this.table.getModel();
        this.columnNames = columnNames;
        this.row = row;

    }

    /**
     * Reads the Xml, first check the if it does contain this key word
     * JpowderMetaFile if return Invalid xml file otherwise populate xml
     * data into the Jtable.
     * @param aFile
     */
    public void read_MetaFile(File aFile) {
        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(aFile);
            // normalize text representation
            doc.getDocumentElement().normalize();

            if (!doc.getDocumentElement().getNodeName().contains("JpowderMetaFile")) {
                javax.swing.JOptionPane.showMessageDialog(null, "Invalid XML file.");
                return;
            }

            //clear rows and columns
            this.sortableTableModel.setColumnCount(0);
            System.out.println("Column remains: " + this.sortableTableModel.getColumnCount());

            NodeList listOfColumns = doc.getElementsByTagName("Columns");
            int totalColumns = listOfColumns.getLength();
            //TODO : 7/04/2012
            //parse ',' again.
            //String string = getValue(el).substring(getValue(el).indexOf("["), getValue(el).indexOf("]")).replace("[", "");

            //String str[] = string.split(",");

            System.out.println("Total no of Columns of XML file : " + totalColumns);

            NodeList listOfRow = doc.getElementsByTagName("Row");
            int totalRow = listOfRow.getLength();
            System.out.println("Total no of Rows of XML file : " + totalRow);

            for (int i = 0; i < totalColumns; i++) {
                Node column = listOfColumns.item(i);
                if (column.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) column;
                    String string = getValue(el).substring(getValue(el).indexOf("["), getValue(el).indexOf("]")).replace("[", "");

                    String str[] = string.split(",");

                    for (String s : str) {
//                        System.out.println(s);
//                        columnNames.add(s);

                        sortableTableModel.addColumn(s.trim());
                    }
                }
            }

            //make sure zero row in the TableModel.
            sortableTableModel.setRowCount(0);

            for (int i = 0; i < totalRow; i++) {
                Node rows = listOfRow.item(i);

                if (rows.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) rows;
                    String stringRow = getValue(el).substring(getValue(el).indexOf("["), getValue(el).indexOf("]")).replace("[", "");
                    String str[] = stringRow.split(",");
                    sortableTableModel.addRow(str);
                }
            }

            //To refresh the drawer of table.
            SortButtonRenderer renderer = new SortButtonRenderer();
            TableColumnModel tableColumnModel = this.table.getColumnModel();
            for (int i = 0; i < this.sortableTableModel.getColumnCount(); i++) {
                //  set renderer to the column.
                tableColumnModel.getColumn(i).setHeaderRenderer(renderer);
            }

            JTableHeader header = this.table.getTableHeader();
            //assign the mouse listener to the column header.
            //header.addMouseListener(new HeaderListener(header, renderer));
            //assign the mouse listener to the column header.
            headerListener = new HeaderListener(header, renderer);
            headerListener.setIgnoreColumnName("path");
            header.addMouseListener(headerListener);

        } catch (SAXException ex) {
            Logger.getLogger(IO_MetaFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IO_MetaFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(IO_MetaFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            System.out.println("FINALLLLLLLLLLLLLLLLLLLLLLLY");
            return;
        }

    }

    /**
     * Saving data in the Jtable into a XML file.
     * @param aFile Filename to save the metafile to
     */
    public void save_MetaFile(File aFile) {
        try {

            for (int i = 0; i < this.sortableTableModel.getColumnCount(); i++) {
                //  clear rows.
                System.out.println("Column names are: " + this.sortableTableModel.getColumnName(i));

            }

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("JpowderMetaFile");
            document.appendChild(rootElement);

            Element columns = document.createElement("Columns");

//            columns.appendChild(document.createTextNode(" " + columnNames.get(1) + "," + columnNames.get(columnNames.size() - 1)));
            columns.appendChild(document.createTextNode("" + columnNames.toString()));
            rootElement.appendChild(columns);

//            for (int j = 0; j < sortableTableModel.getRowCount(); j++) {
//                vt.add((Vector<String>) sortableTableModel.getDataVector().get(j));
//
//            }

            for (int j = 0; j < sortableTableModel.getRowCount(); j++) {
                Element rows = document.createElement("Row");
//                metaFileRow.add(vt.get(j).get(1)+","+vt.get(j).get(vt.get(0).size()-1));
//                rows.appendChild(document.createTextNode(vt.get(j).get(1)+","+vt.get(j).get(vt.get(0).size()-1)));
                rows.appendChild(document.createTextNode(sortableTableModel.getDataVector().get(j).toString()));
                rootElement.appendChild(rows);
            }

//            System.out.println(metaFileRow.toString());
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(aFile);
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(IO_MetaFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(IO_MetaFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//            System.out.println("FINALLLLLLLLLLLLLLLLLLLLLLLY");
            return;
        }
    }

    /**
     * reads each node of a XML file.
     * @param eElement
     * @return node
     */
    private static String getValue(Element eElement) {
        NodeList nlList = eElement.getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }
}
