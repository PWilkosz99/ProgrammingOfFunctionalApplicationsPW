import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try {
            TrainStation station = new TrainStation("Kraków Główny", 5);
            TrainStation station1 = new TrainStation("Warszawa Zachodnia", 3);
            TrainStation station2 = new TrainStation("Warszawa Centralna", 10);
            TrainStation station5 = new TrainStation("Warszawa Wschodnia", 4);
            TrainStation station6 = new TrainStation("Iława Główna", 2);
            TrainStation station7 = new TrainStation("Gdańsk Główny", 5);
            TrainStation station8 = new TrainStation("Gdańsk Wrzeszcz", 2);
            TrainStation station9 = new TrainStation("Gdańsk Oliwa", 2);
            TrainStation station10 = new TrainStation("Sopot", 1);
            TrainStation station11 = new TrainStation("Gdynia Główna", 7);

            
            var record = new TimeRecord("Express InterCity Premium", new String[] { "Kraków Główny", "Warszawa Centralna", "Gdańsk Główny", "Sopot", "Gdynia Główna"},
                    new int[] { 10, 13, 16,18,20 }, new int[] { 30, 10, 35, 10, 50 });
            var record2 = new TimeRecord("USTRONIE", new String[] {  "Kraków Główny", "Warszawa Zachodnia","Warszawa Centralna", "Warszawa Wschodnia", "Iława Główna", "Gdańsk Główny", "Gdańsk Wrzeszcz", "Gdańsk Oliwa","Sopot", "Gdynia Główna" },
                    new int[] { 2, 8, 5, 6, 9, 12, 13, 14 ,15, 16 }, new int[] { 20, 50, 10, 5,  5, 5, 5, 5, 5, 5 });
            var record3 = new TimeRecord("Express InterCity", new String[] {  "Kraków Główny", "Warszawa Zachodnia","Warszawa Centralna", "Warszawa Wschodnia", "Iława Główna", "Gdańsk Główny", "Gdańsk Wrzeszcz","Sopot", "Gdynia Główna" },
                    new int[] { 6, 8, 10, 11, 14, 16, 17, 18, 21 }, new int[] { 15, 20, 25, 35,  45, 10, 7, 9, 50 });
            var record4 = new TimeRecord("PKP Cargo", new String[] {  "Kraków Główny", "Gdańsk Główny", "Gdańsk Wrzeszcz","Sopot", "Gdynia Główna" },
                    new int[] { 6, 16, 17, 18, 21 }, new int[] { 15, 20, 25, 9, 50 }, "cargo");

            var container = new TrainStationContainer(station, station1, station2, station5, station6, station7, station8, station9, station10, station11);
            container.updateTimeTable(record, record3, record2, record4);

            LocalTime time = LocalTime.of(0, 0);

            while (time.compareTo(LocalTime.of(23, 50)) < 0) {
                container.tick(time);
                time = time.plusMinutes(10);

                if(time.compareTo(LocalTime.of(8, 20))  == 0)
                {
                  //  container.renovation("Warszawa Zachodnia");
                }                

                Thread.sleep(10);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}


class TimeRecord {
    String trainName;
    String[] stations;
    LocalTime[] arriveTimes;
    LocalTime[] DepartureTime;
    int count = 0;
    String cargo;
    int capacity;

    public TimeRecord(String trainName, String[] stations, int[] arriveTimes, int[] stationDelay) {
            this(trainName, stations, arriveTimes, stationDelay, "passanger", 50); 
    }

    public TimeRecord(String trainName, String[] stations, int[] arriveTimes, int[] stationDelay, String cargo) {
        this(trainName, stations, arriveTimes, stationDelay, cargo, 50);
    }

    public TimeRecord(String trainName, String[] stations, int[] arriveTimes, int[] stationDelay, int capacity) {
        this(trainName, stations, arriveTimes, stationDelay, "passanger", capacity);
    }

    public TimeRecord(String trainName, String[] stations, int[] arriveTimes, int[] stationDelay, String cargo, int capacity) {
        this.trainName = trainName;
        this.stations = stations;
        this.cargo = cargo;
        this.capacity = capacity;
        var listArriveTimes = new ArrayList<LocalTime>();
        var listDepartureTimes = new ArrayList<LocalTime>();

        for (int i = 0; i < stations.length; i++){
            var clc = LocalTime.of(arriveTimes[i], 0);
            listArriveTimes.add(clc);
            listDepartureTimes.add(clc.plusMinutes(stationDelay[i]));
        }
        var arriveTimesTypeDef = new LocalTime[listArriveTimes.size()];
        this.arriveTimes = listArriveTimes.toArray(arriveTimesTypeDef);

        var departureTimeTypeDef = new LocalTime[listDepartureTimes.size()];
        this.DepartureTime = listDepartureTimes.toArray(departureTimeTypeDef);
        return;
    }
}