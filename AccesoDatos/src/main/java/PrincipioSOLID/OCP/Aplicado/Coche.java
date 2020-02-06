package PrincipioSOLID.OCP.Aplicado;


abstract class Coche {
    abstract int precioMedioCoche();
    /*
    Un método abstracto para Java es un método que nunca va a ser ejecutado porque no tiene cuerpo. Simplemente, un
    método abstracto referencia a otros métodos de las subclases. ¿Qué utilidad tiene un método abstracto? Podemos ver
    un método abstracto como una palanca que fuerza dos cosas: la primera, que no se puedan crear objetos de una clase.
    La segunda, que todas las subclases sobreescriban el método declarado como abstracto.
     */
}
