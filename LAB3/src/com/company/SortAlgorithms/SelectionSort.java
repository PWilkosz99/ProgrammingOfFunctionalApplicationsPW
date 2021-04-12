package com.company.SortAlgorithms;
import com.company.Exceptions.*;

public class SelectionSort implements Sort {
    public void sort(int arr[]) throws EmptyArrayException
    {
        int n = arr.length;

        
        if(n==0){
            throw new EmptyArrayException("SelectionSort: Empty array");
        }
        
        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
  
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
}
