package entity;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketsEntity {
    private int id;
    private int buyerId;
    private Integer trainId;
    private Integer startStationId;
    private Integer finishStationId;
    private Integer departTime;
    private Integer travelTime;

    public TicketsEntity(int buyerId, Integer trainId, Integer startStationId, Integer finishStationId, Integer departTime, Integer travelTime) {
        this.buyerId = buyerId;
        this.trainId = trainId;
        this.startStationId = startStationId;
        this.finishStationId = finishStationId;
        this.departTime = departTime;
        this.travelTime = travelTime;
    }

    public TicketsEntity() {}

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
    @Column(name = "buyerID")
    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    @Basic
    @Column(name = "trainID")
    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "startStationID")
    public Integer getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(Integer startStationId) {
        this.startStationId = startStationId;
    }

    @Basic
    @Column(name = "finishStationID")
    public Integer getFinishStationId() {
        return finishStationId;
    }

    public void setFinishStationId(Integer finishStationId) {
        this.finishStationId = finishStationId;
    }

    @Basic
    @Column(name = "traveltime")
    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    @Basic
    @Column(name = "departuretime")
    public Integer getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Integer departTime) {
        this.departTime = departTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketsEntity that = (TicketsEntity) o;

        if (id != that.id) return false;
        if (buyerId != that.buyerId) return false;
        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;
        if (startStationId != null ? !startStationId.equals(that.startStationId) : that.startStationId != null)
            return false;
        if (finishStationId != null ? !finishStationId.equals(that.finishStationId) : that.finishStationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + buyerId;
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        result = 31 * result + (startStationId != null ? startStationId.hashCode() : 0);
        result = 31 * result + (finishStationId != null ? finishStationId.hashCode() : 0);
        return result;
    }
}
