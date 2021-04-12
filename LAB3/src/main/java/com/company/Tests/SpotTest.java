package main.java.com.company.Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import main.java.com.company.LabEx.*;

public class SpotTest {
    public static void test() throws Exception {

        Random random = new Random();
        int m = 1;

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            list.add(m*random.nextInt(1000));
            m *=-1;
        }
        int r[] = Spot.spot(list, 523);
        System.out.println(r[0]+"  "+r[1]);
    }
}
