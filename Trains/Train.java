import java.time.LocalTime;

public class Train implements Comparable<Train> {
    private String name;
    private String cargo;
    private int number;
    private int capacity;
    private int arrivalTime = 0;
    private int departureTime = 0;
    private TrainState state;
    private int startTime = 0;
    private int finishTime = 0;

    Train(String name, int number, int capacity, String cargo, LocalTime arrivalTime, LocalTime departureTime, TrainState state, LocalTime startTime, LocalTime finishTime) throws Exception{
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.cargo = cargo;
        this.arrivalTime=(arrivalTime.getHour()*60 + arrivalTime.getMinute()) ;
        this.departureTime=(departureTime.getHour()*60 + departureTime.getMinute());
        this.state=state;       
        this.startTime=(startTime.getHour()*60 + startTime.getMinute());
        this.finishTime=(finishTime.getHour()*60 + finishTime.getMinute());
    }

    //@Test
    public void printTrainCargo() {
        System.out.println(String.format("Pociąg o %s o numerze %d przwozi %s", name, number, cargo));
    }

    @Override
    public String toString() {
        return String.format("%s o numerze %d\nprzyjezdza o %s, odjezdza o %s\nzaczyna podróż o %s\nkończy podróż o %s\nprzewozi %s\nstatus: %s",
        getName(), getNumber(), getArrivalDate(),  getDepartureDate(), getStartTimeDate(), getFinishTimeDate(), getCargo(), getState());

    }

    String getName(){
        return name;
    }
    // z poszczególnej stacji
    int getDeparture(){
        return departureTime;
    }

    int getArrival(){
        return arrivalTime;
    }
    // cala podroż
    int getStartTime(){
        return startTime;
    }

    int getFinishTime(){
        return finishTime;
    }

    String getCargo() {
        return cargo;
    }

    // ---- date ---

    // z poszczególnej stacji
    LocalTime getDepartureDate() {
        return LocalTime.MIN.plusMinutes(departureTime);
    }

    LocalTime getArrivalDate() {
        return LocalTime.MIN.plusMinutes(arrivalTime);
    }

    // cala podroż
    LocalTime getStartTimeDate() {
        return LocalTime.MIN.plusMinutes(startTime);
    }

    LocalTime getFinishTimeDate() {
        return LocalTime.MIN.plusMinutes(finishTime);
    }

    // interfejs Comparable
    public int compareTo(Train o) {
        return name.compareTo(o.name);
    }   
    
    int getCapacity(){
        return capacity;
    }

    TrainState getState(){
        return state;
    }

    
    void setState(TrainState state){
        this.state = state;
    }

    int getNumber(){
        return number;
    }
    void updateTimeAtStation(int arriveTime, int departureTime)
    {
        this.arrivalTime = arriveTime;
        this.departureTime = departureTime;
    }

    public void updateTimeAtStation(LocalTime arriveTime, LocalTime departureTime) {
        updateTimeAtStation((arriveTime.getHour()*60 + arriveTime.getMinute())  , (departureTime.getHour()*60 + departureTime.getMinute()));
    }
    
}
