/**********************************************************************************************************************
 **********************************************************************************************************************
 *****     Course: CSC-364-002      Semester: Fall 2019      Professor: Rasib Khan      Student: Ryan Huffman     *****
 *****------------------------------------------------------------------------------------------------------------*****
 *****         Programming Assignment #2: Optimizing Profit of Available Projects Using Dynamic Programming       *****
 *****                                (Knapsack Without Repetition Problem)                                       *****
 *****____________________________________________________________________________________________________________*****
 *****                 This program is designed to solve the "Knapsack Without Repetition Problem"                *****
 *****           The basis of this "Knapsack problem" is a software company and their profit potential.           *****
 *****                                                                                                            *****
 *****     The program prompts the user to answer three key questions via the Console and a Scanner variable.     *****
 *****             1 - The number of employee work weeks that are available                                       *****
 *****             2 - The name of the input file (that will be read with a Scanner variable)                     *****
 *****             3 - The name of the output file (that a PrintWriter variable will use to output to)            *****
 *****       The program then stores the user responses to the three questions into three String variables.       *****
 *****                                                                                                            *****
 *****           The program then uses a try block to attempt to open the input file specified by the user,       *****
 *****                  a catch block to catch any errors while attempting to open the input file and             *****
 *****              a finally block that checks if the input and output files were not equal to null ...          *****
 *****              (input and output files were opened successfully) and then call the .close() method           *****
 *****                            on the files if the files were not equal to null.                               *****
 *****                                                                                                            *****
 *****                                                                                                            *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 *****                     MORE COMMENTS TO GO HERE ONCE PROGRAM IS FINISHED BEING WRITTEN                        *****
 **********************************************************************************************************************
 ******************************************************************************************************************** */

// IMPORTS of needed tools and plug-ins
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Knapsack {

    // CLASS VARIABLE(s) declaration(s)
    // Declare an ArrayList of Project objects
    private static ArrayList<Project> projects;


    public static void main(String[] args) {    // throws FileNotFoundException
        // Scanner variable to get user input from the console via keyboard input
        Scanner input = new Scanner(System.in);

        // String variables that store the input file's name and the output file's name
        ///// THESE VARIABLES WILL BE REMOVED AFTER TESTING! THESE NEED TO BE ESTABLISHED THROUGH USER INPUT! /////
        String inputFileName = "KnapsackData1.txt";
        String outputFileName = "Output3.txt";

        // Declare a Scanner variable to read in from the input file and initialize the variable to null
        Scanner dataFile = null;
        // Declare a PrintWriter variable to write to the output file and initialize the variable to null
        PrintWriter outputFile = null;

        // Declare an ArrayList of Project objects
        // ArrayList<Project> projects;

        // String variables used to store user input on how many employee work weeks are available,
            // the name of the input file and the name of the output file
        String workWeeks, nameOfInputFile, nameOfOutputFile;

        // Print to the console a prompt for the user to enter how many employee work weeks are available
        System.out.print("Enter the number of available employee work weeks: ");
        // Get the user's input and store it in the String variable workWeeks
            // Using .next(), because this is supposed to be an integer input, so there should be no spaces
        workWeeks = input.next();
        // Make sure the Scanner moves on to the next line
        input.nextLine();

        // Print to the console a prompt asking the user for the name of the input file
        System.out.print("Enter the name of input file: ");
        // Get the user's input and store it in the String variable nameOfInputFile
        nameOfInputFile = input.nextLine();

        // Print to the console a prompt asking the user for the name of the output file
        System.out.print("Enter the name of output file: ");
        // Get the user's input and store it in the String variable nameOfOutputFile
        nameOfOutputFile = input.nextLine();


        // try block used to attempt to open the input file specified by the user
        try{
            // Initialize the Scanner variable, dataFile
            dataFile = new Scanner(new File(inputFileName));
            // Initialize the PrintWriter variable, outputFile
            outputFile = new PrintWriter(outputFileName);

            // Initialize the ArrayList variable, projects
            projects = new ArrayList<>();


            // while loop to go through the input file, line by line, until the end of the input file
            while(dataFile.hasNextLine()){
                // String variable used to store the current line of text from the input file
                String line = dataFile.nextLine();
                // String array to split the above String line variable anywhere there is a blank space
                    // (will separate each line of input into: Project name, employee work weeks and profit)
                String[] split = line.split(" ");
                // Add to the ArrayList projects: name of Project, employee work weeks and profit
                    // (one at a time and separated by commas)
                projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
            }

            ///// TESTING TO MAKE SURE THE ArrayList projects GETS FILLED CORRECTLY AND PRINTS CORRECTLY /////
            outputFile.println(knapsack(10));
        }
        // catch block used for when the file that was specified by the user will not open
            // (it might not exist or there may be another error preventing the file from opening)
        catch(IOException ioE){
            System.out.println(ioE);
        }
        // finally block that executes as long as the program has not exited due to error
            // (executes regardless of what happens in the try and/or catch blocks)
        finally {
            // if statement that checks if the value of the dataFile (the input file specified by the user) is
                // not equal to null (the file was successfully opened) and
                // call the .close() method on the dataFile, if so
            if(dataFile != null){
                dataFile.close();
            }

            // if statement that checks if the value of the outputFile (the output file specified by the user) is
                // not equal to null (the file was successfully opened) and
                // call the .close() method on the outputFile, if so
            if(outputFile != null){
                outputFile.close();
            }
        }
    }

/*
            Knapsack without repetition
            Problem instance: (w1, v1), (w2, v2), …, (wn, vn), W
            Define K(w, j) = maximum value achievable with a
                knapsack of capacity w and only
                items 1, …, j available

            Recurrence relation:
            K(w, j) = max{K(w – wj, j – 1) + vj , K(w, j – 1)}
            w = weight (vertical column)

            Pseudocode:
            Initialize all K(0, j) = 0 and all K(w, 0) = 0
            for j = 1 to n:
	            for w = 1 to W:
		            if wj > w: K(w, j) = K(w, j – 1)
		            else: K(w, j) = max{K(w – wj, j – 1) + vj, K(w, j – 1)}

		    Return  K(W,n)


            Base cases:
            K(w, 0) = 0
            K(0, j) = 0


            Loop through items n down to 1.
            For each item, determine whether the array entry was gotten
            by including the item or
            by excluding the item.
            This will tell you whether to include the item in the solution.



            Runtime:    O(nW)
*/

    private static ArrayList<Project> knapsack(int maxWW){

        ArrayList<Project> maxProfitProjects = new ArrayList<>();
        int n = projects.size() + 1;
        int W = maxWW + 1;
        int[][] K = new int[n][W];
        int sj = projects.size();
        int sw = maxWW;

        for(int j = 1; j < n; j++){
            for(int w = 1; w < W; w++){
                if(projects.get(j - 1).getEmployeeWorkWeeks() > w){
                    K[j][w] = K[j - 1][w];
                } else{
                    K[j][w] = Math.max(K[j - 1][w - projects.get(j - 1).getEmployeeWorkWeeks()] +
                                    projects.get(j - 1).getProfitInThousands(), K[j - 1][w]);
                }
            }
        }

        for(int i = 0; i < K.length; i++){
            System.out.print("\n");
            for(int m = 0; m < K[i].length; m++){
                System.out.print(K[i][m] + " ");
            }
        }

        while( (sj > 0) && (sw > 0) ){
            sj--; /////////////// TO CLEAR WARNINGS!!!!!!! NEED TO WRITE CODE TO GET PROJECTS FROM 2D ARRAY
            sw--; /////////////// TO CLEAR WARNINGS!!!!!!! NEED TO WRITE CODE TO GET PROJECTS FROM 2D ARRAY
        }

        return maxProfitProjects;
    }
}
