package Gestor.IngresoDatos;

import Gestor.POJO.Alumno;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/*
    Mejorar este fichero
    https://www.boraji.com/hibernate-5-remove-an-entity-example
 */
public class Hibernate {

    private static SessionFactory sessionFactory = null;

    static {
        try{
            loadSessionFactory();
        }catch(Exception e){
            System.err.println("Exception al iniciar Hibernate ");
            e.printStackTrace();
        }
    }

    public static void loadSessionFactory(){

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Alumno.class);
        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(srvcReg);
    }

    public static Session getSession() throws HibernateException {

        Session retSession=null;
        try {
            retSession = sessionFactory.openSession();
        }catch(Throwable t){
            System.err.println("Exception while getting session.. ");
            t.printStackTrace();
        }
        if(retSession == null) {
            System.err.println("session is discovered null");
        }

        return retSession;
    }
}