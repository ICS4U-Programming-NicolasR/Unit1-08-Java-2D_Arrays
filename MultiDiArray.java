
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
* Reads a file and calculates mean median and mode.
*
* @author  Mr. Riscalas
* @version 1.0
* @since   2023-03-28
*/

public final class MultiDiArray {
    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private MultiDiArray() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the generateStudentMarks method.
     *
     * @param studentsArr //array of students
     * @param assignmentsArr //array of assignments
     * @return MARKS_AND_NAME //2d array
     *
     */
    public static String[][] generateStudentMarks(
            final String[] studentsArr, final String[] assignmentsArr) {
        // Create a random object
        final Random RANDOM = new Random();
        final int TEN = 10;
        final int MEAN = 75;
        // Create the 2D array
        final String[][] MARKS_AND_NAME = new String[studentsArr.length + 1][
                                                    assignmentsArr.length + 1];
        // Assign name to the base value
        MARKS_AND_NAME[0][0] = "Name";
        // First for loop that takes care of the students
        for (int i = 1; i <= studentsArr.length; i++) {
            MARKS_AND_NAME[i][0] = studentsArr[i - 1];
            // nested for loop taking care of the assignments
            for (int j = 1; j <= assignmentsArr.length; j++) {
                MARKS_AND_NAME[0][j] = assignmentsArr[j - 1];
                // generate a random gaussian number
                MARKS_AND_NAME[j][j] = String.valueOf(
                        RANDOM.nextGaussian() * TEN + MEAN);
            }
        }
        // return the 2d array
        return MARKS_AND_NAME;
    }

    /**
     * This is the modeCalc method.
     *
     * @param arr //2D array
     * @return CSVstring//2d array
     *
     */

    public static String arrayToCsvString(final String[][] arr) {
        // function for converting to a csv (just in case we need again)
        return Arrays.stream(arr)
            .map(row -> String.join(",", row))
            .collect(Collectors.joining("\n"));
    }
    /**
     * This is the main method.
     *
     * @param args //unused
     *
     */

    public static void main(final String[] args) {
        // Set file names and output file path
        final String STUDENT_FILE_NAME = "students.txt";
        final String ASSIGNMENT_FILE_NAME = "assignments.txt";
        final String OUTPUT_FILE_NAME = "Unit1-08-Output.csv";
        // Try to write/scan the file (avoids closing at the end)
        try (Scanner STUDENTS = new Scanner(new File(STUDENT_FILE_NAME));
            Scanner ASSIGNMENTS = new Scanner(new File(ASSIGNMENT_FILE_NAME));
            FileWriter WRITER = new FileWriter(OUTPUT_FILE_NAME)) {
            // Create a delimiter that tells the scanner to read the whole file
            final String DELIMITER = "\\Z";
            final String STUDENTS_TOTAL_FILE = STUDENTS.useDelimiter(DELIMITER)
                                                        .next();
            final String ASSIGNMENTS_TOTAL_FILE = ASSIGNMENTS.useDelimiter(
                                                            DELIMITER).next();
            // Split the file contents into arrays by line
            final String[] STUDENTS_ARR_INPUT = STUDENTS_TOTAL_FILE.split(
                                                        System.lineSeparator());
            final String[] ASSIGNMENTS_ARR_INPUT = ASSIGNMENTS_TOTAL_FILE.split(
                                                        System.lineSeparator());
            // Generate the student marks array
            final String[][] STUDENT_MARKS = generateStudentMarks(
                                    STUDENTS_ARR_INPUT, ASSIGNMENTS_ARR_INPUT);
            // Convert the student marks array to a CSV string
            final String CSV_STRING = arrayToCsvString(STUDENT_MARKS);
            System.out.println(CSV_STRING);
            // Write the CSV string to the output file
            writer.write(CSV_STRING);
            // update the file
            writer.flush();
        } catch (FileNotFoundException error) {
            System.out.println("File not found!");
        } catch (IOException error) {
            System.out.println("An error occurred while writing to the file: ");
            error.printStackTrace();
        }
    }
}
