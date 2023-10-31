package modelView;

import java.sql.Connection;

public abstract class Abst {
    protected int id;
    protected String nombre;
    protected double precio;
    protected int cantidad;

    public Abst(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public abstract void agregarStock(int cantidad, Connection connection);
    public abstract void vender(int cantidad, Connection connection);

}
