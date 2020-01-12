package sqlmongo;

import java.util.List;

public interface MetodosBaseDeDatos {

    List<Alumno> seleccionAlumnos();

    Alumno seleccionAlumnoId(int id);

    List<Alumno> seleccionAlumnoApellido(String apellido);

    boolean crearAlumno(Alumno alumno);

    boolean modificarAlumno(Alumno alumno);

    boolean eliminarAlumno(int id);

    boolean insertarAlumnos(List<Alumno> alumnosEntrada);
}
