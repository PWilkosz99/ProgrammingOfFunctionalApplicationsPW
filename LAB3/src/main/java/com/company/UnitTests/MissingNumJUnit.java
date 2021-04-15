package main.java.com.company.UnitTests;

import main.java.com.company.LabEx.MissingNum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MissingNumJUnit {


    @Test
    @DisplayName("MissingNum: equals(missing 3)")
    void missingNum_is_missing_3() {
        ArrayList<Integer> list = new ArrayList<Integer>(){
            {
                add(0);
                add(1);
                add(2);
                //add(3);
                add(4);
            }
        };

        Assertions.assertEquals(3,MissingNum.missingNum(list,4)[0]);
    }

    @Test
    @DisplayName("MissingNum: assertAll(missing 1 and 2)")
    void missingNum_is_missing_2_and_3() {
        ArrayList<Integer> list = new ArrayList<Integer>(){
            {
                add(0);
                add(3);
                add(4);
            }
        };

        int[] samplearr = {1,2};
        int[] res = MissingNum.missingNum(list,4);

        Assertions.assertAll(
                () -> assertTrue(samplearr[0]==res[0]),
                () -> assertTrue(samplearr[1]==res[1])
        );
    }
}