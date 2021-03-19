import java.util.Comparator;
import java.util.List;

public class TrainStation implements Comparator<String> {
    String name;
    List<Train> trainsList;
   public  int capacity;
    int capacityLimit;

    int getCapacity()
    {
        return trainsList.size();
    }

    TrainStation(String name, int capacity){
        this.name=name;
        this.capacity = capacity;
    }

    void addTrain(Train train) {
        if(getCapacity()<capacityLimit){
            trainsList.add(train);
           // capacity++;
        }
    }

    void deleteTrain(Train train){
        trainsList.remove(train);
    }

    void deleteLastTrain(){
        deleteTrain(trainsList.get(trainsList.size()-1));
    }

    // Train searchTrain(String trainName){
    //     // compare(trainName, );
    //     // trainsList.indexOf(o)
    // }

    @Override
    public int compare(String o1, String o2) {
        if(o1==o2){
            return 1;
        }else{
            return 0;
        }
    }
    
    @Override
    public String toString() {
             return name;
    }

}
