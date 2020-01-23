package Gestor_SQL_MONGO.IngresoDatos;

import Gestor_SQL_MONGO.POJO.Alumno;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Ejemplo {

    public static List<Alumno> getEmployeeList(){

        Session session = null;
        session = Hibernate.getSession();
        String queryStr = "select emp from Employee emp";
        Query query = session.createQuery(queryStr);

        return query.list();
    }

    public static void main(String a[]) {

        Alumno empDao = new Alumno();
        List<Alumno> empList = getEmployeeList();
        System.out.println("emp size: "+empList.size());
        empList.stream().forEach(System.out::println);
    }
}
