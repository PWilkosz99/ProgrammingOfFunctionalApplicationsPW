package LabEx;
import java.util.List;

public class Spot {
    public static int[] spot(List<Integer> a, int target) throws Exception {
        int i = 0, j = 0;
        for (Integer firstint : a) {

            for (Integer secint : a) {
                if ((firstint + secint) == target) {
                    return new int[] { i, j };
                }
                j++;
            }
            i++;
        }
        throw new Exception("Brak oczekiwanej sumy");
    }
}
