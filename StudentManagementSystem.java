import java.io.*;
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
        Make call call to this method to turn csv into 2d array.
     */
    public static String [][] getArray(){
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

        return array;
    }

    /*
        This method will need to be edited later, we can get duplicates here
     */
    public static int generateId(){
        int min = 100000;
        int max = 999999;

        //Generate random int value from 100000 to 999999
        //int random_int = (int)(Math.random() * (max - min + 1) + min);

        //inline variable
        return (int)(Math.random() * (max - min + 1) + min);
    }


    public static void addStudent(){
        /*
            Thing to work on:
            1. adding a student Id for each user added
            and making sure that the Id does not repeat.
            Randomization for the Ids might work well for this.

            this might help!
            https://stackoverflow.com/questions/20384127/creating-an-incremental-number-sequence-in-java
         */

        // Write To a File
        try {
            int studentId = generateId();

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

    public static void viewStudents(){
        /*
            Reading a CSV File into an Array
            https://stackoverflow.com/questions/33034833/converting-csv-file-into-2d-array
        */
        String[][] array = getArray();

        for (int i = 0;i < array.length; i++){
            for(int j = 0;j < array[i].length;j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        // Print a 2D array
        //System.out.println(Arrays.deepToString((array)));
    }


    /*
        I though I might need to use a liner or binary search for this
        method but the link below might help with this.
        This might help with with method
        https://stackoverflow.com/questions/6016348/search-particular-column-value-from-csv-file-using-java
     */
    public static void findStudent(){
        String [][] array = getArray();

    }

    /*
        We will need to add some input validation to be sure the program does not crash
     */
    public static void main(String[] args) {
        createFile();

        Scanner keyboard = new Scanner(System.in);

        while(true){
            System.out.println("Please select from the following:");
            System.out.println("1. Add a new student");
            System.out.println("2. Check all student information");
            System.out.println("3. Check student by ID");
            System.out.println("4. Quit");
            System.out.print("User selection: ");

            int input = keyboard.nextInt();
            if (input == 1){
                addStudent();
            } else if (input == 2){
                viewStudents();
            } else if (input == 3){
                findStudent();
            } else if (input == 4){
                break;
            } else {
                System.out.println("Please select a number 1-4!");
            }
        }
    }
}