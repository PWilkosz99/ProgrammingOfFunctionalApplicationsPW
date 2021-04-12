package com.company.Tests;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.company.LabEx.*;


public class MissingNumTest {
    public static void test(){

        Random random = new Random();

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 7; i++) {
            list.add(random.nextInt(10));
        }

        int[] res = MissingNum.missingNum(list, 10);

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
