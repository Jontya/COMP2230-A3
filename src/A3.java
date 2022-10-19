//---------------------------------------------------------------------------------------------------
/** COMP2240 A3
*** Jonty Atkinson (C3391110)
*** 19/10/22
***
*** A3:
*** Main program file. Creates and instance for each algorithm and ready every file inputted in the cmd
*** line, runs the program and prints the results
**/
//---------------------------------------------------------------------------------------------------

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class A3{
    public static void main(String[] args) throws Exception{
        // Ensures there is a valid number of cmd line arguments
        if(args.length > 2){
            for(int i = 2; i < args.length; i++){
                // Checks if each file inputted exists
                File tempFile = new File(args[i]);
                if(!tempFile.exists()){
                    System.out.println("File Does Not Exist!");
                    return;
                }
            }
            // Runs the actual program
            A3 A3 = new A3();
            A3.run(args);
        }
        else{
            System.out.println("Incorrect Command Line Arguments!");
            return;
        }
    }

    private void run(String[] args) throws Exception{
        // Process Lists
        ArrayList<Process> fixedProcesses = new ArrayList<>();
        ArrayList<Process> variableProcesses = new ArrayList<>();
        
        // Adds the data into the list
        for(int i = 2; i < args.length; i++){
            fixedProcesses.add(new Process(args[i], readFile(args[i])));
            variableProcesses.add(new Process(args[i], readFile(args[i])));
        }

        // Creates and runs the fixed-local algorithm
        Fixed fixedCPU = new Fixed(Integer.valueOf(args[0]), Integer.valueOf(args[1]), fixedProcesses, "Fixed-Local");
        fixedCPU.run();

        // Creates and runs the global-variable algorithm
        Variable variableCPU = new Variable(Integer.valueOf(args[0]), Integer.valueOf(args[1]), variableProcesses, "Variable-Global");
        variableCPU.run();

        // Prints the results
        System.out.println(fixedCPU.getSimulationReport());
        System.out.println(variableCPU.getSimulationReport());
    }

    private Queue<Integer> readFile(String filename) throws Exception{
        // Creates a queue for the files page instructions
        Queue<Integer> pageInstructions = new LinkedList<>();
        Scanner scanner = new Scanner(new File(filename));
        try{
            // Finds the file begin
            String next = "";
            while(!next.equals("begin")){
                next = scanner.next();
            }
            next = scanner.next();
            // Loops until the file ends
            while(!next.equals("end")){
                // Breaks if there is more than 50 pages
                if(pageInstructions.size() == 50){
                    break;
                }
                pageInstructions.add(Integer.valueOf(next));
                next = scanner.next();
            }
        } 
        finally{
            scanner.close();
        }
        return pageInstructions;
    }
}