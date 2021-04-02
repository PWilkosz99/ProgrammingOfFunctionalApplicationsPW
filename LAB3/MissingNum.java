import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MissingNum {
    public static int[] missingNum(List<Integer> a, int max) {

        Set<Integer> missNums = new HashSet<Integer>();
        Boolean match = false;
        for (int i = 0; i < max; i++) {
            for (Integer integer : a) {
                if (i == integer) {
                    match = true;
                    break;
                }
                match=false;
            }
            if (match == false) {
                missNums.add(i);
            }
        }

        Object[] arr = missNums.toArray();
        int[] intarr = Arrays.stream(arr).mapToInt(o -> (int) o).toArray();
        return intarr;
    }
}
