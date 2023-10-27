package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class SQLconection {
    private Connection connection;

    public SQLconection(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Conexión exitosa a la bodega");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se ha podido conectar a la bodaga, habilita el acceso");
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
