package com.company;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrainStationContainer {
    
    int trainNumber = 1000;

    Map<String, TrainStation> stations = new TreeMap<>();

    ArrayList<TimeRecord> timeRecords = new ArrayList<>();

    Map<String, Train> trainsInTravel = new TreeMap<>();

    TrainStationContainer(TrainStation... stations) {
        for (TrainStation trainStation : stations) {
            addStation(trainStation);
        }
    }

    void updateTimeTable(TimeRecord... record) {
        for (TimeRecord timeRecord : record) {
            this.timeRecords.add(timeRecord);
        }
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addStation(TrainStation station) {
        stations.put(station.name, station);
    }

    void removeStation(TrainStation station) {
        stations.remove(station.name);
    }

    void removeStation(String station) {
        stations.remove(station);
    }

    List<TrainStation> emptyTrainStations() {
        List<TrainStation> tmp = new ArrayList<TrainStation>();
        for (var a : stations.entrySet()) {
            if (a.getValue().getCapacity() == 0) {
                tmp.add(a.getValue());
            }
        }
        return tmp;
    }

    void showStationState(TrainStation station) {
        System.out.println(station.name + " " + station.trainsList.size());
    }

    TrainStation search(String renovationStation)
    {
        return stations.get(renovationStation);
    }

    // remoncik na sacji 
    void renovation(String renovationStation)
    {
        var station = search(renovationStation);
        if(station != null)
        {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!\nZe stacji zostaną usunięte pociagi alfabetyznie\n");            
            var alfa = station.sortByName();

            for (Train train : alfa) {
                System.out.println(train);            
            }

            System.out.println("\nKolejno po czasie podrózy\n");
            var fast = station.sortByDepartureTime();
            System.out.println("Pociąg o najktórszej trasie: " + station.shortesTravelTime());
            System.out.println("Pociąg o najdłóższej trasie: " + station.longestTravelTime());             
            

            for (Train train : fast) {
                System.out.println( "P " + train.getStartTimeDate() + " ; O " + train.getFinishTimeDate() +   " - " + train.getName() + " typ: ");
                train.printTrainCargo(); 
                try {
                    train.setState(TrainState.Cancelled);
                    trainsInTravel.put(train.getName(), train);
                    station.deleteTrain(train);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            removeStation(station);

            System.out.println("Stacja " + renovationStation + "\n\n");

        }
    }

    public void tick(LocalTime CurrentTime) {
        System.out.println("\n---------------" + CurrentTime + "------------------\n\0" );

        for (TimeRecord timeRecord : timeRecords) { // kazdy element w rozkladzie
            System.out.println("\n......" + timeRecord.trainName + ".........");
            for (int i = 0; i < timeRecord.stations.length; i++) // kazda stacja w rekordzie roskaldu -> podroz pkpka
            {
                var actualStationName = timeRecord.stations[i];
                var actualStation = stations.get(actualStationName);
                System.out.print("\n" + actualStation + " :");

                if (actualStation == null) {
                     System.out.println("Stacja w rozkladzie: " + actualStationName + 
                     " nie istnieje w świecie\nPopraw rozkład");
                    break;
                }
                 actualStation.printAllTrains();

                var trainInStation = actualStation.search(timeRecord.trainName.toString()); // na kazdej stacji sprawdzam czy jestpociag                                                                                 
                if (trainInStation == null) {
                    if (timeRecord.count >= timeRecord.stations.length) {
                     //   System.out.println("Dziekujemy za korzystanie z PKP XD");
                    }

                    else if (timeRecord.arriveTimes[timeRecord.count].compareTo(CurrentTime) < 0) {
                        if (i == timeRecord.count) {

                            System.out.println("\n+++ dodaje: " + timeRecord.trainName);
                            try {
                                var a = trainsInTravel.get(timeRecord.trainName); // nie wyrzuca wyjatku jak nie ma elementu w liscie...
                                if(a == null)
                                {
                                    System.out.println("pociag rozpoczyna podróz");

                                    actualStation.addTrain(new Train(timeRecord.trainName, trainNumber++, timeRecord.capacity , timeRecord.cargo, CurrentTime, timeRecord.DepartureTime[i], TrainState.New,
                                     timeRecord.arriveTimes[0], timeRecord.DepartureTime[timeRecord.DepartureTime.length-1])  );

                                }else{
                                    a.updateTimeAtStation(CurrentTime, timeRecord.DepartureTime[i]  );
                                    actualStation.addTrain(a);
                                }
                            }
                            catch (Exception e) {                                
                                e.printStackTrace();
                            }
                        }
                    }

                } else // jesli istnieje taka stacja w rozkladzie
                {
                    if (timeRecord.DepartureTime[i].compareTo(CurrentTime) < 1) {
                        System.out.println("\n--- wyjazd " + trainInStation);
                        try {
                            actualStation.deleteTrain(trainInStation, CurrentTime);
                            timeRecord.count++; // stacja zaliczona
                            trainsInTravel.put(trainInStation.getName(), trainInStation);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
