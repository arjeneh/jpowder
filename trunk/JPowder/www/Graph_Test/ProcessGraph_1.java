/*
 * @author Kreecha Puphaiboon
 * @version 1.00 2006/12/21
 *
 * This JAVA file calls ReadFile.java and colouring the graph
 * then computes the distance and cost.
 *
 * @I need to do a maximum matching.
 *
 * A basic extension of the java.applet.Applet class
 */

import java.awt.*;
import java.applet.*;
import java.util.*;

import drasys.or.graph.*;
import drasys.or.graph.sp.*;

public class ProcessGraph_1 extends Applet {
	
	void destChoice_Action(Event event) {
        selectedDestChoice();
	}

	void sourceChoice_Action(Event event) {
        selectedSourceChoice();
	}

    Object destKey;
    Object sourceKey;
    SingleVertexI    algorithm;

    public void displayPath(Object src, Object dst){
        if(src.equals(dst)){
            pathLabel.setText(String.valueOf(src));
            costLabel.setText("0.0");
            return;
        }
        try{
            algorithm.setCandidate(false);
            algorithm.setCandidate(dst, true);
            algorithm.generatePathsFrom(src);
            VertexI nearest = algorithm.getNearestCandidate();
            double cost = algorithm.getEdgeValue(nearest).getCost(false);
            costLabel.setText(String.valueOf(cost));

            Vector path = algorithm.getPath(nearest);
            Enumeration enum_Obj = path.elements();
            
            VertexI vertex = (VertexI)enum_Obj.nextElement();
            String pathStr = String.valueOf(vertex.getKey());

            while(enum_Obj.hasMoreElements()){
                enum_Obj.nextElement();// Remove Edge
                vertex = (VertexI)enum_Obj.nextElement();
                pathStr += " - " + vertex.getKey();
            }
            
            pathLabel.setText(pathStr);
        }
        catch(VertexNotFoundException e){}
        catch(InvalidPropertyException e){}
    }

    public void init() {
        super.init();

        destKey = "A";
        sourceKey = "A";
        SparseGraph graph = new SparseGraph();

        // Add the vertices
        try{
            graph.addVertex("A");
            graph.addVertex("B");
            graph.addVertex("C");
            graph.addVertex("D");
            graph.addVertex("E");
            graph.addVertex("F");
            graph.addVertex("G");
            graph.addVertex("H");
        }
        catch(DuplicateVertexException e){}

        // Add the edges
        try{
            graph.addEdge("A","B", new Double(2));
            graph.addEdge("A","C", new Double(2));
            graph.addEdge("D","B", new Double(3));
            graph.addEdge("D","C", new Double(2));
            graph.addEdge("D","E", new Double(2));
            graph.addEdge("D","F", new Double(3));
            graph.addEdge("G","E", new Double(2));
            graph.addEdge("G","F", new Double(2));
            graph.addEdge("E","B", new Double(1), true); // directed
            graph.addEdge("C","F", new Double(1), true); // directed
        }
        
        catch(DuplicateEdgeException e){}
        catch(VertexNotFoundException e){}

        algorithm = new Dijkstra(graph);

		//{{INIT_CONTROLS
		setLayout(null);
		setSize(430,270);
		sourceChoice.addItem("A");
		sourceChoice.addItem("B");
		sourceChoice.addItem("C");
		sourceChoice.addItem("D");
		sourceChoice.addItem("E");
		sourceChoice.addItem("F");
		sourceChoice.addItem("G");
		
		try {
			sourceChoice.select(0);
		}
		
		catch (IllegalArgumentException e) { }
			add(sourceChoice);
			sourceChoice.setBounds(60,12,36,21);
			label1.setText("Source");
			label1.setAlignment(java.awt.Label.RIGHT);
			add(label1);
			label1.setBounds(0,12,48,20);
			label2.setText("Dest");
			label2.setAlignment(java.awt.Label.RIGHT);
			add(label2);
			label2.setBounds(108,12,40,20);
			label3.setText("Path:");
			add(label3);
			label3.setBounds(12,36,40,24);
			label4.setText("Cost:");
			add(label4);
			label4.setBounds(12,60,40,24);
			add(pathLabel);
			pathLabel.setBounds(60,36,144,24);
			add(costLabel);
			costLabel.setBounds(60,60,144,24);
			destChoice.addItem("A");
			destChoice.addItem("B");
			destChoice.addItem("C");
			destChoice.addItem("D");
			destChoice.addItem("E");
			destChoice.addItem("F");
			destChoice.addItem("G");
		
		try {
			destChoice.select(0);
		}
		catch (IllegalArgumentException e) { }
			add(destChoice);
			destChoice.setBounds(156,12,36,21);
		//}}
	}
	public boolean handleEvent(Event event) {
		if (event.target == sourceChoice && event.id == Event.ACTION_EVENT) {
			sourceChoice_Action(event);
			return true;
		}
		if (event.target == destChoice && event.id == Event.ACTION_EVENT) {
			destChoice_Action(event);
			return true;
		}
		return super.handleEvent(event);
	}
	//{{DECLARE_CONTROLS
	java.awt.Choice sourceChoice = new java.awt.Choice();
	java.awt.Label label1 = new java.awt.Label();
	java.awt.Label label2 = new java.awt.Label();
	java.awt.Label label3 = new java.awt.Label();
	java.awt.Label label4 = new java.awt.Label();
	java.awt.Label pathLabel = new java.awt.Label();
	java.awt.Label costLabel = new java.awt.Label();
	java.awt.Choice destChoice = new java.awt.Choice();
	//}}
	
    public void selectedDestChoice() {
        destKey = destChoice.getSelectedItem();
        displayPath(sourceKey, destKey);
    }

    public void selectedSourceChoice() {
        sourceKey = sourceChoice.getSelectedItem();
        displayPath(sourceKey, destKey);
    }	
}
