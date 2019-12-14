package sqlmongo;

import java.util.Date;

public class Alumno {

    private int id;
    private String nombre;
    private String apellido;
    private String grupo;
    private Date fecha_nacimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Alumno(int id, String nombre, String apellido, String grupo, Date fecha_nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.grupo = grupo;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Alumno(){
    }
}
