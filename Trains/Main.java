public class Main {
    public static void main(String[] args) {

        Train train = new Train("Train1", 123, 500, "passenger", 13, 14, TrainState.New, 1, 24);
        Train train2 = new Train("Train2", 555, 200, "apples", 12, 13, TrainState.New, 7, 18);
        Train train3 = new Train("Train3", 632, 500, "passenger", 11, 12, TrainState.New, 9, 19);
        Train train4 = new Train("Train4", 412, 200, "food", 17, 18, TrainState.New, 5, 23);
        Train train5 = new Train("Train1", 412, 200, "food", 15, 16, TrainState.New, 13, 18);
        Train train6 = new Train("Train20", 412, 200, "food", 15, 16, TrainState.New, 13, 18);

        TrainStation station = new TrainStation("stacja1", 5);

        station.addTrain(train);
        station.addTrain(train2);
        station.addTrain(train3);
        station.addTrain(train4);
        station.addTrain(train5);
        System.out.println("\nDodaje stajce:\n");
        station.addTrain(train6);

        // System.out.println("\n\n\n");

        // station.printAllTrains();
        System.out.println("\nNajkrótszy czas przejazdu:\n");

        station.shortesTravelTime().print();

        System.out.println("\nNajdłuższy czas przejazdu:\n");

        station.longestTravelTime().print();

        System.out.println("\nSortuje po nazwie:\n");

        var a = station.sortByName();

        for (Train trains : a) {
            System.out.println(trains.name);
        }

        System.out.println("\nSortuje po czasie odjazdu:\n");

        var b = station.sortByDepartureTime();

        for (Train trains : b) {
            System.out.println(trains.name + " " + trains.getDeparture());
        }

        System.out.println("\nUsuwam pociag train2:\n");

        station.deleteTrain(train2);

        station.printAllTrains();

        System.out.println("\nUsuwam ostatni pociag :\n");

        station.deleteLastTrain();
        station.printAllTrains();

        System.out.println("\nUsuwam pociagi o nazwie Train1 :\n");

        station.deleteAllTrainsNamed(train);
        station.printAllTrains();

        //tworze i dodaje stacje do kontenera

        TrainStation station2 = new TrainStation("stacja2", 2);

        TrainStationContainer container = new TrainStationContainer();

        container.addStation(station);
        container.addStation(station2);

        System.out.println("\nPojemnosc stacji 2 :\n");
        System.out.println(station2.getCapacity());

        System.out.println("\nPociagi o statusie nowy na stacji1 :\n");
        System.out.println(station.countTrainState(TrainState.New));

        System.out.println("\nSzukam pociagu o nazwie Train3 :\n");
        System.out.println(station.search("Train999"));

        System.out.println("\nSzukam pociagu zawierajacego Tra :\n");
        System.out.println(station.searchPartial("Tra"));

        System.out.println("\nPrzepełnione stacje :\n");
        System.out.println(container.overcrowdedTrainStations());

    }
}