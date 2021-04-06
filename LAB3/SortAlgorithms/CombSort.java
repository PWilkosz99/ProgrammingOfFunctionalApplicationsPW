package SortAlgorithms;
import Exceptions.*;

public class CombSort implements Sort {

       int getNextGap(int gap)
       {
           gap = (gap*10)/13;
           if (gap < 1)
               return 1;
           return gap;
       }

       public void sort(int arr[]) throws EmptyArrayException
       {

        if(arr.length==0){
            throw new EmptyArrayException("CombSort: Empty array");
        }

           int n = arr.length;
           int gap = n;
           boolean swapped = true;

           while (gap != 1 || swapped == true)
           {
               gap = getNextGap(gap);
               swapped = false;
    
               for (int i=0; i<n-gap; i++)
               {
                   if (arr[i] > arr[i+gap])
                   {
                       int temp = arr[i];
                       arr[i] = arr[i+gap];
                       arr[i+gap] = temp;
    
                       swapped = true;
                   }
               }
           }
       }
    
}
