import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import javax.swing.*;


public class XYLineChart_AWT extends ApplicationFrame {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        SwingUtilities.invokeLater(new Runnable() {
        @Override
            public void run() {
                new XYLineChartJFrame() {
                };
            }
        });

        Connection connect = getConnection();

//        XYSeriesCollection dataset = getXySeriesCollection(connect);

//  Object serie = dataset.getSeries();
//  System.out.println(serie);

//        XYLineChart_AWT chart = getXyLineChart_awt(dataset);

        //----------wyświetlenie na ekranie tabeli-----------------//
//        RefineryUtilities.centerFrameOnScreen(chart);
//        chart.setVisible(true);
    }


    private static XYLineChart_AWT getXyLineChart_awt(XYSeriesCollection dataset) {
        XYLineChart_AWT chart = new XYLineChart_AWT("Dane z czujników",
                "Which Browser are you using?",
                dataset);
        chart.pack();
        return chart;
    }


    public XYLineChart_AWT(String applicationTitle, String chartTitle, XYDataset dataset) {

        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle, "Category", "Score", dataset, PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }



    private static XYSeriesCollection getXySeriesCollection(Connection connect) throws SQLException {


        Statement statement = connect.createStatement();
        Statement statement2 = connect.createStatement();


        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT char_id FROM data_series");
        //ResultSet resultSet2 = statement2.executeQuery("SELECT char_id FROM data_series WHERE char_id = 'pl.atlantis.poz.temp.L1'");


        final XYSeries CO2 = new XYSeries("CO2");
        //inal XYSeries CO22 = new XYSeries("CO 2");

        ArrayList<String> lista = new ArrayList<String>();
        int x = 1;
        while (resultSet.next()) {
            lista.add(resultSet.getString("char_id"));
            String listaCzujników = ("x = " + x++ + " char_id = " + resultSet.getString("char_id"));
            System.out.println("x = " + x++ + " char_id = " + resultSet.getString("char_id"));
//            CO2.add(x++, Double.parseDouble(resultSet.getString("char_id")));
            //System.out.println(listaCzujników);
        }


//    ArrayList<String> lista2 = new ArrayList<String>();
//    int y=1;
//    while (resultSet2.next()){
//     lista2.add(resultSet2.getString("last_value"));
//     String listaCzujników2 = ("x = " + x++ + " last_value = " +Double.parseDouble(resultSet.getString("last_value")));
//   CO22.add(x++, Double.parseDouble(resultSet2.getString("last_value")));
//     System.out.println(listaCzujników2);
//
// }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(CO2);
        //dataset.addSeries(CO22);
        return dataset;
    }


    private static Connection getConnection() throws ClassNotFoundException, SQLException {

        /* Create MySQL Database Connection */
        Class.forName("org.postgresql.Driver");
        Connection connect = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cezary",
                "postgres",
                "Cezary2019");

        System.out.println("Opened database successfully");
        return connect;


    }
}
