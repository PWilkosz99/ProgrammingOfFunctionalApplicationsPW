public class Main {
    public static void main(String[] args) {

        Train train = new Train("Train1", 123, 500, "passenger");
        Train train2 = new Train("Train2", 555, 200, "apples");
        Train train3 = new Train("Train3", 632, 500, "passenger");
        Train train4 = new Train("Train4", 412, 200, "food");

        TrainStation station = new TrainStation("stacja1",5);

        station.addTrain(train);
        station.addTrain(train2);
        station.addTrain(train3);
        station.addTrain(train4);

        station.printAllTrains();

        station.deleteTrain(train);

        station.printAllTrains();

        station.deleteLastTrain();

        station.printAllTrains();
        // TrainStation station2 = new TrainStation("stacja2",0);

        // TrainStationContainer container = new TrainStationContainer();

        // container.addStation(station);
        // container.addStation(station2);


        
        // train.printTrainCargo();


        // container.emptyTrainStations();



    }
}