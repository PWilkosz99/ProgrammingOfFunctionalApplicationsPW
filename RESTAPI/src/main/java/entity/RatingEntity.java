package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "rating", schema = "trainsapp", catalog = "")
public class RatingEntity {
    private int id;
    private Integer rate;
    private Integer trainid;
    private Integer stationid;
    private Timestamp date;

    public RatingEntity(Integer rate, Integer stationid) {
        this.rate = rate;
        this.stationid = stationid;
        this.date = new Timestamp((new Date()).getTime());
    }

    public RatingEntity() {}

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
    @Column(name = "rate")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "trainid")
    public Integer getTrainid() {
        return trainid;
    }

    public void setTrainid(Integer trainid) {
        this.trainid = trainid;
    }

    @Basic
    @Column(name = "stationid")
    public Integer getStationid() {
        return stationid;
    }

    public void setStationid(Integer stationid) {
        this.stationid = stationid;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingEntity that = (RatingEntity) o;

        if (id != that.id) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;
        if (trainid != null ? !trainid.equals(that.trainid) : that.trainid != null) return false;
        if (stationid != null ? !stationid.equals(that.stationid) : that.stationid != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (trainid != null ? trainid.hashCode() : 0);
        result = 31 * result + (stationid != null ? stationid.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
