package examen;

import java.sql.Date;

//La finalidad de esta clase es contruir objetos para posteriormente insertarlos en la lista
public class Pedido {
    int cliente;
    String pedido;
    Date fecha;

    //Constructores
    public Pedido(int cliente, String pedido, Date fecha) {
        this.cliente = cliente;
        this.pedido = pedido;
        this.fecha = fecha;
    }

    public Pedido() {

    }

    //Getter ans setters
    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
