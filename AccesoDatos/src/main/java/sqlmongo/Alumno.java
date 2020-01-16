package sqlmongo;

import java.util.Date;

public class Alumno {
    /*
        Este clase POJO, es usada en todas las clases de este paquete haciendola crucial para el programa, con esta
        clase usaremos desde crear y leer ficheros con alumnos con los datos que contiene abajo, hasta introducirlos
        en Base de Datos totalmente distintas con los mismo parametros en ambas.
     */
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

    public  Alumno(){
    }
   //todo Hacer el metodo toString, para reducir la carga de codigo en la clase Gestor
   public static String padRight(String s, int n) {
       return String.format("%-" + n + "s", s);
   }

   /*
    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
    */

    @Override
    public String toString() {
        return (
                "[ALUMNO]" +
                        " || ID: " + padRight(Integer.toString(this.id),4) +
                        " || Nombre: " + padRight(this.nombre,10) +
                        " || Apellido: " + padRight(this.apellido,20) +
                        " || Grupo: " + padRight(this.grupo,5) +
                        " || Fecha_Nacimiento: " + padRight(this.fecha_nacimiento.toString(),30) + " ||");
    }
}
