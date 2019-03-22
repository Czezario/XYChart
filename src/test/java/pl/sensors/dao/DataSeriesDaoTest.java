package pl.sensors.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.sensors.dao.model.DataSeriesModel;

import java.util.List;

import static org.junit.Assert.*;

public class DataSeriesDaoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectData() {
        DataSeriesDao dao = new DataSeriesDao();
        List<DataSeriesModel> dataSeriesModelList = dao.selectData();

        System.out.println(dataSeriesModelList);
        assertNotNull("List model should not be null", dataSeriesModelList);
    }
}