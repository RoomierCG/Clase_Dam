package PrincipioSOLID.ISP.NoAplicado;

public class Pingüino implements Ave {
    @Override
    public void volar() {
        //No puede velor ERROR
    }

    @Override
    public void comer() {
        //Si puede comer
    }
}
