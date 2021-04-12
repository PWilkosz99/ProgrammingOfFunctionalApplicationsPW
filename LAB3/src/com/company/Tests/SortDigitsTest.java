package com.company.Tests;
import com.company.LabEx.*;

public class SortDigitsTest {
    public static void test() throws Exception {

        int[] testarr= {1523, 5315, 67324, 5123, 63245};
        System.out.println("DESC");
        int[] res = SortDigits.reorderDigits(testarr, "desc");
      for (int i = 0; i < res.length; i++) {
          System.out.println(res[i]);
      }
      int[] testarr2= {1523, 5315, 67324, 5123, 63245};
      System.out.println("ACS");
      int[] res2 = SortDigits.reorderDigits(testarr2, "asc");
    for (int i = 0; i < res.length; i++) {
        System.out.println(res2[i]);
    }
    }
}
