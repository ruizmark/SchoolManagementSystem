import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class StudentManagementSystem {

    //class constructor
    StudentManagementSystem(){


    }

    // Create a File
    public static void createFile(){
        try {
            File myObj = new File("filename.csv");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                //System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public static void addStudent(){
        // Write To a File
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Student first name: ");
            String firstName = keyboard.nextLine();

            System.out.print("Student last name: ");
            String lastName = keyboard.nextLine();

            System.out.print("Student graduation year: ");
            String gradYear = keyboard.nextLine();

            // By default FileWriter will overwrite the file. Need to append to true.
            FileWriter myWriter = new FileWriter("filename.csv", true);
            myWriter.write(firstName + "," + lastName + "," + gradYear + "\n");

            // Example for .csv
            //myWriter.write("John, Deer, 2024\n");

            // Remember to close the file when done.
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void findStudent(){
        // Reading a CSV File into an Array

        //Create a 2D ArrayList
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("filename.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(records);
    }

    public static void main(String[] args) {
        createFile();

        Scanner keyboard = new Scanner(System.in);

        while(true){
            System.out.println("Please select from the following:");
            System.out.println("1. Add a new student");
            System.out.println("2. Check student information");
            System.out.println("3. Quit");
            System.out.print("User selection: ");

            int input = keyboard.nextInt();
            if (input == 1){
                addStudent();
            } else if (input == 2){
                findStudent();
            } else if (input == 3){
                break;
            } else {
                System.out.println("Please select a number 1-3!");
            }
        }

//        // Java Read Files
//        try {
//            File myObj = new File("filename.csv");
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                System.out.println(data);
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
    }
}
