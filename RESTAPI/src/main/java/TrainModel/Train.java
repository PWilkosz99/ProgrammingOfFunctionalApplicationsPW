package TrainModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Train implements Comparable<Train>, Serializable {
    @Serial
    private static final long serialVersionUID = 4159421413701554479L;
    String name;
    String cargo;
    transient TrainState state;
    int number;
    int capacity;
    int arrivalTime;
    int departureTime;
    int startTime;
    int finishTime;
    int travelTime;
    int ticketCost;
    public ArrayList<TrainStation> stationList;
    public ArrayList<Integer> timeTableList;

    public Train(String name, int number, int capacity, int travelTime, TrainState state) {
        this.name = name;
        this.number = number;//of cars in this case
        this.capacity = capacity;
        this.travelTime = travelTime;
        this.state = state;
        this.arrivalTime = 0;
        this.departureTime = 0;
        this.startTime = 0;
        this.finishTime = 0;
    }

    public Train(String name) {
        this.name = name;
        this.number = 0;
        this.capacity = 0;
        this.travelTime = 0;
        this.state = TrainState.New;
        this.arrivalTime = 0;
        this.departureTime = 0;
        this.startTime = 0;
        this.finishTime = 0;
        this.travelTime = 0;
    }

    public Train(String name, ArrayList<TrainStation> stationList, TrainState state) {
        this.name = name;
        this.state = state;
        this.stationList = stationList;
        this.ticketCost = ticketCost;
        this.number = 0;
        this.capacity = 0;
        this.travelTime = 0;
        this.arrivalTime = 0;
        this.departureTime = 0;
        this.startTime = 0;
        this.finishTime = 0;
        this.travelTime = 0;

    }

    public Train(String name, ArrayList<TrainStation> stationList, ArrayList<Integer> timeTableList, TrainState state, int ticketCost, int capacity) {
        this.name = name;
        this.state = state;
        this.stationList = stationList;
        this.timeTableList = timeTableList;
        this.ticketCost = ticketCost;
        this.capacity = capacity;
    }

    public void printTrainCargo() {
        System.out.println(String.format("Poci??g o %s o numerze %d przwozi %s", name, number, cargo));
    }

    public void print() {
        System.out.println(String.format("\t\t-----\nPoci??g o %s o numerze %d\nPrzyje??dza o: %d, Odjezdza o: %d\nWyjezdza z stacji poczatkowej o %d\nDojezdza do stacji koncowej o %d\nprzewozi %s\n\t\t-----\n", name, number, arrivalTime, departureTime, startTime, finishTime, cargo));
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public int getTicketCost() {
        return ticketCost;
    }

    public TrainState getTrainState() {
        return state;
    }

    int getDeparture() {
        return departureTime;
    }

    int getArrival() {
        return arrivalTime;
    }

    int getStartTime() {
        return startTime;
    }

    int getFinishTime() {
        return finishTime;
    }

    public void setState(TrainState s) {
        state = s;
    }

    public void decreaseCapacity() {
        capacity--;
    }

    public void increaseCapacity() {
        capacity++;
    }

    public int compareTo(Train o) {
        return name.compareTo(o.name);
    }
}
