import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *                              Insertion       Selection      Merge        Quick
 * numbers1000.txt              5.38ms          3.36ms         0.37ms       0.81ms
 * numbers1000Duplicates        0.20ms          0.69ms         0.63ms       0.36ms
 * numbersNearlyOrdered1000     0.03ms          0.36ms         0.69ms       0.07ms  
 * numbersReverse1000           0.19ms          0.30ms         0.69ms       0.39ms
 * numbersSorted1000            0.00ms          0.28ms         0.14ms       0.61ms    
 * numbers10000                 8.41ms          24.90ms        2.16ms       0.93ms   
 */

/**
 * Test class for SortComparison.java
 *
 * @author Sean Gaffney
 * @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest {
    // ~ Constructor ........................................................
    @Test
    public void testConstructor() {
        new SortComparison();
    }

    // ~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {
        double[] a = new double[0];
        assertEquals(a, SortComparison.selectionSort(a));
        assertEquals(a, SortComparison.insertionSort(a));
        assertEquals(a, SortComparison.mergeSort(a));
        assertEquals(a, SortComparison.quickSort(a));
    }

    @Test
    public void testInsertionSort() {
        double a[] = {6, 1, 11, 19, 4, 13, 20, 0, 10, 2, 18, 16, 3, 7, 14, 17, 8, 12, 9, 5, 15};
        double sorted[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        assertTrue(Arrays.equals(sorted, SortComparison.insertionSort(a)));
    }

    @Test
    public void testSelectionSort() {
        double a[] = {6, 1, 11, 19, 4, 13, 20, 0, 10, 2, 18, 16, 3, 7, 14, 17, 8, 12, 9, 5, 15};
        double sorted[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        assertTrue(Arrays.equals(sorted, SortComparison.selectionSort(a)));
    }

    @Test
    public void testQuickSort() {
        double a[] = {6, 1, 11, 19, 4, 13, 20, 0, 10, 2, 18, 16, 3, 7, 14, 17, 8, 12, 9, 5, 15};
        double sorted[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        assertTrue(Arrays.equals(sorted, SortComparison.quickSort(a)));
    }

    @Test
    public void testMergeSort() {
        double a[] = {6, 1, 11, 19, 4, 13, 20, 0, 10, 2, 18, 16, 3, 7, 14, 17, 8, 12, 9, 5, 15};
        double sorted[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        assertTrue(Arrays.equals(sorted, SortComparison.mergeSort(a)));
    }

    // ----------------------------------------------------------
    /**
     * Main Method. Use this main method to create the experiments needed to answer
     * the experimental performance questions of this assignment.
     * 
     * @throws FileNotFoundException
     *
     */
    public static void main(String[] args) throws IOException {
        String[] files = new String[]{
        "/Users/seangaffney/Documents/Code/SecondYear/AlgorithmsAndDataStructuresII/Assignment1/numbers1000.txt",
        "/Users/seangaffney/Documents/Code/SecondYear/AlgorithmsAndDataStructuresII/Assignment1/numbers1000Duplicates.txt",
        "/Users/seangaffney/Documents/Code/SecondYear/AlgorithmsAndDataStructuresII/Assignment1/numbersNearlyOrdered1000.txt",
        "/Users/seangaffney/Documents/Code/SecondYear/AlgorithmsAndDataStructuresII/Assignment1/numbersReverse1000.txt",
        "/Users/seangaffney/Documents/Code/SecondYear/AlgorithmsAndDataStructuresII/Assignment1/numbersSorted1000.txt",
        "/Users/seangaffney/Documents/Code/SecondYear/AlgorithmsAndDataStructuresII/Assignment1/numbers10000.txt"
        };

        for (String file : files) {
            ArrayList<Double> doubles = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                while (line != null) {
                    line = br.readLine();
                    if (line != null) doubles.add(Double.parseDouble(line));
                }
                
                double insertionTime = 0;
                for(int i = 0; i < 3; i++) {
                    double[] a = resetArray(doubles);
                    double startTime = System.nanoTime();
                    SortComparison.insertionSort(a);
                    double endTime = System.nanoTime();
                    double duration = (endTime - startTime) / 1000000;
                    insertionTime += duration;
                }
                System.out.println("Insertion Sort time for " + file.split("/")[file.split("/").length - 1] + ": " + insertionTime/3 + "ms");
                
                double selectionTime = 0;
                for(int i = 0; i < 3; i++) {
                    double[] a = resetArray(doubles);
                    double startTime = System.nanoTime();
                    SortComparison.selectionSort(a);
                    double endTime = System.nanoTime();
                    double duration = (endTime - startTime) / 1000000;
                    selectionTime += duration;
                }
                System.out.println("Selection Sort time for " + file.split("/")[file.split("/").length - 1] + ": " + selectionTime/3 + "ms");


                double mergeTime = 0;
                for(int i = 0; i < 3; i++) {
                    double[] a = resetArray(doubles);
                    double startTime = System.nanoTime();
                    SortComparison.mergeSort(a);
                    double endTime = System.nanoTime();
                    double duration = (endTime - startTime) / 1000000;
                    mergeTime += duration;
                }
                System.out.println("Merge Sort time for " + file.split("/")[file.split("/").length - 1] + ": " + mergeTime/3 + "ms");

                double quickTime = 0;
                for(int i = 0; i < 3; i++) {
                    double[] a = resetArray(doubles);
                    double startTime = System.nanoTime();
                    SortComparison.quickSort(a);
                    double endTime = System.nanoTime();
                    double duration = (endTime - startTime) / 1000000;
                    quickTime += duration;
                }
                System.out.println("Quick Sort time for " + file.split("/")[file.split("/").length - 1] + ": " + quickTime/3 + "ms");
            }
        }
    }
    private static void printArray(double[] a) {
        for (double d : a) {
            System.out.print(d + ", ");
        }
        System.out.println();
    }


    private static double[] resetArray(ArrayList<Double> doubles) {
        double[] a;
        a = new double[doubles.size()];
        for (int j = 0; j < a.length; j++) {
            a[j] = doubles.get(j);
        }
        return a;
    }
}