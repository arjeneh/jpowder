/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditAnnotationFrame.java
 *
 * Created on 02-Jun-2012, 11:09:08
 */
package org.jpowder.jfreechart;

import Annotation.IMapObserver;
import Annotation.IMapSubject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jpowder.util.HashMapHelper;
import org.jpowder.util.StringUtil;

/**
 * Singleton Class for handling comments/remarks for the dataset within the JPowderFrame.
 * @author Toshiba
 */
public class EditAnnotationFrame extends javax.swing.JFrame implements IMapSubject {

    //Singleton instance.
    private static EditAnnotationFrame instance = null;
    private int mouseX, mouseY;//in case for the future.
    private double valueX, valueY;
    private int annoNumber = 0;
    //Map of Integer and Annotation
    private HashMap<Integer, PointAnno> annoMap = new HashMap<Integer, PointAnno>();
    private Map<Integer, PointAnno> synMap = Collections.synchronizedMap(annoMap);
    //Observers
    private List observers = Collections.synchronizedList(new ArrayList());
    // Name/Title of the Frame
    private static final String title = "Annotation number: ";

    private static final Object classLock = EditAnnotationFrame.class;
    /**
     *
     * @return
     */
    public synchronized static final EditAnnotationFrame getInstance() {
        synchronized (classLock) {
            if (instance == null) {
                instance = new EditAnnotationFrame();
            }
            return instance;
        }
    }

    /**
     * Creates new form EditAnnotationFrame
     */
    private EditAnnotationFrame() {
        initComponents();
    }

    /**
     * Singleton private constructor.
     * @param mouseX
     * @param mouseY
     * @param ValueX
     * @param ValueY
     */
    private EditAnnotationFrame(int mouseX, int mouseY, double ValueX, double ValueY) {
        initComponents();

        this.mouseX = mouseX;
        this.mouseY = mouseY;

        this.valueX = ValueX;
        this.valueY = ValueY;
        annoNumber++;

        setTitle(title + annoNumber);
        commentTextArea.setText("Value of X is: " + this.valueX + " , Value of Y is: " + this.valueY);
        //annoMap.put(annoNumber, new PointAnno(this.valueX, this.valueY));
        synMap.put(annoNumber, new PointAnno(this.valueX, this.valueY));
        // TODO notify the number of Annotation in the Map to all listeners.
        //if AnnoPanel is not null, then addObserver?

    }

    /**
     * <p> add annotation when start.
     * @param mouseX
     * @param mouseY
     * @param ValueX
     * @param ValueY
     * @param internalFrameName
     */
    public void addAnnotation(int mouseX, int mouseY, double ValueX, double ValueY, String internalFrameName) {
        annoNumber++;
        setTitle(title + annoNumber);
        PointAnno pa = new PointAnno(annoNumber, mouseX, mouseY, ValueX, ValueY, internalFrameName);
        synMap.put(annoNumber, pa);

        getCommentTextArea().setText("Name: " + annoNumber + ", Mouse X: " + pa.getMouseX() + ", Mouse Y " + pa.getMouseY() +
                ", Value of X is: " + pa.getX() + " , Value of Y is: " + pa.getY() + ", frame name is: " + pa.getInternalFrameName());

    }

    @Override
    protected void finalize() {
        setAnnoNumber(getAnnoNumber() - 1); // decrement static count of Annotation
    } // end method finalize

    public synchronized static final EditAnnotationFrame getInstance(int mouseX, int mouseY, double ValueX, double ValueY) {

        if (instance == null) {
            instance = new EditAnnotationFrame(mouseX, mouseY, ValueX, ValueY);
        }

        return instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void addObserver(IMapObserver o) {
        observers.add(o);
    }

    public void removeObserver(IMapObserver o) {
        observers.remove(o);
    }

    public Map getStatusUpdate() {
        return synMap;
    }

    /**
     *
     * @param state
     */
    public void setStatusUpdate(HashMap state) {
        this.annoMap = state;
        notifyObservers();
    }

    public void notifyObservers() {
        Iterator i = observers.iterator();
        while (i.hasNext()) {
            IMapObserver o = (IMapObserver) i.next();
            o.updateMap(this);
        }
    }

    /**
     * Intended only for debugging.
     *
     * <P>Here, the contents of every field are placed into the result, with
     * one item per line.
     */
    public void printAllAnno() {
        // Get a set of the entries
        Set set = annoMap.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        System.out.println("---------------------------------------------------");
        // Display elements
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }//while
        System.out.println("---------------------------------------------------");
    }

    /**
     * <P>Here, the contents of a specific commentery annotation are placed into the HashMap
     */
    public void editAnnotation(Integer a, String s) {
        PointAnno pa = synMap.get(a);
        pa.setComment(s);
        System.out.println("editAnnotation is called: " + pa.toString());
        getAnnoMap().put(a, pa);
    }

    /**
     * Use for displaying the screen for editing PointAnnotation. It is called by
     * BalloonFrame.java when user clicks edit
     * @param pa
     */
    public void showAnnotation(PointAnno pa) {
        HashMapHelper.getKeyByValue(synMap, pa);
        boolean bFound = synMap.containsValue(pa);
        
        System.out.println("found tru/false: " + bFound + " pa name is: " +
                pa.getPointName() + " and Comment is: " + pa.getComment());

        System.out.println("Synchronized map is :" + synMap);

        PointAnno point = synMap.get(HashMapHelper.getKeyByValue(synMap, pa));

        this.getCommentTextArea().setText(point.getComment());
        this.setVisible(true);
    }

    /**
     * <P>Here, the contents of a specific commentery annotation is deleted off the HashMap
     */
    public void removeAnnotation(Integer a) {
        synMap.remove(a);
        notifyObservers();
    }

    /**
     * @return the annoMap
     */
    public Map getAnnoMap() {
        return synMap;
    }

    /**
     * <P>Here, the contents of a PointAnno is returned.
     */
    public PointAnno findValue(int Name) {
        for (Object o : annoMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;

            if (entry.getKey().equals(Name)) {
                return (PointAnno) entry.getValue();
            }
        }
        return null;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        commentTextArea = new javax.swing.JTextArea();
        cancelButton = new javax.swing.JButton();
        okButtone = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Annotation");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        commentTextArea.setColumns(20);
        commentTextArea.setLineWrap(true);
        commentTextArea.setRows(5);
        commentTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(commentTextArea);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButtone.setText("OK");
        okButtone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtoneActionPerformed(evt);
            }
        });

        jLabel1.setText("Annotation Text:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(cancelButton)
                        .addGap(67, 67, 67)
                        .addComponent(okButtone, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButtone)
                    .addComponent(cancelButton))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        //Disregard any change to the text and close the wondow.
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtoneActionPerformed

        //search for the name of PointAnno.
        //need to insert some name to the PointAnno when loading wihtout being seen.

        //find Annotation by the tile ID from HashMap - wrong. Can not do.
        String[] titleParse = StringUtil.getSplit(this.getTitle(), ":");
        String idStr = titleParse[titleParse.length - 1];
        System.out.println("ID is: " + idStr);

        PointAnno pa = synMap.get(Integer.parseInt(idStr.trim()));

        //Save any change to the text and close the wondow.
        if (pa == null) {
            System.out.println("PointAnno is null");
            this.dispose();
            return;
        }

        pa.setComment(getCommentTextArea().getText());
        notifyObservers(); //printAllAnno();
        this.dispose();
        //
    }//GEN-LAST:event_okButtoneActionPerformed

    /**
     * @return the annoNumber
     */
    public int getAnnoNumber() {
        return annoNumber;
    }

    /**
     * @return the current number of annotation
     */
    public int getCurrentAnnoMapSize() {
        return this.synMap.size();
    }

    /**
     * @param aAnnoNumber the annoNumber to set
     */
    public void setAnnoNumber(int aAnnoNumber) {
        annoNumber = aAnnoNumber;
    }

    /**
     * @return the commentTextArea
     */
    public javax.swing.JTextArea getCommentTextArea() {
        return commentTextArea;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new EditAnnotationFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextArea commentTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButtone;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the map
     */
    public Map getMap() {
        return synMap;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.synMap = map;
    }

    public void setStatusUpdate(Map state) {
        this.synMap = state;
        notifyObservers();
    }
}



