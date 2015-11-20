/*************************************************
File: Life.java
By: Jacob Cross
Date: 11/20/15
Compile: javac Life.java
Usage: java Life
Description: User is prompted to enter the coordinates of the occupied cells that they would like, as well as the number of time steps they would like.
The game of Life is a form of a two dimensional cellular automation.
The cellular automation rules are that of 'Conways game of life'.

The program has primary methods that are used to comply with DRY principles, these methods are:
displayGrid(int grid[][]) - takes in the 2D array that is filled with 1's and 0's, and prints the grid using # and spaces respectively, so the user can see the change occuring.
updateGrid(int grid[][]) - takes in the 2D array that is filled with 1's and 0's, and 'updates' it according to the rules of Conway's game of Life
countNeighbors(int grid[][],int n, int b) - this is a method that I made up and decided to implent, it takes in the 2D array that is filled with 1's and zeros, and it takes in
the coordinates of the current position that is being examined by updateGrid, and counts the number of neighbors that it has, and returns this value as an integer.
*************************************************/
import java.util.Scanner;

public class Life {
  public static void main(String[] args){
    int[][] grid = new int[10][10]; //this is the grid that is filled with the actual 1's and 0's which represent occupied/non-occupied cells.
    int[] coords = new int[200]; //this is the array that I use to take the initial user input about which coordinates they want to be occupied
    int[] finalCoords; //this is the 'clean cut' array that I get when I clean up the raw-input 'coords' array above.
    int x,y,n,b,p,user,counter,current,steps;
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

    //assigns all values of the 'coords'' array to 99 (which is a value the user will not enter), so that
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
    System.out.print("Enter number of time steps: ");
    steps = input.nextInt();

    //see more explanation below
    n = 0;
    counter = 0;
    while(n<coords.length){
        p = coords[n];
        if(p>=0 && p!=99){ //if the value is valid, that is: if it's not 99, and it is greater than or equal to 0.
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

    //initial population of grid using finalCoords array
    n=0;
    while(n<finalCoords.length){
      x = finalCoords[n];
      y = finalCoords[n+1];
      grid[x][y] = 1;
      n+=2;
    }

    //main loop for displaying and updating cells, along with an integrated Heading printing based on the step counter
    n=0;
    while(n<=steps){
        if(n==0){
          System.out.println("Initial Grid:");
        }
        else if(n>0){
          System.out.println("Time step "+n);
        }
        displayGrid(grid);
        updateGrid(grid);
        n++;
    }

  }

  //descrption in heading
  public static void displayGrid(int grid[][]){
    System.out.println("0123456789");
    int n = 0;
    int b = 0;
    int current;
    while(n<10){
      while(b<10){
          current=grid[n][b];
          if(current==0){
            System.out.print(" ");
          }
          else if(current==1){
            System.out.print("#");
          }
        b++;
      }
      System.out.print(" "+n);
      System.out.println();
      n++;
      b=0;
    }
  }

  //descrption in heading
  public static int countNeighbors(int grid[][],int n, int b){
    int count = 0;
    int c = 0;
    //checking for all specific neighbor cases - no real good way to loop this smoothly in my opinion
    if(grid[n-1][b-1]==1){
      count+=1;
    }
    if(grid[n-1][b]==1){
      count+=1;
    }
    if(grid[n-1][b+1]==1){
      count+=1;
    }
    if(grid[n][b-1]==1){
      count+=1;
    }
    if(grid[n][b+1]==1){
      count+=1;
    }
    if(grid[n+1][b-1]==1){
      count+=1;
    }
    if(grid[n+1][b]==1){
      count+=1;
    }
    if(grid[n+1][b+1]==1){
      count+=1;
    }
    return count;
  }

  //descrption in heading
  public static void updateGrid(int grid[][]){

        int n = 1;
        int b = 1;
        //below, this is the array that I am assigning the new, edited values to.
        //I must use this because I cannot tamper with the main data cells array while I am trying to check it for
        //matches in the rules I have specified.
        int[][] newGrid = new int[10][10];

        while(n<9){
            while(b<9){
              //These are the Conway rules:
              if(grid[n][b]==1){
                if(countNeighbors(grid,n,b)==1 || countNeighbors(grid,n,b)==0 || countNeighbors(grid,n,b)>=4){
                  newGrid[n][b] = 0;
                }
                else if(countNeighbors(grid,n,b)==3||countNeighbors(grid,n,b)==2){
                  newGrid[n][b] = 1;
                }
              }
              else if(grid[n][b]==0 && countNeighbors(grid,n,b)==3){
                newGrid[n][b]=1;
              }
              b++;
          }
          b=1;
          n++;
        }

        //copying the array I edited, to the one in use by displayGrid.
        n = 0;
        b = 0;
        while(n<10){
          while(b<10){
            grid[n][b] = newGrid[n][b];
            b++;
          }
          n++;
          b=0;
        }
    }
}
