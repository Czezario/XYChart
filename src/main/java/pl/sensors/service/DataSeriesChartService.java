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

    private TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

    public ChartFrame createChartFrame() {
        System.out.println("DataSeriesChartService.createChartFrame()");

        JFreeChart freeChart = ChartFactory.createTimeSeriesChart(
                "SENSORS VPPLANT",
                "Date and Time",
                "Sensor Value",
                this.timeSeriesCollection,
                true,
                true,
                false
        );

        ChartFrame chartFrame = new ChartFrame("", freeChart);
        chartFrame.pack();

        return chartFrame;
    }

    public DataSeriesChartFrameModel createChart() {
        System.out.println("DataSeriesChartService.createChart()");

        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

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

        model.getTimeSeriesCollection().addSeries(timeSeries);
    }

    public void addToTimeSeriesCollection(List<DataSeriesModel> dataSeriesModels) {
        TimeSeries timeSeries = convertDataSeriesModelToTimeSeries(dataSeriesModels);
        this.timeSeriesCollection.addSeries(timeSeries);
    }

    public TimeSeries convertDataSeriesModelToTimeSeries(List<DataSeriesModel> dataSeriesModels) {
        TimeSeries timeSeries = new TimeSeries("dataSeriesModels");

        for (DataSeriesModel dataSeriesModel : dataSeriesModels) {
            LocalDateTime lastChange = dataSeriesModel.getLastChange();

            if (lastChange != null) {
                Instant instant = lastChange.atZone(ZoneId.systemDefault()).toInstant();
                timeSeries.add(new Second(Date.from(instant)), dataSeriesModel.getLastValue());
            }
        }

        return timeSeries;
    }

    public void refreshChart(DataSeriesChartFrameModel model) {
        TimeSeriesCollection timeSeriesCollection = model.getTimeSeriesCollection();

    }

    public void showChart(List<DataSeriesModel> dataSeriesModels) {
        System.out.println("DataSeriesChartService.showChart(" + dataSeriesModels + ")");

    }

    public TimeSeriesCollection getTimeSeriesCollection() {
        return timeSeriesCollection;
    }

    public void setTimeSeriesCollection(TimeSeriesCollection timeSeriesCollection) {
        this.timeSeriesCollection = timeSeriesCollection;
    }
}
