package Ticket_Edgar_Alexander_Calle_Granda;

import java.util.Scanner;

public class Ticket_Seller {
	
	//creamos los objetos
	static Scanner scanner = new Scanner(System.in);
	static Ticket_Avion avion;
	static Ticket_Bus Bus;
	static Ticket_Coche Coche;
	
	String transport;
	
	public static void main(String[] args) {
		
		//Empieza el programa
		
		//pedimos valor al user
		
		switch (ask_transport()) {
		case "a":
			int quantity = ask_ticket();
			avion = new Ticket_Avion(quantity,"a");
			avion.quantity_transport();
			break;
		case "b":
			quantity = ask_ticket();
			Bus = new Ticket_Bus(quantity,"b");
			Bus.quantity_transport();
			break;
		case "c":
			quantity = ask_ticket();
			Coche = new Ticket_Coche(quantity,"c");
			Coche.quantity_transport();
			break;
		}
	}
	
	//metodos
	public static String ask_transport() {
		//
		String transport;
		
		System.out.println("=====================\n"
				+ "Que Transporte va a coger:\n"
				+ "A. Avion\n"
				+ "B. Autobus\n"
				+ "C. Coche\n"
				+ "=====================");
		transport = scanner.next();
		
		//controlamos la salida
		if (!transport.equals("a") && !transport.equals("b") && !transport.equals("c")) {
			System.out.println("Letra no valida intentalo de nuevo");
			main(null);
		}
		
		return transport;
	}
	
	public static int ask_ticket() {
		//
		int ticket = 0;
		
		System.out.println("Cuantos billetes va a comprar");
		
		try {
			ticket = scanner.nextInt();
			
			
		} catch (Exception e) {
			System.out.println("valor no valido");
			//vaciamos buffer para que no de problemas en el siguiente scanner
			scanner.nextLine();
			
			//volvemos al main
			ask_transport();
			
		}
		//controlamos la salida
		return ticket;
	}
}
