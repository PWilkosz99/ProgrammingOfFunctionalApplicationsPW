import java.io.Console;
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
        }else{
            System.err.println("Pojemność stacji przekroczona");
        }
    }

    void deleteTrain(Train train){
        if(trainsList.remove(train)){
            System.out.println("Brak pociagu o podanej nazwie");
        }
    }

    // void deleteAllTrains(Train train){
    //     for (Train tr : trainsList) {
            
    //     }
    // }

    int countTrainState(TrainState s) {
        int count = 0;
        for (Train train : trainsList) {
            if (train.state == s) {
                count++;
            }
        }
        return count;
    }

    void printAllTrains(){
        for (Train train : trainsList) {
            System.out.println(String.format("Pociąg %s o numerze %d", train.name, train.number));
        }
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
