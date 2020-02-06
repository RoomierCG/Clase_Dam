package PrincipioSOLID.DIP.Aplicado;

public class AccesoDatos {
    private Conexion conexion;

    public AccesoDatos(Conexion conexion){
        this.conexion = conexion;
    }

    void getDatos(){
        conexion.getDatos();
    }
}
