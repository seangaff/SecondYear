// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Sean Gaffney
 *  @version HT 2020
 */
import java.util.Arrays;

 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){
        for(int i = 1; i < a.length; i++) {
            double key = a[i];
            int j = i - 1;
            while(j >= 0 && a[j] > key) {
                a[j + 1] = a [j];
                j = j -1;
            }
            a[j + 1] = key;
        }
        return a;
    }//end insertionsort
	
	    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]) {
        for(int i = 0; i < a.length; i++) {
            int min = i;
            for(int j = i + 1; j < a.length; j++) {
                if(a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
        return a;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]) {
        quickSort(a, 0, a.length - 1);
        return a;
    }//end quicksort

    //quicksort recurisve
    private static void quickSort(double a[], int low, int high) {
        if (low < high) {
            int pivot = partition(a, low, high);
            quickSort(a, low, pivot - 1);
            quickSort(a, pivot + 1, high);
        }
    }

    //partition for quicksort
    private static int partition(double a[], int low, int high) {
        double pivot = a[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }
    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSort (double a[]) {
        int low = 0;
        int high = a.length - 1;

        double[] temp = Arrays.copyOf(a, a.length);

        for (int m = 1; m <= high - low; m = 2 * m) {
            for (int bottom = low; bottom < high; bottom += 2 * m) {
                int mid = bottom + m - 1;
                int top = bottom + 2 * m - 1;

                merge(a, temp, bottom, mid, (top < high) ? top : high);
            }
        }
        return a;
    }

    //
    private static void merge(double[] a, double[] temp, int bottom, int mid, int top) {
        int k = bottom, i = bottom, j = mid + 1;
        while (i <= mid && j <= top) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid && i < a.length) {
            temp[k++] = a[i++];
        }
        for (i = bottom; i <= top; i++) {
            a[i] = temp[i];
        }
    }

    //swaps two values
    private static void swap(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {

        //expirments done in main of test file
    }

 }//end class

