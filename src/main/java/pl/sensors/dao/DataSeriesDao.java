package pl.sensors.dao;

import pl.sensors.dao.model.DataSeriesModel;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataSeriesDao {

    private static final String SELECT_DATA_SERIES_SQL = "SELECT * FROM DATA_SERIES WHERE LAST_CHANGE IS NOT NULL";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CHAR_ID = "char_id";
    private static final String COLUMN_LAST_VALUE = "last_value";
    private static final String COLUMN_LAST_CHANGE = "last_change";

    public List<DataSeriesModel> selectData() {
        List<DataSeriesModel> dataSeriesModelList = new ArrayList<>();

        try {
            Connection connection = SensorsDaoUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DATA_SERIES_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String charId = resultSet.getString(COLUMN_CHAR_ID);
                double lastValue = resultSet.getDouble(COLUMN_LAST_VALUE);
                Timestamp lastChange = resultSet.getTimestamp(COLUMN_LAST_CHANGE);

                LocalDateTime lastChangeLocalDateTime = null;
                if (lastChange != null) {
                    lastChangeLocalDateTime = lastChange.toLocalDateTime();
                }

                DataSeriesModel model = new DataSeriesModel(id, charId, lastValue, lastChangeLocalDateTime);
                dataSeriesModelList.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SensorsDaoUtils.closeConnection();
        }
        return dataSeriesModelList;
    }
}
