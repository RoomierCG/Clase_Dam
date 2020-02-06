package PrincipioSOLID.LSP.NoAplicado;

public class IterableCoche {

    static Coche[] arrayCoches = {
            new Renault(),
            new Audi(),
    };

    public static void main(String[] args) {
        imprimirNumAsientos(arrayCoches);
    }

    public static void imprimirNumAsientos(Coche[] arrayCoches) {
        for (Coche coche : arrayCoches) {
            if (coche instanceof Renault)
                System.out.println(numAsientosRenault(coche));
            if (coche instanceof Audi)
                System.out.println(numAsientosAudi(coche));
        }
    }

    private static boolean numAsientosAudi(Coche coche) {
        coche.setPuerta(4);
        return true;
    }

    private static boolean numAsientosRenault(Coche coche) {
        coche.setPuerta(5);
        return true;
    }
}
