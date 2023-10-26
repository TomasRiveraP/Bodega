package modelView;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bodeg extends Bodega{
    private static Map<Integer, Producto> inventario;

    public Bodeg(Connection connection) {
        super(connection);  // Llama al constructor de la clase base (Bodega)
        this.inventario = new HashMap<>();
    }
    
    public void agregarProducto(Producto producto) {
        inventario.put(producto.getId(), producto);
    }

    public static Producto obtenerProducto(int id) {
        return inventario.get(id);
    }

    public Map<Integer, Producto> actualizarProducto(int id, int cantidad) {
        Producto producto = inventario.get(id);
        if (producto != null) {
            producto.agregarStock(cantidad, sqlConnection.getConnection());
            inventario.put(id, producto);
        } else {
            
        }
        return inventario;
    }
    public Map<Integer, Producto> mostrarProductos() {
        return inventario;
    }
    public Map<Integer, Producto> venderProducto(int id, int cantidad) {
        Producto productoVender = obtenerProducto(id);
        if (productoVender != null) {
            if (cantidad <= productoVender.getCantidad()) {
                productoVender.vender(cantidad, sqlConnection.getConnection());
                System.out.println("Venta realizada con exito.");
            } else {
                System.out.println("No hay suficiente stock disponible.");
                }
            } else {
                System.out.println("Producto no encontrado en la bodega.");
            }
        return inventario;
    }
    public void insertarProductosEnBaseDeDatos(Bodeg inventario, Connection connection) {
        
        if (connection != null) {
            try {
                String insertQuery = "INSERT INTO pedidos (id, nombre, precio, cantidad) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                for (Producto producto : inventario.mostrarProductos().values()) {
                    preparedStatement.setInt(1, producto.getId());
                    preparedStatement.setString(2, producto.getNombre());
                    preparedStatement.setDouble(3, producto.getPrecio());
                    preparedStatement.setInt(4, producto.getCantidad());

                    preparedStatement.executeUpdate();
                }

                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println("Error al insertar productos en la base de datos: " + e.getMessage());
            }
        }
    }
}
