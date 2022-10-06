import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class A3{
    public static void main(String[] args) throws Exception{
        if(args.length > 2){
            for(int i = 2; i < args.length; i++){
                File tempFile = new File(args[i]);
                if(!tempFile.exists()){
                    System.out.println("File Does Not Exist!");
                    return;
                }
            }
            A3 A3 = new A3();
            A3.run(args);
        }
        else{
            System.out.println("Incorrect Command Line Arguments!");
            return;
        }
    }

    private void run(String[] args) throws Exception{
        ArrayList<Process> processes = new ArrayList<>();
        for(int i = 2; i < args.length; i++){
            processes.add(new Process(args[i], readFile(args[i])));
        }

        Fixed fixedCPU = new Fixed(Integer.valueOf(args[0]), Integer.valueOf(args[1]), processes, "Fixed-Local");
        fixedCPU.primeReadyQueue();
        fixedCPU.run();

        //Variable variableCPU = new Variable();
        //variableCPU.run();

        System.out.println(fixedCPU.getSimulationReport());
    }

    private Queue<Integer> readFile(String filename) throws Exception{
        Queue<Integer> pageInstructions = new LinkedList<>();
        Scanner scanner = new Scanner(new File(filename));
        try{
            String next = "";
            while(!next.equals("begin")){
                next = scanner.next();
            }
            next = scanner.next();
            while(!next.equals("end")){
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