import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.soap.Node;

/*
 * Class Driver is where we implement the creation of the maze
 * Either by generating the maze randomly by size N specified by the user
 * or by creating the maze from reading a text file.
 */


public class Driver {
	
	public static void main(String[] args){
		int direction = 1;
		int realsize;
		int roomArray[][] ;
		Maze mazeCraze;
		
		if (isStringInt(args[0]))
		{
			direction = 1;
		}
		else
		{
			direction = 3;
		}
		if(direction == 1){
			int size = Integer.parseInt(args[0]);

			mazeCraze = new Maze(size);
			roomArray = mazeCraze.generateRandomMaze();
			realsize = size;
		}
		else {
			int size = 0;
			try{
				Scanner scan = new Scanner(new File(args[0]));
				if (scan.hasNext()) {
					size = Integer.parseInt(scan.next()); //only get the first argument on the first line for the size of the maze
				}
				scan.close();
			}
			catch(FileNotFoundException e){
				System.out.println("File not found.");
				System.exit(0);
			}

			mazeCraze = new Maze(size);
			roomArray = mazeCraze.mazeRead(args[0]);
			realsize = size;
		}

		/*
		 * Prints out the maze
		 */

		for ( int i = 0 ; i < realsize; i++){
			if (i == 0 )   
				System.out.print("  ");
			else 
				System.out.print("_");
			System.out.print(" ");
		}
		System.out.println();
		for (int i = 0; i < realsize*realsize; i++){
			if(roomArray[i][3] == 1 )
				System.out.print("|");
			if(roomArray[i][3] == 0)
				System.out.print(" ");
			if(roomArray[i][1] == 1)
				System.out.print("_");
			if(roomArray[i][1] == 0)
				System.out.print(" ");
			if((i+1) % realsize == 0){
				System.out.print("|");
				System.out.println();
			}
		}

		mazeCraze.mazeToGraph(); //creates the equivalent graph of the maze
		mazeCraze.dfs(0); // depth first search
		mazeCraze.bfs(0); // breadth first search
	}

	public static boolean isStringInt(String s)
	{
		try
		{
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex)

		{
			return false;
		}
	}

}


