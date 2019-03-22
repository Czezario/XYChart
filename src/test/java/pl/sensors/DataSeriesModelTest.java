package pl.sensors;

import org.junit.Before;
import org.junit.Test;
import pl.sensors.dao.model.DataSeriesModel;
import pl.sensors.dao.model.DataSeriesGroup;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataSeriesModelTest {

    public static final int EXPECTED_SIZE = 2;

    private List<DataSeriesModel> dataSeriesModelList;

    @Before
    public void setUp() {
        dataSeriesModelList = new ArrayList<>();

        dataSeriesModelList.add(new DataSeriesModel("pl.basen.joz.CO2.L01"));
        dataSeriesModelList.add(new DataSeriesModel("pl.atlantis.poz.CO2.L02"));
        dataSeriesModelList.add(new DataSeriesModel("pl.oth.war.temp.L01"));
        dataSeriesModelList.add(new DataSeriesModel("pl.basen.joz.temp.L02"));
        dataSeriesModelList.add(new DataSeriesModel("pl.atlantis.poz.rh.L01"));
        dataSeriesModelList.add(new DataSeriesModel("pl.oth.war.rh.L02"));
    }

    @Test
    public void filterBySensorGroupTest() {
        List<DataSeriesModel> dataSeriesModelFilteredList = new ArrayList<>();

        for (DataSeriesModel series : dataSeriesModelList) {
            String charId = series.getCharId();
            if (charId.contains(DataSeriesGroup.RH.getName())) {
                dataSeriesModelFilteredList.add(series);
            }
        }

        System.out.println(dataSeriesModelFilteredList);

        assertEquals(EXPECTED_SIZE, dataSeriesModelFilteredList.size());
    }

    @Test
    public void filterBySensorInGroupTest() {
        List<DataSeriesModel> dataSeriesModelFilteredList = new ArrayList<>();

        for (DataSeriesModel series : dataSeriesModelList) {

        }

    }
}
