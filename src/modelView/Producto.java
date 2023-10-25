
package modelView;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Producto {
    final String tabla = "estudiante";
    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void agregarStock(int cantidad, Connection connection) {
    this.cantidad += cantidad;
    
    // Actualiza la cantidad en la base de datos SQL
    if (connection != null) {
        try {
            String updateQuery = "UPDATE pedidos SET cantidad = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, this.cantidad);
            preparedStatement.setInt(2, this.id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error al actualizar la cantidad en la base de datos: " + e.getMessage());
        }
    }
}

    public void vender(int cantidad, Connection connection) {
    if (cantidad <= this.cantidad) {
        this.cantidad -= cantidad;

        // Actualiza la cantidad en la base de datos SQL
        if (connection != null) {
            try {
                String updateQuery = "UPDATE pedidos SET cantidad = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setInt(1, this.cantidad);
                preparedStatement.setInt(2, this.id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println("Error al actualizar la cantidad en la base de datos: " + e.getMessage());
            }
        }
    } else {
        System.out.println("No hay suficiente stock disponible.");
    }
}

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nPrecio: $" + precio + "\nCantidad en Stock: " + cantidad + "\n";
    }
}
