import java.awt.Color;
import java.awt.BasicStroke;
import java.sql.*;
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


public class XYLineChart_AWT extends ApplicationFrame {

 public static void main(String[] args) throws SQLException, ClassNotFoundException {

  Connection connect = getConnection();

  XYSeriesCollection dataset = getXySeriesCollection(connect);



  XYLineChart_AWT chart = new XYLineChart_AWT("Dane z czujników",
          "Which Browser are you using?",
          dataset);
  chart.pack();

  RefineryUtilities.centerFrameOnScreen(chart);
  chart.setVisible(true);
 }


 public XYLineChart_AWT(String applicationTitle, String chartTitle, XYDataset dataset) {
  
  super(applicationTitle);
  JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle,"Category","Score", dataset, PlotOrientation.VERTICAL,
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

  ResultSet resultSet = statement.executeQuery("SELECT * FROM data_series WHERE char_id LIKE '%CO2%'");
  ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM data_series WHERE char_id LIKE '%CO2%'");
  final XYSeries CO2 = new XYSeries("CO2");
  final XYSeries CO22 = new XYSeries("CO22");

  int x=1;
  while(resultSet.next()) {
   CO2.add(x++, Double.parseDouble(resultSet.getString("last_value")));
  }
  while (resultSet2.next()){

    CO22.add(x++, Double.parseDouble(resultSet2.getString("last_value")));

  }
  XYSeriesCollection dataset = new XYSeriesCollection();
  dataset.addSeries(CO22);
  dataset.addSeries(CO2);
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
