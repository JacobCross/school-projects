
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

    public static void updateCells(int data[]){

        int n = 1;
        int b = 1;

        //below, this is the array that I am assigning the new, edited values to.
        //I must use this because I cannot tamper with the main data cells array while I am trying to check it for
        //matches in the rules I have specified.
        int[] newData = new int[data.length];

        //first rule: if there is a one on each side of any cell, flip the value of that middle cell.

        //second rule: if there are two cells of value '0' to the left of a cell with the value '1', make the cell directly
        //to the left of the '1' cell, '1'.

        //checking to see if rules are matched, and if they are, assigning the proper values to the new array.

        // NEED TO IMPLEMENT 2-VAIABLE CYCLING TO MOVE THROUGH 2-D ARRAY, CHECKING FOR RULES ON THE
        //INSIDE-MOST LOOP
        //THESE RULES ARE TAKEN FROM THE CONWAY GAME OF LIFE FOUND ONLINE

        //make sure that the cell being checked is not 9, if it is, break

        while(n<9){
            while(b<9){
              //change these rules to match the Conway rules.
              if(data[n-1]==1 && data[n+1]==1){ //first rule
                  if(data[n] == 1){
                      newData[n] = 0;
                  }
                  else{
                      newData[n] = 1;
                  }
              }
              else if(n>=2){
                  if(!(data[n-2]==0  && data[n-1]==0 && data[n]==1)){ //second rule
                      newData[n] = data[n];
                  }
                  else{
                      newData[n-1] = 1;
                      newData[n] = data[n]; //assign the newArray the same value
                  }
              }
              else if (n<2){
                  newData[n] = data[n]; //assign the newArray the same value
              }
              n++;
        }

        //copying the array I edited, to the one in use by displayCells.
        n = 0;
        while(n<data.length){
            data[n] = newData[n];
            n++;
        }

    }

  //initialize two dimensional array for grid
  //accept user input for coordinates, and for time steps
  //modify updateCells from last program to work on 2D array instead of 1D array
  //modify displayCells to work with 2D array


  //line break after user input
  //System.out.println();

  }
}
