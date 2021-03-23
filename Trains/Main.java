public class Main {
    public static void main(String[] args) {

        Train train = new Train("Train1", 123, 500, "passenger", 13, 14, TrainState.New, 1, 24);
        Train train2 = new Train("Train2", 555, 200, "apples", 12, 13, TrainState.New, 7, 18);
        Train train3 = new Train("Train3", 632, 500, "passenger", 11, 12, TrainState.New, 9, 19);
        Train train4 = new Train("Train4", 412, 200, "food", 17, 18, TrainState.New, 5 ,23);
        Train train5 = new Train("Train1", 412, 200, "food", 19, 20, TrainState.New, 17, 18);

        TrainStation station = new TrainStation("stacja1",5);

        station.addTrain(train);
        station.addTrain(train2);
        station.addTrain(train3);
        station.addTrain(train4);
        station.addTrain(train5);



        // station.printAllTrains();

        // System.out.println("\n\n\n");

        // station.deleteAllTrains(train);

        // station.printAllTrains();

        station.shortesTravelTime().print();

        station.longestTravelTime().print();

        // station.deleteTrain(train);

        // station.printAllTrains();

        // station.deleteLastTrain();

        // station.printAllTrains();
        // TrainStation station2 = new TrainStation("stacja2",0);

        // TrainStationContainer container = new TrainStationContainer();

        // container.addStation(station);
        // container.addStation(station2);


        
        // train.printTrainCargo();


        // container.emptyTrainStations();



    }
}