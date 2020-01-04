package examen.objetos;

import java.util.List;

public class Pedido {
    int id;
    int cliente;
    List<String> producto;
    String fecha;

    public Pedido(int id, int cliente, List<String> producto, String fecha) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public List<String> getProducto() {
        return producto;
    }

    public void setProducto(List<String> producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
