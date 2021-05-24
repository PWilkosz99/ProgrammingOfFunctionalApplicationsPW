package TrainModel;

public class TrainMatchedModel {
    public String name;
    public int departureTime;
    public int arrivalTime;
    public int travelTime;
    public int ticketCost;
    public int capacity;
    public int ticketID;

    public TrainMatchedModel(String name, int departureTime, int arrivalTime, int travelTime, int ticketCost, int capacity) {
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelTime = travelTime;
        this.ticketCost = ticketCost;
        this.capacity = capacity;
    }

    public TrainMatchedModel(String name, int departureTime, int arrivalTime, int ticketCost, int ticketID){
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.ticketCost = ticketCost;
        this.ticketID = ticketID;
    }

    public String getName() {

        return name;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getTicketCost() {
        return ticketCost;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public int getTicketID(){return ticketID;}
}
