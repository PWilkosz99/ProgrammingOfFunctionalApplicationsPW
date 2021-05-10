package TrainModel;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TrainStation implements Serializable {
    String name;
    public List<Train> trainsList = new ArrayList<Train>();
    int capacity;
    int capacityLimit;

    public int getCapacity() {
        return trainsList.size();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacityLimit() {
        return capacityLimit;
    }

    public TrainStation(String name, int capacity, int capacityLimit) {
        this.name = name;
        this.capacity = capacity;
        this.capacityLimit = capacityLimit;
    }

    public TrainStation(String name, int capacity) {
        this.name = name;
        this.capacityLimit = capacity;
    }

    public TrainStation(String name) {
        this.name = name;
        this.capacityLimit = 0;
    }


    public void addTrain(Train train) {
        if (getCapacity() < capacityLimit) {
            trainsList.add(train);
            // capacity++;
        } else {
            System.err.println("Pojemność stacji przekroczona");
        }
    }

    void deleteTrain(Train train) {
        if (!trainsList.remove(train)) {
            System.out.println("Brak pociagu o podanej nazwie");
        }
    }

    public void deleteAllTrainsNamed(Train train) {
        // for (TrainModel.Train trains : trainsList) {
        // if(trains.compareTo(train)==1){
        // trainsList.remove(trains);
        // }
        // }
        for (int i = 0; i < trainsList.size(); i++) {
            String name = trainsList.get(i).name;
            if (name == train.name) {
                trainsList.remove(i);
            }
        }
    }

    void deleteLastTrain() {
        deleteTrain(trainsList.get(trainsList.size() - 1));
    }

    int countTrainState(TrainState s) {
        int count = 0;
        for (Train train : trainsList) {
            if (train.state == s) {
                count++;
            }
        }
        return count;
    }

    void printAllTrains() {
        for (Train train : trainsList) {
            System.out.println(String.format("Pociąg %s o numerze %d", train.name, train.number));
        }
    }

    Train search(String trainName) {
        for (Train train : trainsList) {
            if (train.getName() == trainName) {
                return train;
            }
        }
        return null;
    }

    List<Train> searchPartial(String name) {
        List<Train> trains = new ArrayList<>();
        for (Train train : trainsList) {
            if (train.getName().contains(name)) {
                trains.add(train);
            }
        }
        return trains;
    }

    List<Train> sortByName() {
        List<Train> trains = new ArrayList<Train>();
        for (Train train : trainsList) {
            trains.add(train);
        }
        Collections.sort(trains);
        return trains;
    }

    List<Train> sortByDepartureTime() {
        List<Train> trains = new ArrayList<Train>();
        for (Train train : trainsList) {
            trains.add(train);
        }
        Collections.sort(trains, new SortTrainsByDeparture());
        return trains;
    }

    Train longestTravelTime() {
        return Collections.max(trainsList, new CompareByTravelTime());
    }

    Train shortesTravelTime() {
        return Collections.min(trainsList, new CompareByTravelTime());
    }

    @Override
    public String toString() {
        return name;
    }

    public final void writeObject(Object o) throws IOException {

    }


}

class SortTrainsByDeparture implements Comparator<Train> {

    @Override
    public int compare(Train o1, Train o2) {
        // return o1.compareTo(o2);
        // if (o1.getDeparture() > o2.getDeparture()) {
        //     return 1;
        // } else {
        //     return 0;
        // }
        return o1.getDeparture() - o2.getDeparture();
    }

}

class CompareByTravelTime implements Comparator<Train> {

    @Override
    public int compare(Train o1, Train o2) {

        // if((o1.getFinishTime()-o1.getStartTime())>(o2.getFinishTime()-o2.getStartTime())){
        // return 1;
        // }else{
        // return -1;
        // }

        return (o1.getFinishTime() - o1.getStartTime()) - (o2.getFinishTime() - o2.getStartTime());
    }

}
