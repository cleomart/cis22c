import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/*
 * Class Maze creates the maze either by generating the maze randomly
 * or by reading it from a text file
 * 
 */

public class Maze {
	private int mazeSize; //size of each row and each column
	private int roomCount; // size of the whole maze 
	DisjointSets disjointSet; //keeps the track if there is path from start to finish
	private int[][] mazeArray; //array that holds the room number and walls
	private Stack<Integer> pathStack = new Stack<Integer>();
    private LinkedList<Integer> Q = new LinkedList<Integer>();
	private GraphInt mazeGraph;
	private boolean visited[];
    private boolean visited1[];
	private final int CLOSE = 1;
	private final int OPEN = 0;
	private final int NORTH = 0;
	private final int SOUTH = 1;
	private final int EAST = 2;
	private final int WEST = 3;

	/*
	 * Constructor
	 * 
	 */
	public Maze(int i){
		mazeSize = i;
		roomCount = mazeSize*mazeSize; 
		disjointSet = new DisjointSets(roomCount);
		mazeArray = new int[roomCount][4];
		mazeGraph = new GraphInt(roomCount);
		visited = new boolean[roomCount];
		visited1 = new boolean[roomCount];

		
		for(int m = 0; m < roomCount; m++)
			visited[m] = false;

		/*
		 * initialize the array to 1, which represents that the door is closed
		 * if 0, the door is open
		 */
		for(int k = 0; k < roomCount; k++)
			for(int j =0; j < 4; j++)
				mazeArray[k][j] = CLOSE;
	}

	/*
	 * read in a file that will generate the maze
	 */
	public int[][] mazeRead(String fileName) {
		try{
			Scanner scan = new Scanner(new File(fileName));
			scan.useDelimiter("\n");
			if (scan.hasNext()) {
				int mazeSize = Integer.parseInt(scan.next()); //first argument on the first line
				

				for(int i = 0; i < roomCount; i++) {
					String line = scan.next();
					String[] doors = line.split(" ");
					for(int j = 0 ; j <4; j++){
						mazeArray[i][j] = Integer.parseInt(doors[j]);
				  
					}
				}

			}

			scan.close();
			
			for(int i = 0; i < mazeSize; i++)
			    for( int j = 0; j < 4; j++){ 
					if(mazeArray[i][j] == OPEN){
						disjointSet.union(i, j);
					}
				}


		}
		catch(FileNotFoundException e){
			System.out.println("File not found.");
			System.exit(0);
		}
		
		System.out.println("THE MAZE HAS BEEN READ THROUGH A FILE!");
		return mazeArray;
	}

	/*
	 * This function generates a maze randomly
	 */
	public int [][] generateRandomMaze(){


		int roomNum; // a room #
		int caseNum;  // cases of which walls to open
		//use while loop until there is a path

		while(disjointSet.find(0) != disjointSet.find(roomCount-1)){
			roomNum = (int) (Math.random() * roomCount);
			if(roomNum == 0){
				caseNum = (int) (Math.random() * 2);
				switch(caseNum){
				case 0:
					if( mazeArray[roomNum][EAST] == CLOSE){
						mazeArray[roomNum][EAST] = OPEN;
						mazeArray[roomNum+1][WEST] = OPEN;
						disjointSet.union(roomNum, roomNum+1);
					}
					break;
				case 1:
					if(mazeArray[roomNum][SOUTH] == CLOSE){
						mazeArray[roomNum][SOUTH] = OPEN;
						mazeArray[roomNum+mazeSize][NORTH] = OPEN;
						disjointSet.union(roomNum, roomNum+mazeSize);
					}
					break;
				}
			}
			else if(roomNum == roomCount-1){
				caseNum = (int) (Math.random() * 2);
				switch(caseNum){
				case 0:
					if(mazeArray[roomNum][NORTH] == CLOSE){
						mazeArray[roomNum][NORTH] = OPEN;
						mazeArray[roomNum-mazeSize][SOUTH] = OPEN;
						disjointSet.union(roomNum, roomNum-mazeSize);
					}
					break;
				case 1:
					if(mazeArray[roomNum][WEST] == CLOSE){
						mazeArray[roomNum][WEST] = OPEN;
						mazeArray[roomNum-1][EAST] = OPEN;
						disjointSet.union(roomNum, roomNum-1);
					}
					break;
				}
			}
			else if(roomNum == mazeSize-1){
				caseNum = (int) (Math.random() * 2);
				switch(caseNum){
				case 0:
					if(mazeArray[roomNum][SOUTH] == CLOSE){
						mazeArray[roomNum][SOUTH] = OPEN;
						mazeArray[roomNum+mazeSize][NORTH] = OPEN;
						disjointSet.union(roomNum, roomNum+mazeSize);
					}
					break;
				case 1:
					if (mazeArray[roomNum][WEST] == CLOSE){
						mazeArray[roomNum][WEST] = OPEN;
						mazeArray[roomNum-1][EAST] = OPEN;
						disjointSet.union(roomNum, roomNum-1);
					}
					break;
				}
			}
			else if(roomNum == roomCount-mazeSize){
				caseNum = (int) (Math.random() * 2);
				switch(caseNum){
				case 0:
					if(mazeArray[roomNum][NORTH] == CLOSE){
						mazeArray[roomNum][NORTH] = OPEN;
						mazeArray[roomNum-mazeSize][SOUTH] = OPEN;
						disjointSet.union(roomNum, roomNum-mazeSize);
					}
					break;
				case 1:
					if(mazeArray[roomNum][EAST] == CLOSE){
						mazeArray[roomNum][EAST] = OPEN;
						mazeArray[roomNum+1][WEST] = OPEN;
						disjointSet.union(roomNum, roomNum+1);
					}
					break;
				}
			}
			else if(roomNum > 0 && roomNum < (mazeSize-1)){
				caseNum = (int) (Math.random() * 3);
				switch(caseNum){
				case 0:
					if(mazeArray[roomNum][EAST] == CLOSE){
						mazeArray[roomNum][EAST] = OPEN;
						mazeArray[roomNum+1][WEST] = OPEN;
						disjointSet.union(roomNum, roomNum+1);
					}
					break;
				case 1:
					if(mazeArray[roomNum][WEST] == CLOSE){
						mazeArray[roomNum][WEST] = OPEN;
						mazeArray[roomNum-1][EAST] = OPEN;
						disjointSet.union(roomNum, roomNum-1);
					}
					break;
				case 2:
					if(mazeArray[roomNum][SOUTH] == CLOSE){
						mazeArray[roomNum][SOUTH] = OPEN;
						mazeArray[roomNum+mazeSize][NORTH] = OPEN;
						disjointSet.union(roomNum, roomNum+mazeSize);
					}
					break;
				}
			}
			else if(roomNum % mazeSize == 0){
				caseNum = (int) (Math.random() * 3);
				switch(caseNum){
				case 0:
					if(mazeArray[roomNum][EAST] == CLOSE){
						mazeArray[roomNum][EAST] = OPEN;
						mazeArray[roomNum+1][WEST] = OPEN;
						disjointSet.union(roomNum, roomNum+1);
					}
					break;
				case 1:
					if(mazeArray[roomNum][NORTH] == CLOSE){
						mazeArray[roomNum][NORTH] = OPEN;
						mazeArray[roomNum-mazeSize][SOUTH] = OPEN;
						disjointSet.union(roomNum, roomNum-mazeSize);
					}
					break;
				case 2:
					if(mazeArray[roomNum][SOUTH] == CLOSE){
						mazeArray[roomNum][SOUTH] = OPEN;
						mazeArray[roomNum+mazeSize][NORTH] = OPEN;
						disjointSet.union(roomNum, roomNum+mazeSize);
					}
					break;
				}
			}
			else if( (roomNum+1) % mazeSize == 0){
				caseNum = (int) (Math.random() * 3);
				switch(caseNum){
				case 0:
					if(mazeArray[roomNum][NORTH] == CLOSE ){
						mazeArray[roomNum][NORTH] = OPEN;
						mazeArray[roomNum-mazeSize][SOUTH] = OPEN;
						disjointSet.union(roomNum, roomNum-mazeSize);
					}
					break;
				case 1:
					if(mazeArray[roomNum][WEST] == CLOSE){
						mazeArray[roomNum][WEST] = OPEN;
						mazeArray[roomNum-1][EAST] = OPEN;
						disjointSet.union(roomNum, roomNum-1);
					}
					break;
				case 2:
					if(mazeArray[roomNum][SOUTH] == CLOSE){
						mazeArray[roomNum][SOUTH] = OPEN;
						mazeArray[roomNum+mazeSize][NORTH] = OPEN;
						disjointSet.union(roomNum, roomNum+mazeSize);
					}
					break;
				}
			}
			else if( roomNum > (roomCount - mazeSize) && roomNum < (roomCount - 1)){
				caseNum = (int) (Math.random() * 3);
				switch(caseNum){
				case 0:
					if(mazeArray[roomNum][NORTH] == CLOSE){
						mazeArray[roomNum][NORTH] = OPEN;
						mazeArray[roomNum-mazeSize][SOUTH] = OPEN;
						disjointSet.union(roomNum, roomNum-mazeSize);
					}
					break;
				case 1:
					if(mazeArray[roomNum][WEST] == CLOSE){
						mazeArray[roomNum][WEST] = OPEN;
						mazeArray[roomNum-1][EAST] = OPEN;
						disjointSet.union(roomNum, roomNum-1);
					}
					break;
				case 2:
					if(mazeArray[roomNum][EAST] == CLOSE){
						mazeArray[roomNum][EAST] = OPEN;
						mazeArray[roomNum+1][WEST] = OPEN;
						disjointSet.union(roomNum, roomNum+1);
					}
					break;
				}
			}
			else{
				caseNum = (int) (Math.random() * 4);
				switch(caseNum){
				case 0:
					if(mazeArray[roomNum][EAST] == CLOSE){
						mazeArray[roomNum][EAST] = OPEN;
						mazeArray[roomNum+1][WEST] = OPEN;
						disjointSet.union(roomNum, roomNum+1);
					}
					break;
				case 1:
					if(mazeArray[roomNum][WEST] == CLOSE){
						mazeArray[roomNum][WEST] = OPEN;
						mazeArray[roomNum-1][EAST] = OPEN;
						disjointSet.union(roomNum, roomNum-1);
					}
					break;
				case 2:
					if(mazeArray[roomNum][SOUTH] == CLOSE){
						mazeArray[roomNum][SOUTH] = OPEN;
						mazeArray[roomNum+mazeSize][NORTH] = OPEN;
						disjointSet.union(roomNum, roomNum+mazeSize);
					}
					break;
				case 3:
					if(mazeArray[roomNum][NORTH] == CLOSE){
						mazeArray[roomNum][NORTH] = OPEN;
						mazeArray[roomNum-mazeSize][SOUTH] = OPEN;
						disjointSet.union(roomNum, roomNum - mazeSize);
					}
					break;
				}
			}

		}

		mazeArray[0][NORTH] = OPEN; //open the door at the start
		mazeArray[roomCount-1][SOUTH] = OPEN; // open the door at the finish

		System.out.println("THE MAZE HAS BEEN RANDOMLY GENERATED");
	
		return mazeArray;
	}
	
	
	/*
	 * This function maps the maze into a Graph
	 * 
	 */

	public void mazeToGraph(){
		for (int i = 0; i < roomCount; i++){
			if (mazeArray[i][NORTH]==OPEN){
				if( (i-mazeSize) >= 0){
					mazeGraph.insertEdge(i, i-mazeSize);
				}
			}
			if (mazeArray[i][SOUTH]==OPEN){
				if( (i + mazeSize) < roomCount){
					mazeGraph.insertEdge(i,i+mazeSize);
				}
			}
			 if (mazeArray[i][EAST] == OPEN){
			     if ((i+1) < roomCount)
				 {
				     mazeGraph.insertEdge(i, i+1);
				     
				 }
			}
			if (mazeArray[i][WEST] == OPEN){
				mazeGraph.insertEdge(i, i-1);
			}
		}

	       
	}

	/*
	 * BREATHD FIRST SEARCH
	 */
	public void bfs(int v){
	    Q.add(v);
	    visited1[v] = true;
	    System.out.println ("Room visited by BFS: ");
	    while (!Q.isEmpty())
		{
		    int i = Q.remove();
		    System.out.print (i + " ");
		    if (i == roomCount-1)
			{
			    System.out.println ("Path found!");
			    break;
			}
		    else
			{
			    for (int j  = 0; j< roomCount; j++)
				{
				    if (mazeGraph.areAdjacent(i,j) && !visited1[j])
					{
					    Q.add(j);
					    visited1[j] = true;
					}
				}
			}
		}
	    
	}



	public void dfs(int v){
		pathStack.push(v);
		visited[v] = true;
		System.out.println("Room visited by DFS: ");
		while(!pathStack.isEmpty()){
			int i = pathStack.pop();
			System.out.print(i + " ");
			if( i == (roomCount-1)){
				System.out.println("Path found!");
				break;
			}
			else{
				for(int j = 0; j < roomCount; j++){
					

					if(mazeGraph.areAdjacent(i,j) && !visited[j]){
						pathStack.push(j);
						visited[j] = true;
					
					}
				}
			}
		}
	}

}


