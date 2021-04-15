import java.time.LocalTime;
import java.util.*;

public class TrainStation {
    String name;
    List<Train> trainsList = new ArrayList<Train>();
    int capacityLimit;

    int getCapacity() {
        return trainsList.size();
    }

    TrainStation(String name, int capacity) {
        this.name = name;
        capacityLimit = capacity;
    }

    void addTrain(Train train) throws Exception {
        if (getCapacity() < capacityLimit) {
            trainsList.add(train);
        } else {
            throw new Exception("Pojemność stacji przekroczona");
        }
    }

    void deleteTrain(Train train) throws Exception {
        try {
            train.updateTimeAtStation(0, 1);
            trainsList.remove(train);
        } catch (NullPointerException e) {
            throw new Exception("Brak pociagu o podanej nazwie"); // lub train nie jest typu Train, gdy nie jest inny wyjątek
        }
    }
////////////////// CURRENT------DEPARTURE--------------------->
    void deleteTrain(Train train, LocalTime CurrentTime) throws Exception
    {
        if( train.getDepartureDate().isBefore(CurrentTime))
        {
            // opzoniony
            train.setState(TrainState.Delayed);
        }
        else{
            train.setState(TrainState.Scheduled);

        }
        deleteTrain(train);
    }

    // jw ale jak sie nazwy dublują to sie usuwaja
    void deleteAllSameName(Train train) {

        for (int i = 0; i < trainsList.size(); i++) {
            String name = trainsList.get(i).getName();
            if (name == train.getName()) {
                trainsList.remove(i);
            }
        }
    }

    void deleteLastTrain() throws Exception {
        deleteTrain(trainsList.get(trainsList.size() - 1));
    }

    // ile pociagow ma ten status
    int countTrainState(TrainState s) {
        int count = 0;
        for (Train train : trainsList) {
            if (train.getState() == s) {
                count++;
            }
        }
        return count;
    }

    void printAllTrains() {
        for (Train train : trainsList) {
            System.out.println(String.format("Pociąg %s o numerze %d", train.getName(), train.getNumber()));
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
    // zawiera okreslony ciag znakow
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
    // ktory jedzie pierwszy
    List<Train> sortByDepartureTime() {
        List<Train> trains = new ArrayList<Train>();
        for (Train train : trainsList) {
            trains.add(train);
        }
        //if()
        Collections.sort(trains, new SortTrainsByDeparture()); // zeby nie ruszac oryginalu
        return trains;
    }

    Train longestTravelTime() {
        if (trainsList.size() != 0) {
            return Collections.max(trainsList, new CompareByTravelTime());
        } else {
            return null;
        }
    }

    Train shortesTravelTime() {
        if (trainsList.size() != 0) {
            return Collections.min(trainsList, new CompareByTravelTime());
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return name;
    }

}

class SortTrainsByDeparture implements Comparator<Train> {

    @Override
    public int compare(Train o1, Train o2) {
        if (o1.getDeparture() > o2.getDeparture()) {
            return 1;
        } else {
            return 0;
        }
    }
}

class CompareByTravelTime implements Comparator<Train> {

    @Override
    public int compare(Train o1, Train o2) {
        return (o1.getFinishTime() - o1.getStartTime()) - (o2.getFinishTime() - o2.getStartTime());
    }
}
