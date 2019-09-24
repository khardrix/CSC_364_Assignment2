/**********************************************************************************************************************
 **********************************************************************************************************************
 *****     Course: CSC-364-002      Semester: Fall 2019      Professor: Rasib Khan      Student: Ryan Huffman     *****
 *****------------------------------------------------------------------------------------------------------------*****
 *****         Programming Assignment #2: Optimizing Profit of Available Projects Using Dynamic Programming       *****
 *****____________________________________________________________________________________________________________*****
 *****                                              Object Class                                                  *****
 *****------------------------------------------------------------------------------------------------------------*****
 *****      This class is used to create the Project Objects to store in an ArrayList in the Knapsack Class.      *****
 *****                                                                                                            *****
 *****                                 This class stores three instance datum:                                    *****
 *****    - String representing the Project's name                                                                *****
 *****    - int representing the number of weeks required by employees to complete the Project                    *****
 *****    - int representing the amount of profit (measured in thousands) expected to be made from this Project   *****
 *****                                                                                                            *****
 *****    This class contains a 3-arg Constructor to initialize a Project Object with the above instance datum.   *****
 *****                   This class also contains getters and setters for all instance datum and                  *****
 *****                               overrides the Object Class's .toString() method.                             *****
 **********************************************************************************************************************
 ******************************************************************************************************************** */

public class Project {

    // States / Fields / Instance Data
    private String name;
    private int employeeWorkWeeks;
    private int profitInThousands;


    // 3-arg Constructor
    public Project(String name, int employeeWorkWeeks, int profitInThousands){
        this.name = name;
        this.employeeWorkWeeks = employeeWorkWeeks;
        this.profitInThousands = profitInThousands;
    }


    // Getter for private String name
    public String getName() {
        return name;
    }


    // Setter for private String name
    public void setName(String name) {
        this.name = name;
    }


    // Getter for private int employeeWorkWeeks
    public int getEmployeeWorkWeeks() {
        return employeeWorkWeeks;
    }


    // Setter for private int employeeWorkWeeks
    public void setEmployeeWorkWeeks(int employeeWorkWeeks) {
        this.employeeWorkWeeks = employeeWorkWeeks;
    }


    // Getter for private int profitInThousands
    public int getProfitInThousands() {
        return profitInThousands;
    }


    // Setter for private int profitInThousands
    public void setProfitInThousands(int profitInThousands) {
        this.profitInThousands = profitInThousands;
    }


    // Overriding the Object Class .toString() method
        // example return String: "Project0 6 30"
    @Override
    public String toString() {
        return this.name + " " + this.employeeWorkWeeks + " " + this.profitInThousands + "\n";
    }
}
