package Tests;
import LabEx.*;
import java.util.Random;
import SortAlgorithms.*;
import Exceptions.*;

public class SortTest {

    public static Sort sortMethod;

    public static void Test() {
        Random random = new Random();
        long tStart;
        long tEnd;
        long tDelta;


        int[] arrPesymistic1 = new int[100000];
        int[] arrPesymistic2 = new int[100000];
        int[] arrPesymistic3 = new int[100000];
        int[] arrPesymistic4 = new int[100000];
        int[] arrPesymistic5 = new int[100000];

        int[] arrOptimistic1 = new int[100000];
        int[] arrOptimistic2 = new int[100000];
        int[] arrOptimistic3 = new int[100000];
        int[] arrOptimistic4 = new int[100000];
        int[] arrOptimistic5 = new int[100000];

        for (int i = 0; i < arrOptimistic1.length; i++) {
            arrOptimistic1[i] = i;
            arrOptimistic2[i] = i;
            arrOptimistic3[i] = i;
            arrOptimistic4[i] = i;
            arrOptimistic5[i] = i;
            //System.out.println(arrPesymistic1.length-i);
            arrPesymistic1[i] = arrPesymistic1.length-i;
            arrPesymistic2[i] = arrPesymistic2.length-i;
            arrPesymistic3[i] = arrPesymistic3.length-i;
            arrPesymistic4[i] = arrPesymistic4.length-i;
            arrPesymistic5[i] = arrPesymistic5.length-i;
        }

        int[] arrExpected1 = new int[100000];
        int[] arrExpected2 = new int[100000];
        int[] arrExpected3 = new int[100000];
        int[] arrExpected4 = new int[100000];
        int[] arrExpected5 = new int[100000];

        for (int i = 0; i < arrExpected1.length; i++) {
            arrExpected1[i] = random.nextInt(10000);
            arrExpected2[i] = random.nextInt(10000);
            arrExpected3[i] = random.nextInt(10000);
            arrExpected4[i] = random.nextInt(10000);
            arrExpected5[i] = random.nextInt(10000);
        }

        try {

            System.out.println("BubbleSort:");
            sortMethod = new BubbleSort();

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrPesymistic1);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek pesymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrOptimistic1);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek optymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrExpected1);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek oczekiwany: " + tDelta + "ms");

            System.out.println("\nCoctailSort:");
            sortMethod = new CoctailSort();

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrPesymistic2);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek pesymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrOptimistic2);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek optymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrExpected2);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek oczekiwany: " + tDelta + "ms");

            System.out.println("\nCombSort:");
            sortMethod = new CombSort();

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrPesymistic3);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek pesymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrOptimistic3);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek optymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrExpected3);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek oczekiwany: " + tDelta + "ms");

            System.out.println("\nHeapSort:");
            sortMethod = new HeapSort();

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrPesymistic4);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek pesymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrOptimistic4);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek optymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrExpected4);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek oczekiwany: " + tDelta + "ms");

            System.out.println("\nSelectionSort:");
            sortMethod = new SelectionSort();

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrPesymistic5);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek pesymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrOptimistic5);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek optymistyczny: " + tDelta + "ms");

            tStart = System.currentTimeMillis();
            sortMethod.sort(arrExpected5);
            tEnd = System.currentTimeMillis();
            tDelta = tEnd - tStart;

            System.out.println("Przypadek oczekiwany: " + tDelta + "ms");
        } catch (EmptyArrayException ex) {
            System.err.print("Custom exception: "+ex);
        } catch (Exception e) {
            System.err.print(e);
        }

    }
}
