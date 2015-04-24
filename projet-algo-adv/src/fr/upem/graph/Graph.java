package fr.upem.graph;
import java.util.Iterator;

public interface Graph {
	public static final int ORIENTED = 0x01; 
	public static final int WEIGHTED = 0x10;

	public int numberVertices(); 
	public int numberEdges(); 
	public boolean isEdge(Edge a); 
	public void addEdge(Edge a); 
	public void deleteEdge(Edge a); 
	public Iterator<Integer> neighbors(int n);
	
}
