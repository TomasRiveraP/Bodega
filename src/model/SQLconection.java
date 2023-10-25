package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLconection {
    private Connection connection;

    public SQLconection(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión a la base de datos exitosa.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
        }
    }
}
