
import java.util.Scanner;

public class Life {
  public static void main(String[] args){
    int[][] grid = new int[10][10]; //this is the grid that is filled with the actual 1's and 0's which represent occupied/non-occupied cells.
    int[] coords = new int[200];
    int[] finalCoords;
    int x,n,b,user,counter,current;
    Scanner input = new Scanner(System.in);

    n=0;
    b=0;
    //initilizing all values of the grid to zero
    while(n<10){
      while(b<10){
        grid[n][b] = 0;
        b++;
      }
      n++;
      b=0;
    }

    //CODE FROM CA PROGRAM

    //assigns all values of the 'occupied' array to 99 (which is a value the user will not enter), so that
    //insted of the default values being 0, they are 99, so that when we need to shorten and condense/clean up this
    //array later, it is possible to work with.
    n=0;
    while(n<coords.length){
        coords[n] = 99;
        n++;
    }
    System.out.print("Please enter list of (i,j) pairs for populated cells (negative i or j to quit): ");
    //reads in all values from user and stores them in each cell of array
    n = 0;
    current = 1;
    while(n<coords.length && current>=0){
        current = input.nextInt();
        coords[n] = current;
        n++;
    }

    //see more explanation below
    n = 0;
    counter = 0;
    while(n<coords.length){
        x = coords[n];
        if(x>=0 && x!=99){ //if the value is valid, that is: if it's not 99, and it is greater than or equal to 0.
            counter++;
        }
        n++;
    }

    //create new array of length(counter) - this is the cleaned up version of the 'occupied' array,
    //the reason for the counter above that we use to create the array is so that only the meaningful
    //values that we need from the original messy array will be passed on the the finalOccupied array.
    finalCoords = new int[counter];
    n = 0;
    while(n<finalCoords.length){
      finalCoords[n] = coords[n];
      n++;
    }

    n = 0;
    while(n<finalCoords.length){
      System.out.println(finalCoords[n]);
      n++;
    }

  //initialize two dimensional array for grid
  //accept user input for coordinates, and for time steps
  //modify updateCells from last program to work on 2D array instead of 1D array
  //modify displayCells to work with 2D array


  //line break after user input
  //System.out.println();

  }
}
