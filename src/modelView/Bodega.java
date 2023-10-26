package modelView;

import java.sql.Connection;
import javax.swing.JOptionPane;
import model.SQLconection;
import view.FormularioBod;
import view.FormularioTra;

public class Bodega {
    public static String driver  = "com.mysql.cj.jdbc.Driver";
    public static String user = "root";
    public static String pass = "";
    public static String url = "jdbc:mysql://localhost:3306/test";
    ///Usuario y contraseña para login de trabajador
    public static final String USUARIO_VALIDO = "admin";
    public static final String CONTRASENA_VALIDA = "password";
    
    static FormularioBod Bodega = new FormularioBod();
    static FormularioTra Trab = new FormularioTra();
    
    public static SQLconection sqlConnection = new SQLconection(url, user, pass);
    
    public static Bodeg inventario = new Bodeg(sqlConnection.getConnection());
    
    public Bodega(Connection connection){
    }
    public static void vTrabajador() {
        Trab.setVisible(true);
    }
    
    public static void cerrarT() {
        Trab.setVisible(false);
    } 
    public static void cerrar() {
        Bodega.setVisible(false);
    } 
   
    public static void main(String[] args) {
        
        Bodega.setVisible(true);
        Connection connection = sqlConnection.getConnection();
        
        
        if (sqlConnection.getConnection() != null) {
            
            ///Bodeg inventario = new Bodeg(sqlConnection.getConnection());
            inventario.agregarProducto(new Producto(1, "Hamburguesa", 9, 10));
            inventario.agregarProducto(new Producto(2, "Perro", 9, 10));
            inventario.agregarProducto(new Producto(3, "Empanada", 9, 10));
            inventario.agregarProducto(new Producto(4, "Pizza", 9, 10));
            inventario.agregarProducto(new Producto(5, "Arepa", 9, 10));
            inventario.agregarProducto(new Producto(6, "Chorizo", 9, 10));
            inventario.agregarProducto(new Producto(7, "Salchipapa", 9, 10));
            inventario.agregarProducto(new Producto(8, "Gaseosa", 9, 10));
            
            inventario.insertarProductosEnBaseDeDatos(inventario, connection);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión a la base de datos.");
        }
                  
    }
    
}

