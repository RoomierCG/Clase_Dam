package PrincipioSOLID.LSP.Aplicado;

public class IterableCoche {

    public static void main(String[] args) {
        Audi audi = new Audi();
        Renault renault = new Renault();
        Coche[] arrayCoches = {audi,renault};

        imprimirNumAsientos(arrayCoches);
    }

    public static void imprimirNumAsientos(Coche[] arrayCoches){
        for (Coche coche : arrayCoches) {
            System.out.println(coche.numPuertas());
        }
    }
}
