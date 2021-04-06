package Tests;
import LabEx.*;

public class SortDigitsTest {
    public static void test() throws Exception {

        int[] testarr= {1523, 5315, 67324, 5123, 63245};
        int[] res = SortDigits.reorderDigits(testarr, "desc");
      for (int i = 0; i < res.length; i++) {
          System.out.println(res[i]);
      }
    }
}
