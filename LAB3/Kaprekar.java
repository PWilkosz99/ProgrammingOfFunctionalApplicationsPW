import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Kaprekar {
    public static int kaprekar() {
        Random random = new Random();
        boolean different = false;
        int number = -1;
        int res = -1;
        int[] digits = { 0, 0, 0, 0 };
        List<Integer> digit = new ArrayList<Integer>();
        do {
            number = random.nextInt(1000);
            int j = 0;
            for (int i = number; i > 0; i /= 10) {
                digits[j] = i % 10;
                digit.add(i % 10);
                j++;
            }
            for (int i = 0; i < 4; i++) {
                for (int k = 0; k < 4; k++) {
                    if (digits[i] != digits[j]) {
                        different = true;
                        break;
                    }
                    if (different) {
                        break;
                    }
                }
            }
        } while (different != true);

        List<Integer> digitasc = new ArrayList<Integer>();
        List<Integer> digitdesc = new ArrayList<Integer>();
        int ii = 1;
        while (res != 6174) {

            while (digit.size() != 4) {
                digit.add(0);
            }

            Collections.sort(digit);
            digitasc.addAll(digit);
            Collections.reverse(digit);
            digitdesc.addAll(digit);

            int multiplier = 1000;

            int nasc = 0;
            int ndesc = 0;

            for (Integer dg : digitasc) {
                nasc += dg * multiplier;
                multiplier /= 10;
            }

            multiplier = 1000;

            for (Integer dg : digitdesc) {
                ndesc += dg * multiplier;
                multiplier /= 10;
            }

            res = ndesc - nasc;

            System.out.println(ndesc +" - " + nasc + " = "+ res+"\n");

            if (res == 6174) {
                return ii;
            }

            digit.clear();
            digitasc.clear();
            digitdesc.clear();
            ii++;

            for (int i = res; i > 0; i /= 10) {
                digit.add(i % 10);
            }
        }

        return -1;
    }
}
