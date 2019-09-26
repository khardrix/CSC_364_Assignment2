/**********************************************************************************************************************
 **********************************************************************************************************************
 *****     Course: CSC-364-002      Semester: Fall 2019      Professor: Rasib Khan      Student: Ryan Huffman     *****
 *****------------------------------------------------------------------------------------------------------------*****
 *****         Programming Assignment #2: Optimizing Profit of Available Projects Using Dynamic Programming       *****
 *****                                (Knapsack Without Repetition Problem)                                       *****
 *****____________________________________________________________________________________________________________*****
 *****                                             Main Class                                                     *****
 *****------------------------------------------------------------------------------------------------------------*****
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
 *****  This program has a private static void knapsack(int maxWW) {...} method that solves the Knapsack Problem. *****
 *****    After the Knapsack Problem is solved, the results are output to the output file specified by the user.  *****
 **********************************************************************************************************************
 **********************************************************************************************************************/

// IMPORTS of needed tools and plug-ins
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Knapsack {

    // CLASS VARIABLE declaration
    private static ArrayList<Project> projects; // Project ArrayList to store all Projects in input file
    private static ArrayList<Project> maxProfitProjects; // Project ArrayList to store solution Projects


    public static void main(String[] args) {

        // Scanner variable to get user input from the console via keyboard input
        Scanner input = new Scanner(System.in);

        // Declare a Scanner variable to read in from the input file and initialize the variable to null
        Scanner dataFile = null;
        // Declare a PrintWriter variable to write to the output file and initialize the variable to null
        PrintWriter outputFile = null;

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
            dataFile = new Scanner(new File(nameOfInputFile));
            // Initialize the PrintWriter variable, outputFile
            outputFile = new PrintWriter(nameOfOutputFile);

            // Initialize the ArrayList variable, projects
            projects = new ArrayList<>();

            // while loop to go through the input file, line by line, until the end of the input file
            while(dataFile.hasNextLine()){
                // String variable used to store the current line of text from the input file
                String line = dataFile.nextLine();
                // String array to split the above String line variable anywhere there is a blank space
                    // (will separate each line of input into: Project name, employee work weeks and profit),
                    // so that new Project Objects can be added to the ArrayList<Project> projects variable using the
                    // Project Class's 3-arg constructor, properly
                String[] split = line.split(" ");
                // Add to the ArrayList projects each Project in the input file specified by the user
                projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
            }

            // Call the knapsack method to solve the Knapsack Problem
            knapsack(Integer.parseInt(workWeeks));

            // int variable to store the sum of the profits of the solution to the Knapsack Problem
            int totalProfit = 0;

            // for loop to add the profit from each of the selected Projects in the solution to the Knapsack Problem
                // to the int variable, totalProfit
            for(int i = 0; i < maxProfitProjects.size(); i++){
                totalProfit += maxProfitProjects.get(i).getProfitInThousands();
            }

            // Print the results of the Knapsack Problem to the output file specified by the user
            outputFile.println("Number of projects available: " + projects.size() +
                    "\nAvailable employee work weeks: " + Integer.parseInt(workWeeks) +
                    "\nNumber of projects chosen: " + maxProfitProjects.size() +
                    "\nTotal profit: " + totalProfit);
            // for loop to print each Project in the solution to the Knapsack Problem to the output file
                // specified by the user
            for(int i = maxProfitProjects.size() - 1; i >= 0; i--){
                outputFile.println(maxProfitProjects.get(i));
            }

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


    // Method to solve the Knapsack Problem
    private static void knapsack(int maxWW){

        // LOCAL VARIABLE declarations
        maxProfitProjects = new ArrayList<>(); // initialize the maxProfitProjects ArrayList
        int n = projects.size() + 1; // store the size of the projects ArrayList + 1 to use in below nested for loops
        int W = maxWW + 1; // store the maximum allotted work weeks + 1 to use in below nested for loops
        int[][] K = new int[n][W]; // 2D int Array to store result matrix
        int sj = projects.size(); // to be used in bottom nested for loops to get solution Projects (rows)
        int sw = maxWW; // to be used in bottom nested for loops to get solution Projects (columns)

        // Nested for loops to go through the 2D int Array, K
        for(int j = 1; j < n; j++){
            for(int w = 1; w < W; w++){
                // if statement that checks if work weeks required by the current Project is greater than the remaining
                    // allotted work weeks
                if(projects.get(j - 1).getEmployeeWorkWeeks() > w){
                    // Set the value of K[j][w] to the cell above it
                    K[j][w] = K[j - 1][w];
                }
                else {
                    // Set the value of K[j][w] equal to the value that is greater, between
                        // ( (the profit of the cell above the current cell and to the left equal to the number of work
                        // weeks required by the current Project) + the profit of the current Project) and
                        // the cell above the current cell
                    K[j][w] = Math.max(K[j - 1][w - projects.get(j - 1).getEmployeeWorkWeeks()] +
                                    projects.get(j - 1).getProfitInThousands(), K[j - 1][w]);
                }
            }
        }

        // while loop to go through the solution 2D int matrix. The while loops ends when sj <= 0
        while(sj > 0){
            // if statement that checks if the current cell is equal to the cell above it
            if(K[sj][sw] == K[sj - 1][sw]){
                // Decrement sj
                sj--;
            } else {
                // Add the current Project to the solution Project ArrayList, maxProfitProjects
                maxProfitProjects.add(projects.get(sj - 1));
                // Decrease the value of sw by the number of work weeks required by the current Project
                sw -= projects.get(sj - 1).getEmployeeWorkWeeks();
                // Decrement sj
                sj--;
            }
        }
    }
}
