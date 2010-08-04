/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpowder.dataset;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

/**
 *
 * @author qyt21516
 */
public class IO_MetaFile {

    private DefaultTableModel defaultTableModel;
    private Vector<String> columnNames = new Vector<String>();
    private Vector<String> row = new Vector<String>();

    public IO_MetaFile(DefaultTableModel defaultTableModel,
            Vector<String> columnNames, Vector<String> row) {
        this.defaultTableModel = defaultTableModel;
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
            NodeList listOfColumns = doc.getElementsByTagName("Columns");
            int totalColumns = listOfColumns.getLength();
            System.out.println("Total no of Columns : " + totalColumns);



            NodeList listOfRow = doc.getElementsByTagName("Row");
            int totalRow = listOfRow.getLength();
            System.out.println("Total no of Rows : " + totalRow);

            for (int i = 0; i < totalColumns; i++) {
                Node column = listOfColumns.item(i);

                if (column.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) column;

                    String string = getValue(el).substring(getValue(el).indexOf("["), getValue(el).indexOf("]")).replace("[", "");

                    String str[] = string.split(",");

                    for (String s : str) {
//                        System.out.println(s);
//                        columnNames.add(s);
                        defaultTableModel.addColumn(s);

                    }

                }
            }
            for (int i = 0; i < totalRow; i++) {
                Node rows = listOfRow.item(i);

                if (rows.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) rows;
                    String stringRow = getValue(el).substring(getValue(el).indexOf("["), getValue(el).indexOf("]")).replace("[", "");
                    String str[] = stringRow.split(",");

//                    for (String s : str) {
//                     System.out.println(s);
                    defaultTableModel.addRow(str);
//                    }
//
//                    defaultTableModel.addRow(row);

                }
            }


        } catch (SAXException ex) {
            Logger.getLogger(IO_MetaFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IO_MetaFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(IO_MetaFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("FINALLLLLLLLLLLLLLLLLLLLLLLY");
            return;
        }

    }

    /**
     * Saving data in the Jtabel into a XML file.
     * @param aFile
     */
    public void save_MetaFile(File aFile) {
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("JpowderMetaFile");
            document.appendChild(rootElement);

            Element columns = document.createElement("Columns");

//            columns.appendChild(document.createTextNode(" " + columnNames.get(1) + "," + columnNames.get(columnNames.size() - 1)));
            columns.appendChild(document.createTextNode("" + columnNames.toString()));
            rootElement.appendChild(columns);

//            for (int j = 0; j < defaultTableModel.getRowCount(); j++) {
//                vt.add((Vector<String>) defaultTableModel.getDataVector().get(j));
//
//            }

            for (int j = 0; j < defaultTableModel.getRowCount(); j++) {
                Element rows = document.createElement("Row");
//                metaFileRow.add(vt.get(j).get(1)+","+vt.get(j).get(vt.get(0).size()-1));
//                rows.appendChild(document.createTextNode(vt.get(j).get(1)+","+vt.get(j).get(vt.get(0).size()-1)));
                rows.appendChild(document.createTextNode(defaultTableModel.getDataVector().get(j).toString()));
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
