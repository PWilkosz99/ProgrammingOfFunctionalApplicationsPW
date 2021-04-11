import SortAlgorithms.*;
import Tests.*;
import LabEx.*;
import Exceptions.*;

public class Main {

  public static Sort sortMethod;

  public static void main(String[] args) throws Exception {
    // SortTest.Test();
    System.out.println("\nSpotTest\n");
    SpotTest.test();
    System.out.println("\nMissingNum\n");
    MissingNumTest.test();
    System.out.println("\nSortDigits\n");
    SortDigitsTest.test();
    System.out.println("\nKaprekar\n");
    //System.out.println(Kaprekar.kaprekar());
     System.out.println(Kaprekar.kaprekar(222));
  }
}
