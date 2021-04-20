public class Train implements Comparable<Train> {
    String name;
    String cargo;
    int number;
    int capacity;
    int arrivalTime;
    int departureTime;
    TrainState state;
    int startTime;
    int finishTime;
    int travelTime;

    Train(String name, int number, int capacity, int travelTime, TrainState state){
        this.name=name;
        this.number = number;//of cars in this case
        this.capacity = capacity;
        this.travelTime = travelTime;
        this.state = state;
        this.arrivalTime=0;
        this.departureTime=0;
        this.startTime=0;
        this.finishTime=0;
    }

    Train(String name){
        this.name=name;
        this.number = 0;
        this.capacity = 0;
        this.travelTime = 0;
        this.state = TrainState.New;
        this.arrivalTime=0;
        this.departureTime=0;
        this.startTime=0;
        this.finishTime=0;
        this.travelTime=0;
    }

    Train(String name, int number, int capacity, String cargo) {
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.cargo = cargo;
        this.arrivalTime=0;
        this.departureTime=0;
        this.state=TrainState.New;
        this.startTime=0;
        this.finishTime=0;
        this.travelTime=0;
    }

    Train(String name, int number, int capacity, String cargo, int arrivalTime, int departureTime) {
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.cargo = cargo;
        this.arrivalTime=arrivalTime;
        this.departureTime=departureTime;
        this.state=TrainState.New;
        this.startTime=0;
        this.finishTime=0;
    }

    Train(String name, int number, int capacity, String cargo, int arrivalTime, int departureTime, TrainState state){
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.cargo = cargo;
        this.arrivalTime=arrivalTime;
        this.departureTime=departureTime;
        this.state=state;
        this.startTime=0;
        this.finishTime=0;
    }

    Train(String name, int number, int capacity, String cargo, int arrivalTime, int departureTime, TrainState state, int startTime, int finishTime){
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.cargo = cargo;
        this.arrivalTime=arrivalTime;
        this.departureTime=departureTime;
        this.state=state;
        this.startTime=startTime;
        this.finishTime=finishTime;
    }


    public void printTrainCargo() {
        System.out.println(String.format("Pociąg o %s o numerze %d przwozi %s", name, number, cargo));
    }

    public void print(){
        System.out.println(String.format("\t\t-----\nPociąg o %s o numerze %d\nPrzyjeżdza o: %d, Odjezdza o: %d\nWyjezdza z stacji poczatkowej o %d\nDojezdza do stacji koncowej o %d\nprzewozi %s\n\t\t-----\n", name, number, arrivalTime, departureTime, startTime, finishTime, cargo));
    }

    String getName(){
        return name;
    }

    int getNumber() {return number;}

    int getCapacity() {return capacity;}

    int getTravelTime(){return travelTime;}

    TrainState getTrainState(){return state;}

    int getDeparture(){
        return departureTime;
    }

    int getArrival(){
        return arrivalTime;
    }

    int getStartTime(){
        return startTime;
    }

    int getFinishTime(){
        return finishTime;
    }

    public int compareTo(Train o) {
        return name.compareTo(o.name);
    }
    
}