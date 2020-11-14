import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 * Test class for Collinear.java
 *
 * @author
 * @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest {
    // ~ Constructor ........................................................
    @Test
    public void testConstructor() {
        new Collinear();
    }

    // ~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty() {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays", expectedResult,
                Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult,
                Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse() {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})", expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue() {
        int[] a3 = { 15, 5 };
        int[] a2 = { 5 };
        int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals(
                "countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",
                expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3)
                + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    // TODO: add more tests here. Each line of code and ech decision in
    // Collinear.java should
    // be executed at least once from at least one test.
    @Test
    public void testBinary() {
        int[] a1 = { 1, 5, 17, 103, 332 };
        int x1 = 103;
        int x2 = 18;

        boolean expectedFalse = false;
        boolean expectedTrue = true;
        assertEquals(expectedFalse, Collinear.binarySearch(a1, x2));
        assertEquals(expectedTrue, Collinear.binarySearch(a1, x1));
    }

    @Test
    public void testSort() {
        int[] test1 = { 55, 23, 42, 2, 105 };
        int[] expectedResult = { 2, 23, 42, 55, 105 };
        Collinear.sort(test1);
        assertArrayEquals(expectedResult, test1);
    }
}
