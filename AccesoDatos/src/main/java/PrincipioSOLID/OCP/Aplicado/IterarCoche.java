package PrincipioSOLID.OCP.Aplicado;

public class IterarCoche {

    public static void main(String[] args) {

        Audi audi = new Audi();
        Renault renault = new Renault();

        Coche[] arrayCoches = {audi,renault};
        imprimirPrecioCoche(arrayCoches);
    }

    public static void imprimirPrecioCoche(Coche[] arrayCoches) {

        for (Coche coche : arrayCoches) {
            System.out.println(coche.precioMedioCoche());
        }
    }

}
