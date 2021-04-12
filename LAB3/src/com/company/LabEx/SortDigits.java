package com.company.LabEx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortDigits {
    public static int[] reorderDigits(int[] arr, String str) throws Exception {
        if (str == "asc") {
            List<Integer> finalNumbers = new ArrayList<Integer>();
            for (int i = 0; i < arr.length; i++) {
                List<Integer> numbers = new ArrayList<Integer>();
                for (int j = arr[i]; j > 0; j /= 10) {
                    numbers.add(j%10);
                    //System.out.println(j%10);
                }
                Collections.sort(numbers);
                int multiplier = (int) Math.pow(10, numbers.size())/10;
                int sum = 0;
                for (Integer digit : numbers) {
                    sum += digit * multiplier;
                    multiplier /= 10;
                }
                finalNumbers.add(sum);
            }
            Object[] arrobj = finalNumbers.toArray();
            int[] intarr = Arrays.stream(arrobj).mapToInt(o -> (int) o).toArray();
            return intarr;
        } else if (str == "desc") {
            List<Integer> finalNumbers = new ArrayList<Integer>();
            for (int i = 0; i < arr.length; i++) {
                List<Integer> numbers = new ArrayList<Integer>();
                for (int j = arr[i]; j > 0; j /= 10) {
                    numbers.add(j%10);
                }
                Collections.sort(numbers);
                Collections.reverse(numbers);
                int multiplier = (int) Math.pow(10, numbers.size())/10;
                int sum = 0;
                for (Integer digit : numbers) {
                    sum += digit * multiplier;
                    multiplier /= 10;
                }
                finalNumbers.add(sum);
            }
            Object[] arrobj = finalNumbers.toArray();
            int[] intarr = Arrays.stream(arrobj).mapToInt(o -> (int) o).toArray();
            return intarr;
        } else {
            throw new Exception("Blene argumenty");
        }
    }
}
