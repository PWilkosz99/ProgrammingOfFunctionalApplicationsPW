package entity;

import javax.persistence.*;

@Entity
@Table(name = "trains")
public class TrainsEntity {
    private int id;
    private Integer cars;
    private Integer capacity;
    private Integer traveltime;
    private Integer state;

    public TrainsEntity(int id, Integer cars, Integer capacity, Integer traveltime, Integer state) {
        this.id = id;
        this.cars = cars;
        this.capacity = capacity;
        this.traveltime = traveltime;
        this.state = state;
    }

    public TrainsEntity(Integer cars, Integer capacity, Integer traveltime, Integer state) {
        this.cars = cars;
        this.capacity = capacity;
        this.traveltime = traveltime;
        this.state = state;
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
    @Column(name = "STATE")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
