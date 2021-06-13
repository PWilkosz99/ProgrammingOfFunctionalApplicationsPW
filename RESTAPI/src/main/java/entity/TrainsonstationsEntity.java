package entity;

import javax.persistence.*;

@Entity
@Table(name = "trainsonstations")
public class TrainsonstationsEntity {
    private int id;
    private int stationId;
    private int trainId;
    private Integer order;
    private Integer stime;

    public TrainsonstationsEntity(int stationId, int trainId) {
        this.stationId = stationId;
        this.trainId = trainId;
    }

    public TrainsonstationsEntity(int stationId, int trainId, Integer order) {
        this.stationId = stationId;
        this.trainId = trainId;
        this.order = order;
    }

    public TrainsonstationsEntity(){}

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
    @Column(name = "stationID")
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Basic
    @Column(name = "trainID")
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "storder")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Basic
    @Column(name = "stime")
    public Integer getStime() {
        return stime;
    }

    public void setStime(Integer stime) {
        this.stime = stime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainsonstationsEntity that = (TrainsonstationsEntity) o;

        if (stationId != that.stationId) return false;
        if (trainId != that.trainId) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stationId;
        result = 31 * result + trainId;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
}
