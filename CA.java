/*************************************************
File: CA.java
By: Jacob Cross
Date: 11/9/15
Compile: javac CA.java
Usage: java CA
Description: User is prompted to enter several details about a cellular automation that will occur, those details being: number of cells, number of time steps, and initial
* occupied cells. After the user enters this information, the program runs and generates a visual cellular automation output according to rules that are defined in the code, 
* which were deduced and taken from the examples given in the assignment document.
* 
* The program uses two primary methods to operate, those being displayCells(), and updateCells().
* displayCells() interprets the information in the 'cells' array and prints it to the screen as a visual representation of the changes that are occuring
* updateCells() is the heart of this code. It takes in the last 'cells' array, looks at the value of each cell, and it's neighbors, and makes changes to the next 'cells' array
* according to the rules that are defined.
*************************************************/
import java.util.Scanner;

public class CA {
    public static void main(String[] args){
        
        //declaring variables and Scanner to be used in the main method.
        int numCells,steps,n,occ,x,counter,t,b;
        int[] occupied,cells,finalOccupied;
        Scanner input = new Scanner(System.in);
        
        //Print statements for communication with the user, as well as gathering information
        System.out.println("Welcome to the cellular automaton simulation!");
        
        
        System.out.print("Enter number of cells (<= 80): ");
        numCells = input.nextInt(); //variable that holds the number of cells the user wants the array to have in the automation
        occupied = new int[numCells]; //array that stores the cells that user wants to fill
        cells = new int[numCells]; //array to be used for printing and editing
        //System.out.println();
        
        System.out.print("Enter number of time steps: ");       
        steps = input.nextInt(); //variable that holds the number of time steps the user wants to have in the automation
        //System.out.println();
        
        System.out.print("Enter the index of occupied cells (negative index to end): ");
        n = 0;
        occ = 1;
        
        
        //assigns all values of the 'occupied' array to 99 (which is a value the user will not enter), so that
        //insted of the default values being 0, they are 99, so that when we need to shorten and condense/clean up this 
        //array later, it is possible to work with.
        while(n<occupied.length){
            occupied[n] = 99;
            n++;
        }
        
        //reads in all values from user and stores them in each cell of array       
        n = 0;
        while(n<numCells && occ>=0){ 
            occ = input.nextInt();
            occupied[n] = occ;
            n++;            
        }
        
        //see more explanation below
        n = 0;
        counter = 0;
        while(n<occupied.length){
            x = occupied[n];
            if(x>=0 && x!=99){ //if the value is valid, that is: if it's not 99, and it is greater than or equal to 0.
                counter++;
            }
            n++;
        }
        
        //create new array of length(counter) - this is the cleaned up version of the 'occupied' array,
        //the reason for the counter above that we use to create the array is so that only the meaningful
        //values that we need from the original messy array will be passed on the the finalOccupied array.
        finalOccupied = new int[counter];
        
        //assigning the meaningful values from the messy array to the new, clean one.
        n = 0;
        while(n<finalOccupied.length){
            finalOccupied[n] = occupied[n];
            n++;
        }
        
        //sets all values in cells to zero
        n = 0;
        while(n<cells.length){
            cells[n] = 0;
            n++;
        }
   
        //fill cells array with 1s that need it
        n = 0;
        while(n<finalOccupied.length){
            t = finalOccupied[n];
            cells[t] = 1;
            n++; 
        }
        
        System.out.println();
        //printing the heading labels '0123456789' which repeats as needed depending on the number of cells.
        n = 0;
        b = 0;
        while(n<cells.length){
            if(b<=9){
                System.out.print(b);
                b++;
                n++;
            }
            else if(b>9){
                b = 0;
            }   
        }
        System.out.println();
        
        //other functions
        //while loop - while there are still steps left to do, run the functions over and over.
        n = 0;
        while(n<=steps){
            displayCells(cells);
            updateCells(cells);
            n++;
        }
    }
    
    public static void displayCells(int data[]){
        //interprets each cell from the array it's passed.
        //print rules follow:
        // if cell = 0, print " " 
        // if cell = 1, print "#"
        int n = 0;
        while(n<data.length){
            if(data[n]==0){
                System.out.print(" ");
            }
            else{
                System.out.print("#");
            }
            n++;
        }
        System.out.println(); //formatting
    }
    
    public static void updateCells(int data[]){
        
        int n = 1;
        
        //below, this is the array that I am assigning the new, edited values to.
        //I must use this because I cannot tamper with the main data cells array while I am trying to check it for
        //matches in the rules I have specified.
        int[] newData = new int[data.length];
        
        //first rule: if there is a one on each side of any cell, flip the value of that middle cell.
        
        //second rule: if there are two cells of value '0' to the left of a cell with the value '1', make the cell directly
        //to the left of the '1' cell, '1'.
        
        //checking to see if rules are matched, and if they are, assigning the proper values to the new array.
        while(n<data.length-1){
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
    
}
