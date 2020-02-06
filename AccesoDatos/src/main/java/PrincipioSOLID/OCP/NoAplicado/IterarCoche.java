package PrincipioSOLID.OCP.NoAplicado;

public class IterarCoche {

    public static void main(String[] args) {

        Coche Auidi = new Coche("Audi");
        Coche Renault = new Coche("Renault");

        Coche[] arrayCoches = {Auidi, Renault};
        imprimirPrecioCoche(arrayCoches);
    }

    public static void imprimirPrecioCoche(Coche[] arrayCoches) {

        for (Coche coche : arrayCoches) {
            if (coche.marca.equals("Audi")) System.out.println(18000);
            if (coche.marca.equals("Renault")) System.out.println(25000);
        }
    }
}
