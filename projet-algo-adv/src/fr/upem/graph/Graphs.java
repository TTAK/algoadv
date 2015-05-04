package fr.upem.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


/**
 *
 * @author Besnard Arthur
 */
public class Graphs {

    public static Graph getMatrixGraph(int verticesNumber) {

        return new Graph() {
            private final int[][] matrix = new int[verticesNumber][verticesNumber];
            private int edgeNumber = 0;

            @Override
            public int numberVertices() {
                return matrix[0].length;
            }

            @Override
            public int numberEdges() {
                return edgeNumber;
            }

            @Override
            public boolean isEdge(Edge a) {
                if (isAValidEdge(a)) {
                    throw new IllegalArgumentException(a + " is not a valid Edge for the graph");
                }
                return matrix[a.start][a.end] > 0;
            }

            @Override
            public void addEdge(Edge a) {
                if (isAValidEdge(a)) {
                    throw new IllegalArgumentException(a + " is not a valid Edge for the graph");
                }
                if (matrix[a.start][a.end] == 0) {
                    ++edgeNumber;
                }
                matrix[a.start][a.end] = a.weight;
            }

            @Override
            public void deleteEdge(Edge a) {
                if (matrix[a.start][a.end] == 0 || !isAValidEdge(a)) {
                    throw new IllegalArgumentException(a + " is not a valid Edge of the graph");
                }
                matrix[a.start][a.end] = a.weight;
                --edgeNumber;
            }

            @Override
            public Iterator<Integer> neighbors(int n) {
                return new Iterator<Integer>() {
                    private int i = nextValue(0);

                    @Override
                    public boolean hasNext() {
                        return i < verticesNumber;
                    }

                    @Override
                    public Integer next() {
                        int neighbor = i;
                        i = nextValue(i);
                        return neighbor;
                    }

                    public int nextValue(int i) {
                        for (; i < verticesNumber && matrix[n][i] == 0; ++i);
                        return i;
                    }
                };
            }

            private boolean isAValidEdge(Edge a) {
                return a.start > 0 && a.end < verticesNumber;
            }
        };
    }
    
    private static class weightedInteger{
        private final int value;
        private final int weight;

        public weightedInteger(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 71 * hash + this.value;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final weightedInteger other = (weightedInteger) obj;
            return this.value != other.value;
        }
    }

    public static Graph getListAdjGraph() {
        return new Graph() {
            private final ArrayList<LinkedList<weightedInteger>> adjLists = new ArrayList<>();

            @Override
            public int numberVertices() {
                return adjLists.size();
            }

            @Override
            public int numberEdges() {
                return adjLists.stream().mapToInt(list->list.size()).sum();
            }

            @Override
            public boolean isEdge(Edge a) {
                return adjLists.get(a.start).contains(new weightedInteger(a.end, a.weight));
            }

            @Override
            public void addEdge(Edge a) {
                LinkedList<weightedInteger> list = adjLists.get(a.start);
                if(list == null){
                    adjLists.add(a.start, new LinkedList<>());
                    adjLists.get(a.start).add(new weightedInteger(a.end, a.weight));
                }
            }

            @Override
            public void deleteEdge(Edge a) {
                LinkedList<weightedInteger> list = adjLists.get(a.start);
                if(list != null){
                    list.remove(new weightedInteger(a.end, 0));
                }
            }

            @Override
            public Iterator<Integer> neighbors(int n) {
                Iterator<weightedInteger> it = this.adjLists.get(n).iterator();
                return new Iterator<Integer>() {

                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public Integer next() {
                        return it.next().value;
                    }
                };
            }
        };
    }
	public static void PP(Graph g){
		ArrayList<Integer> ppList = new ArrayList<Integer>();
		int nbVertices = g.numberVertices();
		
		for(int i=0;i<nbVertices;i++){
			if(!ppList.contains(i)){
				Iterator<Integer> iterator = g.neighbors(i);
				int node = -1;
				if(iterator.hasNext()){
					while(iterator.hasNext()){
						node = iterator.next();
						if(ppList.contains(node)){
							break;
						}
						ppList.add(node);
						System.out.print(node+" ");
					}
					node = iterator.next();
					if(ppList.contains(node)){
						break;
					}
					ppList.add(node);
					System.out.print(node+" ");
				}
			}
		}
	}
}
