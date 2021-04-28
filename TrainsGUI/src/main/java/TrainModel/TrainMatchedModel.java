package TrainModel;

public class TrainMatchedModel {
    public String name;
    public int departureTime;
    public int arrivalTime;
    public int travelTime;
    public int ticketCost;

    public TrainMatchedModel(String name, int departureTime, int arrivalTime, int travelTime, int ticketCost) {
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelTime = travelTime;
        this.ticketCost = ticketCost;
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
}
