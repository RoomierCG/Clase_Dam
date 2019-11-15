package Ticket_Edgar_Alexander_Calle_Granda;

public class Tickets_operation implements Ticket_Transport{

	private int quantity;
	//Atributos
	
	private int  max_quantity_plane = 150;
	private int  max_quantity_bus = 55;
	//Maxima cantidad de tickets de transporte
	
	public Tickets_operation(int quantity) {
		this.quantity = quantity;
	}
	
	

	@Override
	public int quantity_transport() {
		
		int resultado;
		//variable
		
		if (quantity >= max_quantity_plane) {
			System.out.println("No hay asientos libres\n");
			return 0;
		}else {
			
			quantity -= max_quantity_plane;
			resultado = max_quantity_plane;
			
			if (quantity >= resultado) {
				System.out.println("compra con exito todavia quedan " + max_quantity_plane);
			}
			if(max_quantity_plane < resultado) {
				System.out.println("No hay disponibles");
			}
		}
		return 1;
	}
}
