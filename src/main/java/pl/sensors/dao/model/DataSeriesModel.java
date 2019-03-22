package pl.sensors.dao.model;

import java.time.LocalDateTime;

public class DataSeriesModel {
    //    id, data_type, name, char_id, last_value, last_change, deviceid
    private int id;
    private String charId;
    private double lastValue;
    private LocalDateTime lastChange;

    public DataSeriesModel(String charId) {
        this.charId = charId;
    }


    public DataSeriesModel(int id, String charId, double lastValue, LocalDateTime lastChange) {
        this.id = id;
        this.charId = charId;
        this.lastValue= lastValue;
        this.lastChange = lastChange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharId() {
        return charId;
    }

    public void setCharId(String charId) {
        this.charId = charId;
    }

    public LocalDateTime getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDateTime lastChange) {
        this.lastChange = lastChange;
    }
    public double getLastValue() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue = lastValue;
    }

    @Override
    public String toString() {
        return "DataSeriesModel{" +
                "id=" + id +
                ", charId='" + charId + '\'' +
                ", lastValue=" + lastValue +
                ", lastChange=" + lastChange +
                '}';
    }
}
