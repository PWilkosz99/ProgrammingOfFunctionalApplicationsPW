package main.java.com.company.UnitTests;

import jdk.jfr.Description;
import main.java.com.company.Exceptions.SameDigitsException;
import main.java.com.company.LabEx.Kaprekar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KaprekarJUnit {

    @Test
    @DisplayName("Kaprekar: test kroków(case: 1234 - 3)")
    void kaprekar() throws SameDigitsException {
        Assertions.assertEquals(Kaprekar.kaprekar(1234), 3);
    }

    @Test
    @DisplayName("Kaprekar: test obecności wyjątku(case: 1111 - throw)")
    void kaprekar2() throws SameDigitsException {
        Assertions.assertThrows(SameDigitsException.class, () -> {
            Kaprekar.kaprekar(1111);
        });
    }
    @Test
    @DisplayName("Kaprekar: test obecności wyjątku(case: 1234 - does not throw)")
    void kaprekar3() throws SameDigitsException {
        Assertions.assertDoesNotThrow(() -> {
            Kaprekar.kaprekar(1234);
        });
    }
}