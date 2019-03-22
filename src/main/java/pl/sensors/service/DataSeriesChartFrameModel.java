package pl.sensors.service;

import org.jfree.chart.ChartFrame;
import org.jfree.data.time.TimeSeriesCollection;

public class DataSeriesChartFrameModel {
    private ChartFrame chartFrame;
    private TimeSeriesCollection timeSeriesCollection;

    public DataSeriesChartFrameModel() {
    }

    public DataSeriesChartFrameModel(ChartFrame chartFrame, TimeSeriesCollection timeSeriesCollection) {
        this.chartFrame = chartFrame;
        this.timeSeriesCollection = timeSeriesCollection;
    }

    public ChartFrame getChartFrame() {
        return chartFrame;
    }

    public void setChartFrame(ChartFrame chartFrame) {
        this.chartFrame = chartFrame;
    }

    public TimeSeriesCollection getTimeSeriesCollection() {
        return timeSeriesCollection;
    }

    public void setTimeSeriesCollection(TimeSeriesCollection timeSeriesCollection) {
        this.timeSeriesCollection = timeSeriesCollection;
    }

    @Override
    public String toString() {
        return "DataSeriesChartFrameModel{" +
                "chartFrame=" + chartFrame +
                ", timeSeriesCollection=" + timeSeriesCollection +
                '}';
    }
}
