package main.java.com.company.UnitTests;

import main.java.com.company.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest extends Main {
    @Test
    @DisplayName("Test testowy")
    void testMethod() {
        Assertions.assertEquals(1, 1);
    }

}