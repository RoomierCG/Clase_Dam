package Gestor.IngresoDatos;

import Gestor.MetodosBaseDeDatos;
import Gestor.POJO.Alumno;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatosHibernate implements MetodosBaseDeDatos {

    private Session session = null;
    private Transaction transaction = null;
    private Alumno alumno = null;

    @Override
    public List<Alumno> seleccionAlumnos() {
        List<Alumno> listaRetorno = null;
        Query query = session.createQuery("from Alumno");
        listaRetorno = query.list();

        return listaRetorno;
    }


    @Override
    public Alumno seleccionAlumnoId(int id) {
        Alumno alumno = session.get(Alumno.class,id);
        return alumno;
    }

    @Override
    public List<Alumno> seleccionAlumnoApellido(String apellido) {
        List<Alumno> listaRetorno = null;
        Query query = session.createQuery("from Alumno as a where a.apellido like :apellido");
        query.setParameter("apellido","%"+apellido+"%");

        listaRetorno = query.list();
        return listaRetorno;
    }

    @Override
    public boolean crearAlumno(Alumno alumno) {
        if (alumno != null){
            Alumno newAlumno = new Alumno(alumno.getId(), alumno.getNombre(), alumno.getApellido(), alumno.getGrupo(), alumno.getFecha_nacimiento());
            session.save(newAlumno);
            transaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean modificarAlumno(Alumno alumno) {
        if (alumno != null){
            Alumno newAlumno = new Alumno(alumno.getId(), alumno.getNombre(), alumno.getApellido(), alumno.getGrupo(), alumno.getFecha_nacimiento());
            session.update(newAlumno);
            transaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarAlumno(int id) {

        alumno = session.get(Alumno.class, id);
        session.delete(alumno);
        transaction.commit();

        return false;
    }

    @Override
    public boolean insertarAlumnos(List<Alumno> alumnosEntrada) {

        if (alumnosEntrada != null) {
            for (Alumno alumno : alumnosEntrada) {
                Alumno newAlumno = new Alumno(alumno.getId(), alumno.getNombre(), alumno.getApellido(), alumno.getGrupo(), alumno.getFecha_nacimiento());
                session.save(newAlumno);
            }
            transaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean borrarAlumnos() {
        alumno = session.get(Alumno.class, 1);
        Query query = session.createQuery("from Alumno");
        List<Alumno> iterable = query.list();

        for (Alumno alumno:iterable) {
            session.delete(alumno);
        }

        System.out.println("todo borrado correctamente");

        transaction.commit();
        return false;
    }

    public DatosHibernate() {
        session = Hibernate.getSession();
        transaction = session.beginTransaction();

    }
}
