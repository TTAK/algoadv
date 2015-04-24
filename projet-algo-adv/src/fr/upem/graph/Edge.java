
package fr.upem.graph;

/**
 *
 * @author Besnard Arthur
 */
public class Edge {
    public final int start;
    public final int end;
    public final int weight;

    public Edge(int start, int end, int weight) {
        if(weight == 0){
            throw new IllegalArgumentException("weight must be != 0");
        }
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
