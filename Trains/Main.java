public class Main {
    public static void main(String[] args) {

        Train train = new Train("Train1", 123, 500, "passenger");
        Train train2 = new Train("Train2", 555, 200, "apples");

        TrainStation station = new TrainStation("stacja1",5);
        TrainStation station2 = new TrainStation("stacja2",0);

        TrainStationContainer container = new TrainStationContainer();

        container.addStation(station);
        container.addStation(station2);

        train.printTrainCargo();

        container.emptyTrainStations();

        System.out.println(train.compareTo(train2));
    }
}