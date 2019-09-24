/**********************************************************************************************************************
 **********************************************************************************************************************
 *****     Course: CSC-364-002      Semester: Fall 2019      Professor: Rasib Khan      Student: Ryan Huffman     *****
 *****------------------------------------------------------------------------------------------------------------*****
 *****         Programming Assignment #2: Optimizing Profit of Available Projects Using Dynamic Programming       *****
 *****____________________________________________________________________________________________________________*****
 *****                                                                                                            *****
 *****                                                                                                            *****
 *****                                                                                                            *****
 *****                                                                                                            *****
 **********************************************************************************************************************
 ******************************************************************************************************************** */

// IMPORTS of needed tools and plug-ins
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Knapsack {

    // CLASS VARIABLE(s) declaration(s)



    public static void main(String[] args) {    // throws FileNotFoundException
        String inputFileName = "KnapsackData1.txt";
        String outputFileName = "Output3.txt";

        Scanner dataFile = null;
        PrintWriter outputFile = null;

        ArrayList<Project> projects;

        try{
            dataFile = new Scanner(new File(inputFileName));
            outputFile = new PrintWriter(outputFileName);

            projects = new ArrayList<>();

            while(dataFile.hasNextLine()){
                String line = dataFile.nextLine();
                String[] split = line.split(" ");
                projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
            }

            outputFile.println(projects);
        }
        // catch block used for when the file will not open successfully.
        catch(IOException ioE){
            System.out.println(ioE);
        }
        finally {
            if(dataFile != null){
                dataFile.close();
            }

            if(outputFile != null){
                outputFile.close();
            }
        }
    }
}
