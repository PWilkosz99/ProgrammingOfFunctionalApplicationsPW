package entity;

import TrainModel.TrainState;

import javax.persistence.*;

@Entity
@Table(name = "trains")
public class TrainsEntity {
    private int id;
    private String name;
    private Integer cars;
    private Integer capacity;
    private Integer traveltime;
    private TrainState state;
    private Integer ticketCost;

    public TrainsEntity(String name, Integer cars, Integer capacity, Integer traveltime, TrainState state) {
        this.name = name;
        this.cars = cars;
        this.capacity = capacity;
        this.traveltime = traveltime;
        this.state = state;
        this.ticketCost=15;
    }

    public TrainsEntity() { }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CARS")
    public Integer getCars() {
        return cars;
    }

    public void setCars(Integer cars) {
        this.cars = cars;
    }

    @Basic
    @Column(name = "CAPACITY")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "TRAVELTIME")
    public Integer getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(Integer traveltime) {
        this.traveltime = traveltime;
    }

    @Basic
    @Column(name = "STATE", columnDefinition = "INT")
    public TrainState getState() {
        return state;
    }

    public void setState(TrainState state) {
        this.state = state;
    }

    @Basic
    @Column(name = "TICKETCOST")
    public Integer getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(Integer ticketCost) {
        this.ticketCost = ticketCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainsEntity that = (TrainsEntity) o;

        if (id != that.id) return false;
        if (cars != null ? !cars.equals(that.cars) : that.cars != null) return false;
        if (capacity != null ? !capacity.equals(that.capacity) : that.capacity != null) return false;
        if (traveltime != null ? !traveltime.equals(that.traveltime) : that.traveltime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cars != null ? cars.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (traveltime != null ? traveltime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
