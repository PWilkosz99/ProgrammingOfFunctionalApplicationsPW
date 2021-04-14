package main.java.com.company.UnitTests;

import main.java.com.company.LabEx.SortDigits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortDigitsJUnit {

    @Test
    @DisplayName("ReorderDigits: Asc(true)")
    void reorderDigitsAsc_Arrays_Equals() throws Exception {
        int[] testarr= {1523, 5315, 67324};
        int[] sample = {1235, 1355, 23467};
        int[] res = SortDigits.reorderDigits(testarr,"asc");

        assertTrue(Arrays.equals(res, sample));
    }
    @Test
    @DisplayName("ReorderDigits: Desc(false)")
    void reorderDigitsDesc_Arrays_Not_Equals() throws Exception {
        int[] testarr= {1523, 5315, 67324};
        int[] sample = {1235, 1355, 23467};
        int[] res = SortDigits.reorderDigits(testarr,"desc");
                assertFalse(Arrays.equals(res, sample)); //intended false
    }
}