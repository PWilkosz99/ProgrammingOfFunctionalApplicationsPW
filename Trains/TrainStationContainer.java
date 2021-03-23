import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrainStationContainer {
    Map<String, TrainStation> stations;

    TrainStationContainer ()
    {
        //stations.entrySet()
        stations =  new TreeMap<>();
    }

    void addStation(TrainStation station){
        stations.put(station.name, station);
    }

    void removeStation(TrainStation station){
        stations.remove(station.name);
    }

    void removeStation(String station){
        stations.remove(station);
    }


    List<TrainStation> emptyTrainStations(){
       // stations.forEach(System.out.println(" ")); 
       List<TrainStation> tmp=  new ArrayList<TrainStation>(); 
        for (var a  : stations.entrySet()) {
            if(a.getValue().capacity == 0)           
            {
                tmp.add(a.getValue());
            //System.out.println(a.getKey() + " - " + a.getValue().toString());
            }
        }
        return tmp;
    }

    void showStationState(TrainStation station){
        System.out.println(station.name+" "+station.trainsList.size());
    }
}
