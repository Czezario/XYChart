package pl.sensors.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SensorsDaoUtils {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cezary",
                "postgres",
                "Cezary2019");

        return connection;
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
