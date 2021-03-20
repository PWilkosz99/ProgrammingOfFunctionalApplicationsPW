public class Train implements Comparable {
    String name;
    String cargo;
    int number;
    int capacity;
    int arrivalTime;
    int departureTime;
    TrainState state;

    Train(String name, int number, int capacity, String cargo) {
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.cargo = cargo;
    }

    public void printTrainCargo() {
        System.out.println(String.format("Pociąg o %s o numerze %d przwozi %s", name, number, cargo));
    }

    @Override
    public int compareTo(Object o) {

        Train temp = (Train) o;

        if (name == temp.name) {
            return 1;
        } else {
            return 0;
        }
    }
}
