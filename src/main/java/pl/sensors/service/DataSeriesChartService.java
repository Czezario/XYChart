package pl.sensors.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import pl.sensors.dao.model.DataSeriesModel;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class DataSeriesChartService {

    public  DataSeriesChartFrameModel createChart() {
        System.out.println("DataSeriesChartService.createChart()");

        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
        TimeSeries timeSeries = new TimeSeries("dataSeriesModels");

//        timeSeriesCollection.addSeries(timeSeries);


        JFreeChart freeChart = ChartFactory.createTimeSeriesChart(
                "SENSORS VPPLANT",
                "Date and Time",
                "Sensor Value",
                timeSeriesCollection,
                true,
                true,
                false
        );

        ChartFrame chartFrame = new ChartFrame("", freeChart);
        chartFrame.pack();
//        chartFrame.setVisible(true);

        return new DataSeriesChartFrameModel(chartFrame, timeSeriesCollection);
    }

    public void addTimeSeries(DataSeriesChartFrameModel model, List<DataSeriesModel> dataSeriesModels) {

        TimeSeries timeSeries = new TimeSeries("dataSeriesModels");

        for (DataSeriesModel dataSeriesModel : dataSeriesModels) {
            LocalDateTime lastChange = dataSeriesModel.getLastChange();

            if (lastChange != null) {
                Instant instant = lastChange.atZone(ZoneId.systemDefault()).toInstant();
                timeSeries.add(new Second(Date.from(instant)), dataSeriesModel.getLastValue());
            }
        }

        model. getTimeSeriesCollection().addSeries(timeSeries);
    }

    public void showChart(List<DataSeriesModel> dataSeriesModels) {
        System.out.println("DataSeriesChartService.showChart(" + dataSeriesModels + ")");
//        List<DataSeriesModel> dataSeriesModelList = dataSeriesDao.selectData();

//        TimeSeriesCollection dataset = new TimeSeriesCollection();
//
//        TimeSeries timeSeries = new TimeSeries("dataSeriesModels");
//
//        for (DataSeriesModel model : dataSeriesModels) {
//            LocalDateTime lastChange = model.getLastChange();
//
//            if (lastChange != null) {
//                Instant instant = lastChange.atZone(ZoneId.systemDefault()).toInstant();
//                timeSeries.add(new Second(Date.from(instant)), model.getLastValue());
//            }
//        }
//        dataset.addSeries(timeSeries);
//
//        JFreeChart chart = ChartFactory.createTimeSeriesChart(
//                "SENSORS VPPLANT",
//                "Date and Time",
//                "Sensor Value",
//                dataset,
//                true,
//                true,
//                false
//        );
//
//        ChartFrame chart1 = new ChartFrame("", chart);  //wyświetlenie na ekranie wykresów
//        chart1.pack();
//        chart1.setVisible(true);
    }
}
