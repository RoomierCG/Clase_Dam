package sqlmongo;

import java.util.List;

public interface MetodosBaseDeDatos {
    /*
        Esta interfaz nos ayudara a mantener un orden a la hora de cambiar de Base de Datos, los metodos tienen nombres
        decriptibles que ayudan a entender el codigo.
     */
    List<Alumno> seleccionAlumnos();

    Alumno seleccionAlumnoId(int id);

    List<Alumno> seleccionAlumnoApellido(String apellido);

    boolean crearAlumno(Alumno alumno);

    boolean modificarAlumno(Alumno alumno);

    boolean eliminarAlumno(int id);

    boolean insertarAlumnos(List<Alumno> alumnosEntrada);

    boolean borrarAlumnos();
}
