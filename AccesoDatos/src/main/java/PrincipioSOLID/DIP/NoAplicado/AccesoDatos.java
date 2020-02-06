package PrincipioSOLID.DIP.NoAplicado;

public class AccesoDatos {

    private DataService databaseService;

    public AccesoDatos(DataService databaseService) {
        this.databaseService = databaseService;
    }

    void getDatos() {
        databaseService.getDatos();
    }
}
