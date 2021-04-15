package main.java.com.company;

import main.java.com.company.SortAlgorithms.*;
import main.java.com.company.Tests.*;
import main.java.com.company.LabEx.*;


public class Main {

    public static void main(String[] args) throws Exception {
        //SortTest.Test();
        System.out.println("\nSpotTest\n");
        SpotTest.test();
        System.out.println("\nMissingNum\n");
        MissingNumTest.test();
        System.out.println("\nSortDigits\n");
        SortDigitsTest.test();
        System.out.println("\nKaprekar\n");
        System.out.println(Kaprekar.kaprekar(1531));
    }
}
