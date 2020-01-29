package Gestor_SQL_MONGO.IngresoDatos;

import Gestor_SQL_MONGO.POJO.Alumno;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Ejemplo {

    public static List<Alumno> getEmployeeList(){

        Session session = null;
        session = Hibernate.getSession();
        Query query = session.createQuery("from Alumno where id > :id ");
        query.setParameter("id", 0);

        return query.list();
    }

    public static void main(String a[]) {

        List<Alumno> alumnos = getEmployeeList();
        System.out.println("emp size: "+alumnos.size());
        alumnos.stream().forEach(System.out::println);
    }
}
