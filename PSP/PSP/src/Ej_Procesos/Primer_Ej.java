package Ej_Procesos;

public class Primer_Ej implements Runnable{
	//variables
	String id;
	
	//constructor
	public Primer_Ej(String id) {
		this.id = id;
	}
	

	@Override
	public void run() {
		System.out.println("Hola, soy " + id);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("ERROR: Thread can't sleep :: \n" + e);
		}
		System.out.println("Adios, soy" + id);
		
	}
}
