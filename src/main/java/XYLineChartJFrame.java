import org.jfree.chart.ChartFrame;
import pl.sensors.dao.DataSeriesDao;
import pl.sensors.dao.model.DataSeriesModel;
import pl.sensors.dao.model.DataSeriesGroup;
import pl.sensors.service.DataSeriesChartFrameModel;
import pl.sensors.service.DataSeriesChartService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XYLineChartJFrame extends JFrame {

    private DataSeriesDao dataSeriesDao = new DataSeriesDao();

    private JComboBox dataSeriesComboBox;
    private ChartFrame chartFrame;

    private List<DataSeriesModel> dataSeriesModelList = new ArrayList<>();
    private List<DataSeriesModel> filteredBySensorGroup = new ArrayList<>();

    private DataSeriesChartService chartService = new DataSeriesChartService();
    private DataSeriesChartFrameModel dataSeriesChartFrameModel;
    private DataSeriesModel selectedDataSeriesModel;

    public XYLineChartJFrame() {
        super(" VPPlant ");

        setSize(850,130);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.setBackground(new Color(180, 138, 147));
        JLabel jLabel = new JLabel("   Wybierz grupę   ");
        jPanel.add(jLabel);

        JComboBox dataSeriesGroupComboBox = new JComboBox();
        jPanel.add(dataSeriesGroupComboBox);

        dataSeriesChartFrameModel = chartService.createChart();
        chartFrame = chartService.createChartFrame();

        DefaultComboBoxModel dataSeriesGroupComboBoxModel = new DefaultComboBoxModel();

        dataSeriesGroupComboBoxModel.addElement("");
        dataSeriesGroupComboBoxModel.addElement(DataSeriesGroup.CO2);
        dataSeriesGroupComboBoxModel.addElement(DataSeriesGroup.TEMP);
        dataSeriesGroupComboBoxModel.addElement(DataSeriesGroup.RH);
        dataSeriesGroupComboBoxModel.addElement(DataSeriesGroup.VOLTAGE);
        dataSeriesGroupComboBoxModel.addElement(DataSeriesGroup.LUX);
        dataSeriesGroupComboBoxModel.addElement(DataSeriesGroup.PIR);
        dataSeriesGroupComboBoxModel.addElement(DataSeriesGroup.HUMIDITY);
        dataSeriesGroupComboBoxModel.addElement(DataSeriesGroup.TEMPERATURE);



        dataSeriesGroupComboBox.setModel(dataSeriesGroupComboBoxModel);

        dataSeriesGroupComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(actionEvent);
                DataSeriesGroup selectedItem = (DataSeriesGroup) dataSeriesGroupComboBox.getSelectedItem();
                System.out.println(selectedItem);


                filteredBySensorGroup = filterBySensorGroup(selectedItem);
                System.out.println(filteredBySensorGroup);

                propagateSensorByGroup(filteredBySensorGroup);
            }
        });

//        JButton jButton = new JButton("Potwierdź");
//        jPanel.add(jButton);
        JLabel jLabel1 = new JLabel("  Wybierz sensor     ");
        jPanel.add(jLabel1);


        dataSeriesComboBox = new JComboBox();
//        dataSeriesComboBox.setPreferredSize(new Dimension(100, 100));
        jPanel.add(dataSeriesComboBox);

        dataSeriesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selectedDataSeriesModel = (DataSeriesModel) dataSeriesComboBox.getSelectedItem();
                System.out.println("Cezary selectedDataSeriesModel: " + selectedDataSeriesModel);
            }
        });

        JButton showChartButton = new JButton("Wykres");
        jPanel.add(showChartButton);
        showChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<DataSeriesModel> dataSeriesModelList = filterBySensor(selectedDataSeriesModel);
//                chartService.showChart(dataSeriesModelList);
//                chartService.createChart(dataSeriesModelList);
                dataSeriesChartFrameModel.getChartFrame().setVisible(true);
                chartService.addTimeSeries(dataSeriesChartFrameModel, dataSeriesModelList);
//                chartService.addToTimeSeriesCollection();
            }
        });


        Container container = getContentPane();
        container.add(jPanel);

        setVisible(true);

    }

    private List<DataSeriesModel> filterBySensorGroup(DataSeriesGroup dataSeriesGroup) {
        dataSeriesModelList = dataSeriesDao.selectData();

        Map<String, DataSeriesModel> dataSeriesModelMap = new HashMap<>();

        for (DataSeriesModel model : this.dataSeriesModelList) {
            String charId = model.getCharId();
            if (charId.contains(dataSeriesGroup.getName())) {
                dataSeriesModelMap.put(charId, model);
            }
        }

        System.out.println("dataSeriesModelMap: " + dataSeriesModelMap);

        return new ArrayList<>(dataSeriesModelMap.values());
    }

    private List<DataSeriesModel> filterBySensor(DataSeriesModel dataSeriesModel) {
        List<DataSeriesModel> dataSeriesModelFilteredList = new ArrayList<>();
        String selectedCharId = dataSeriesModel.getCharId();

        for (DataSeriesModel model : this.dataSeriesModelList) {
            String modelCharId = model.getCharId();
            if (modelCharId.equalsIgnoreCase(selectedCharId)) {
                dataSeriesModelFilteredList.add(model);
            }
        }

        return dataSeriesModelFilteredList;
    }

    private void propagateSensorByGroup(List<DataSeriesModel> dataSeriesModelList) {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

        for (DataSeriesModel series : dataSeriesModelList) {
            comboBoxModel.addElement(series);
        }

        dataSeriesComboBox.setModel(comboBoxModel);
    }
}
