package SortAlgorithms;
import Exceptions.*;

public class BubbleSort implements Sort {

    public void sort(int arr[]) throws EmptyArrayException {
        if (arr.length == 0) {
            throw new EmptyArrayException("BuubleSort: Empty array");
        }

        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
}
