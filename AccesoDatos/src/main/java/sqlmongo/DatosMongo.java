package sqlmongo;

import java.util.List;

public class DatosMongo implements MetodosBaseDeDatos{

    @Override
    public List<Alumno> seleccionAlumnos() {
        return null;
    }

    @Override
    public Alumno seleccionAlumnoId(int id) {
        return null;
    }

    @Override
    public List<Alumno> seleccionAlumnoApellido(String apellido) {
        return null;
    }

    @Override
    public boolean crearAlumno(Alumno alumno) {
        return false;
    }

    @Override
    public boolean modificarAlumno(Alumno alumno) {
        return false;
    }

    @Override
    public boolean eliminarAlumno(int id) {
        return false;
    }

    @Override
    public boolean insertarAlumnos(List<Alumno> alumnosEntrada) {
        return false;
    }

    //constructor de mongo
    public DatosMongo() {
        System.out.println("Soy mongo");
    }
}
