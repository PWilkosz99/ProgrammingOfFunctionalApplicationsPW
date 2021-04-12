package main.java.com.company.UnitTests;
import main.java.com.company.Exceptions.EmptyArrayException;
import main.java.com.company.SortAlgorithms.*;
import static java.time.Duration.ofMillis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortJUnit {
    public static Sort sortMethod;


    int[] sample = {2, 3, 3, 3, 5, 5 ,5, 5, 6, 7, 7, 9, 23, 23 ,32 ,54, 54, 63, 76, 76, 76, 78, 89, 345, 354, 456, 568, 643, 651, 754,5667, 5865};

    @Test
    @DisplayName("BubbleSort: ArrayEquals")
    void bubbleSort() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new BubbleSort();
        sortMethod.sort(arr);
        Assertions.assertArrayEquals(arr, sample);
    }

    @Test
    @DisplayName("BubbleSort: Timeout(10s)")
    void bubbleSortTimeout() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new BubbleSort();
        assertTimeout(ofMillis(10), () -> {
            sortMethod.sort(arr);
            //Thread.sleep(100);
        });
    }

    @Test
    @DisplayName("CoctailSort: ArrayEquals")
    void coctailSort() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new CoctailSort();
        sortMethod.sort(arr);
        Assertions.assertArrayEquals(arr, sample);
    }

    @Test
    @DisplayName("CoctailSort: Timeout(10s)")
    void coctailSortTimeout() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new CoctailSort();
        assertTimeout(ofMillis(10), () -> {
            sortMethod.sort(arr);
        });
    }

    @Test
    @DisplayName("CombSort: ArrayEquals")
    void combSort() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new CombSort();
        sortMethod.sort(arr);
        Assertions.assertArrayEquals(arr, sample);
    }

    @Test
    @DisplayName("CombSort: Timeout(10s)")
    void combSortSortTimeout() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new CombSort();
        assertTimeout(ofMillis(10), () -> {
            sortMethod.sort(arr);
        });
    }

    @Test
    @DisplayName("HeapSort: ArrayEquals")
    void heapSort() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new HeapSort();
        sortMethod.sort(arr);
        Assertions.assertArrayEquals(arr, sample);
    }

    @Test
    @DisplayName("HeapSort: Timeout(10s)")
    void heapSortTimeout() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new HeapSort();
        assertTimeout(ofMillis(10), () -> {
            sortMethod.sort(arr);
        });
    }


    @Test
    @DisplayName("SelectionSort: ArrayEquals")
    void selectionSort() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new SelectionSort();
        sortMethod.sort(arr);
        Assertions.assertArrayEquals(arr, sample);
    }

    @Test
    @DisplayName("SelectionSort: Timeout(10s)")
    void selectionSortTimeout() throws EmptyArrayException {
        int[] arr = {5,7,23,3,651,23,643,5,754,78,568,3,5,345,3,63,7,456,76,89,76,9,5667,54,5,2,354,32,6,54,76,5865};
        sortMethod = new SelectionSort();
        sortMethod.sort(arr);
        assertTimeout(ofMillis(10), () -> {
            sortMethod.sort(arr);
        });
    }
}