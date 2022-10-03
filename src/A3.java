import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class A3{
    private static int frames;
    private static int timeQuantum;
    private static ArrayList<String> filenames;

    public static void main (String[] args) throws Exception{
        try{
            // Runs the program
            frames = Integer.parseInt(args[0]);
            timeQuantum = Integer.parseInt(args[1]);

            filenames = new ArrayList<>();
            for(int i = 0; i < args.length - 2; i++){
                filenames.add(args[i + 2]);
            }

            A3 a3 = new A3();
            a3.run();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing Arguments");
        }
    }

    private void run() throws Exception{
        ArrayList<Process> processes = new ArrayList<>();
        while(!filenames.isEmpty()){
           processes.add(readFile(filenames.remove(0)));
        }
    }

    private Process readFile(String processFile) throws Exception{
        File file = new File(processFile);
        if(!file.exists()){
            System.out.println("File Not Found");
            System.exit(0);
        }

        Scanner scanner = new Scanner(file);
        try{
            
        } 
        finally{
            scanner.close();
        }

        return new Process(processFile);
        
    }
}