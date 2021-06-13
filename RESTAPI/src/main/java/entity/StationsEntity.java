package entity;

import javax.persistence.*;

@Entity
@Table(name = "stations")
public class StationsEntity {
    private int id;
    private String name;
    private Integer capacity;
    private Integer capacityLimit;

    public StationsEntity() { }

    public StationsEntity(String name){
        this.name=name;
    }

    public StationsEntity(String name, Integer capacity, Integer capacityLimit) {
        this.name = name;
        this.capacity = capacity;
        this.capacityLimit = capacityLimit;
    }

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "capacity")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "capacityLimit")
    public Integer getCapacityLimit() {
        return capacityLimit;
    }

    public void setCapacityLimit(Integer capacityLimit) {
        this.capacityLimit = capacityLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationsEntity that = (StationsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (capacity != null ? !capacity.equals(that.capacity) : that.capacity != null) return false;
        if (capacityLimit != null ? !capacityLimit.equals(that.capacityLimit) : that.capacityLimit != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        result = 31 * result + (capacityLimit != null ? capacityLimit.hashCode() : 0);
        return result;
    }
}
