import java.util.LinkedList;

/*
 * GraphInt creates the equivalent graph of the maze.
 * This will be used to insert the path between rooms i,j
 * and check also if rooms i,j are adjacent
 */
public class GraphInt {

	private int vertices; // number of nodes
	private int edges; //number of paths
	private LinkedList<Integer> [] adjacencyList;  //int array of linkedlists


	public GraphInt(int N){
		vertices = N;
		edges = 0;
		adjacencyList = new LinkedList[N];

		for (int i = 0; i<N; i++)
			adjacencyList[i] = new LinkedList<Integer>();
	}
	
	/*
	 * insert an edge between vertices i and j
	 */

	public void insertEdge(int i, int j){
		if(!areAdjacent(i,j)){
		adjacencyList[i].addLast(j);
		adjacencyList[j].addLast(i);
		edges++;
		}
	}

	/*
	 * remove edge between vertices v and w
	 */
	public void removeEdge(int v, int w){
		
			adjacencyList[v].remove(w);
			adjacencyList[w].remove(v);
			edges--;
	}
	
	/*
	 * checks if vertices v and w are adjacent
	 */

	public boolean areAdjacent(int v, int w){

		return (adjacencyList[v].contains(w) && adjacencyList[w].contains(v));
	}

	/*
	 * return the number of edges
	 */
	public int numEdges(){
		return edges;
	}

}
