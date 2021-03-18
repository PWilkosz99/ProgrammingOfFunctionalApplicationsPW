public class Main {
    public static void main(String[] args) {

        Train train = new Train("Train1", 123, 500, "passenger");
        Train train2 = new Train("Train2", 555, 200, "apples");
        train.printTrainCargo();

        System.out.println(train.compareTo(train2));
    }
}