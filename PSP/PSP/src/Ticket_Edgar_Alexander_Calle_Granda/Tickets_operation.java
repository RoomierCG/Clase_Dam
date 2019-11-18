package Ticket_Edgar_Alexander_Calle_Granda;

public class Tickets_operation implements Ticket_Transport{

	private int quantity;
	private String character;
	//Atributos
	
	private int  max_quantity_plane = 150;
	private int  max_quantity_bus = 55;
	private int max_quantity_car = 200;
	//Maxima cantidad de tickets de transporte
	
	public Tickets_operation(int quantity,String character) {
		this.quantity = quantity;
		this.character = character;
	}
	

	@Override
	public int quantity_transport() {
		
		switch (character) {
		case "a": 
			return managment_tickets(quantity,max_quantity_plane);
		case "b":
			return managment_tickets(quantity,max_quantity_bus);
		case "c":
			return managment_tickets(quantity,max_quantity_car);
		}
		
		return 1;
	}
	
	private int managment_tickets(int quantity,int Max_Quantity) {
		
		int aux_Max_Quantity = Max_Quantity;
		//Variable auxiliar para comparacion de datos
		
		Max_Quantity -= quantity;
		
		Math.abs(Max_Quantity);
		
		//hacemos el valor absoluto para evitar la entrada de negativos
		if (Max_Quantity < aux_Max_Quantity) {
			System.out.println("No quedan billetes" + Max_Quantity);
			return quantity;
		}
		if (Max_Quantity == 0) {
			System.out.println("Todo los billetes comprados");
			return quantity;
		}
		if (Max_Quantity <= aux_Max_Quantity) {
			System.out.println("Compra exitosa, quedan " + Max_Quantity);
			return quantity;
		}
		
		return 0;
	}
	
}
