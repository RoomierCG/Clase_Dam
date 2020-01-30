import Gestor.IngresoDatos.Hibernate;
import Gestor.POJO.Alumno;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class eliminarTodos {

    public static void main(String[] args) {
        // start a transaction
        Session session = Hibernate.getSession();
        Transaction transaction = session.beginTransaction();

        //Delete a persistent object
        Alumno customer1=session.get(Alumno.class, 3);
        if(customer1!=null){
            session.delete(customer1);
            System.out.println("Customer 1 is deleted");
            transaction.commit();
        }
    }
}
