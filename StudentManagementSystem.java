import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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


    /*
        Decided on how I was going to create the student IDs...

        Random could have worked but would have to created
        an if statement to check for duplication and stored
        that information somewhere. For now the row number
        will be the student ID.

     */
    public static long countLineJava8() {

        Path path = Paths.get("filename.csv");

        // You can start this at any number for student IDs in my case 10000
        long lines = 10000;
        try {

            // much slower, this task better with sequence access
            //lines = Files.lines(path).parallel().count();

            lines += Files.lines(path).count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }


    public static void addStudent(){
        //TODO Add some useful code here

        /*
            Thing to work on:
            1. adding a student Id for each user added
            and making sure that the Id does not repeat.
            Randomization for the Ids might work well for this.

            this might help!
            https://stackoverflow.com/questions/20384127/creating-an-incremental-number-sequence-in-java
         */

        //Random rand = new Random();


        // Write To a File
        try {
            long studentId = countLineJava8();


            Scanner keyboard = new Scanner(System.in);
            System.out.print("Student first name: ");
            String firstName = keyboard.nextLine();

            System.out.print("Student last name: ");
            String lastName = keyboard.nextLine();

            System.out.print("Student graduation year: ");
            String gradYear = keyboard.nextLine();

            // By default FileWriter will overwrite the file. Need to append to true.
            FileWriter myWriter = new FileWriter("filename.csv", true);
            myWriter.write(studentId + "," +firstName + "," + lastName + "," + gradYear + "\n");

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
        //TODO Add some useful code here
        /*
        I though I might need to use a liner or binary search for this
        method but the link below might help with this.
        This might help with with method
        https://stackoverflow.com/questions/6016348/search-particular-column-value-from-csv-file-using-java
         */

        /*
            Reading a CSV File into an Array
            https://stackoverflow.com/questions/33034833/converting-csv-file-into-2d-array
        */

        //Create a 2D ArrayList
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("filename.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] array = new String[records.size()][0];
        records.toArray(array);

        for (int i = 0;i < array.length; i++){
            for(int j = 0;j < array[i].length;j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
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