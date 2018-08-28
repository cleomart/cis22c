/*
 * Class DisjointSets creates sets that are disjoint.
 * This class is used to know if there is a path in the maze
 * and every time we open adjacent doors of rooms i,j
 * we combine i, j into the same set
 * 
 */

public class DisjointSets {
	private int arraySet[];

/*
 * constructor
 */
	public DisjointSets(int N){
		arraySet = new int[N];
		for(int i = 0; i < N; i++)
			arraySet[i] = -1;
	}

	/*
	 * unionize the two sets that are disjoint
	 */
	public void union(int index1, int index2){
		int root1 = find(index1);
		int root2 = find(index2);
		
		if(root1 != root2){
			if(arraySet[root2] < arraySet[root1]){
				arraySet[root2] += arraySet[root1];
				arraySet[root1] = root2;
			}
			else{
				arraySet[root1] += arraySet[root2];
				arraySet[root2] = root1;
			}
		}
	}

	/*
	 * finds the root of the set where element x belongs to
	 */
	public int find(int x){
		if (arraySet[x] < 0)
			return x;
		else{
			arraySet[x] =  find(arraySet[x]);
			return arraySet[x];
		}

	}
}
