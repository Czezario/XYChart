package pl.sensors.dao.model;

public enum DataSeriesGroup {
    CO2("CO2"), TEMP("temp"), RH("rh"), VOLTAGE("voltage"), LUX("lux"), PIR("pir"), HUMIDITY("humidity"), TEMPERATURE("temperature");

    private String name;

    DataSeriesGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
