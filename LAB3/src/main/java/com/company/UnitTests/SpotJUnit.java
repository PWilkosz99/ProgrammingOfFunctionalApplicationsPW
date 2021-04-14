package main.java.com.company.UnitTests;

import main.java.com.company.LabEx.Spot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpotJUnit {

    @Test
    @DisplayName("Spot: assertAll(7(0) + 2(5) = 7")
    void spot_Sum_Of_0_and_5() throws Exception {
        List<Integer> list = new ArrayList<Integer>(){
            {
          add(5);
          add(0);
          add(1);
          add(6);
          add(7);
          add(2);
          add(3);
          add(4);
          add(8);
            }
        };
        int r[] = Spot.spot(list, 7);
        //System.out.println(r[0]+"  "+r[1]);
        Assertions.assertAll(
                () -> assertTrue(r[0]==0),
                () -> assertTrue(r[1]==5)
        );
    }
}