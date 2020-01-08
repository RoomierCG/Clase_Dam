package sqlmongo;

import sqlmongo.readFiles.LecturaDOM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class Gestor {
    public static void main(String[] args) {
        //iniciamos el menu
        menuOpciones();
    }

    private static void menuOpciones() {
        int eleccion;

        System.out.println("=======================\n"+
                "1) MySQL\n"+
                "2) MongoDB\n");
        final int baseDeDatos = seleccionarBD();

        do {
            System.out.println("=======================\n" +
                    "1) Seleccionar todos los alumnos\n" +
                    "2) Seleccionar alumno por id\n" +
                    "3) Seleccionar alumnos por apellidos\n" +
                    "4) Crear un alumno\n" +
                    "5) Actualizar un alumno\n" +
                    "6) Eliminar un alumno\n" +
                    "7) Insertar todos los alumnos\n" +
                    "[8] SALIR");
            eleccion = pedirDato(1, 8);

            //creamos un objeto para tener acceso a las fuciones para las consultas
            DatosSql datosSql = new DatosSql();
            DatosMongo datosMongo = new DatosMongo();

            switch (eleccion) {
                case 1:

                    List<Alumno> alumnos = new ArrayList<>();
                    if (baseDeDatos == 1) {
                        alumnos = datosSql.seleccionAlumnos();
                    }else if (baseDeDatos == 2){
                        alumnos = datosMongo.seleccionAlumnos();
                    }

                    if (alumnos == null) {
                        System.out.println("ERROR: no se ha podido ejecutar la consultas");
                    } else {
                        for (Alumno alumno : alumnos) {
                            System.out.println(
                                    "Alumno numero: " + alumno.getId() + " " + alumno.getNombre() + " " + alumno.getApellido() + " " + alumno.getGrupo() + " " + alumno.getFecha_nacimiento()
                            );
                        }
                    }
                    break;

                case 2:

                    Alumno alumno = null;
                    if (baseDeDatos == 1) {
                        alumno  = datosSql.seleccionAlumnoId(pedirIdAlumno());
                    }else if (baseDeDatos == 2){
                        alumno  = datosMongo.seleccionAlumnoId(pedirIdAlumno());
                    }

                    if (alumno == null) {
                        System.out.println("ERROR: no se ha podido crear el alumo");
                    } else {
                        System.out.println(alumno.getId() + " " + alumno.getNombre() + " " + alumno.getApellido() + " " + alumno.getGrupo() + " " + alumno.getFecha_nacimiento());
                    }
                    break;
                    
                case 3:

                    List<Alumno> alumnoSalida = new ArrayList<>();
                    if (baseDeDatos == 1) {
                        alumnoSalida = datosSql.seleccionAlumnoApellido(pediApellido());
                    }else if (baseDeDatos == 2){
                        alumnoSalida = datosMongo.seleccionAlumnoApellido(pediApellido());
                    }

                    if (alumnoSalida.isEmpty()) {
                        System.out.println("ERROR: no se ha podido encontrar ese alumno");
                    } else {
                        for (Alumno alumnoIterante : alumnoSalida) {
                            System.out.println(
                                    alumnoIterante.getId() + " " +
                                            alumnoIterante.getNombre() + " " +
                                            alumnoIterante.getApellido() + " " +
                                            alumnoIterante.getGrupo() + " " +
                                            alumnoIterante.getFecha_nacimiento()
                            );
                        }
                    }
                    break;

                case 4:

                    List<Alumno> ListaTamaño = null;

                    if (baseDeDatos == 1) {
                        ListaTamaño = datosSql.seleccionAlumnos();
                    }else if (baseDeDatos == 2){
                        ListaTamaño = datosMongo.seleccionAlumnos();
                    }

                    alumno = new Alumno(ListaTamaño.size(), pedirNombre(), pediApellido(), pedirGrupo(), pedirFecha());

                    if (datosSql.crearAlumno(alumno)) {
                        System.out.println("Creado Exitosamente");
                    } else {
                        System.out.println("ERROR: LA CAGASTE");
                    }
                    break;

                case 5:

                    ListaTamaño = null;
                    if (baseDeDatos == 1) {
                        ListaTamaño = datosSql.seleccionAlumnos();
                    }else if (baseDeDatos == 2){
                        ListaTamaño = datosMongo.seleccionAlumnos();
                    }

                    int id = 0;
                    do {
                        System.out.println("Ingrese el id del alumno");
                        id = pedirDato(0, ListaTamaño.size() - 1);
                        System.out.println("Es este alumno? " + datosSql.seleccionAlumnoId(id).getNombre() + " " + datosSql.seleccionAlumnoId(id).getApellido());
                        System.out.println("1) Si\n2)no");
                    } while (2 == pedirDato(1, 2));

                    Scanner scanner = new Scanner(System.in);
                    alumno = datosSql.seleccionAlumnoId(id);

                    int pedida;
                    do {
                        System.out.println("Que quieres cambiar:\n1. Nombre\n2. Apellido\n3. Grupo\n4. FechaN\n5. He acabado");
                        pedida = pedirDato(1, 5);
                        switch (pedida) {
                            case 1:
                                System.out.println("Introduzca nombre");
                                alumno.setNombre(scanner.nextLine());
                                break;
                            case 2:
                                System.out.println("Introduzca apellido");
                                alumno.setApellido(scanner.nextLine());
                                break;
                            case 3:
                                System.out.println("Introduzca grupo");
                                alumno.setGrupo(pedirGrupo());
                                break;
                            case 4:
                                System.out.println("Introduzca fecha");
                                alumno.setFecha_nacimiento(pedirFecha());
                                break;
                        }
                    } while (pedida != 5);
                    if (datosSql.modificarAlumno(alumno)) {
                        System.out.println("Se ha acutlizado Windows");
                    } else {
                        System.out.println("HOLD........");
                    }
                    break;

                case 6:

                    ListaTamaño = null;
                    if (baseDeDatos == 1) {
                        ListaTamaño = datosSql.seleccionAlumnos();
                    }else if (baseDeDatos == 2){
                        ListaTamaño = datosMongo.seleccionAlumnos();
                    }

                    do {
                        System.out.println("Ingrese el id del alumno que desea ELIMINAR");
                        id = pedirDato(0, ListaTamaño.size() - 1);
                        System.out.println("Es este alumno? " + datosSql.seleccionAlumnoId(id).getNombre() + " " + datosSql.seleccionAlumnoId(id).getApellido());
                        System.out.println("1) Si\n2)no");
                    } while (2 == pedirDato(1, 2));
                    if (datosSql.eliminarAlumno(id)) {
                        System.out.println("Objetivo Eliminado correcto, it's me MARIO");
                    } else {
                        System.out.println("Ha escapaddo del Gulag");
                    }
                    break;

                case 7:

                    List<Alumno> alumnosEntrada = null;
                    if (baseDeDatos == 1) {
                        alumnosEntrada = LecturaDOM.creacionLits();
                    }else if (baseDeDatos == 2){
                        alumnosEntrada = LecturaDOM.creacionLits();
                    }

                    if (!datosSql.insertarAlumnos(alumnosEntrada) || !datosMongo.insertarAlumnos(alumnosEntrada)) {
                        System.out.println("ERROR: no se han creado los alumnos");
                    } else {
                        System.out.println("Todos se ingreso correctamente");
                    }
                    break;
            }
        } while (eleccion != 8);
    }

    private static String pedirGrupo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige un grupo\n1) DAM\n2) DAW\n3) ASIR");
        int dato = pedirDato(1, 3);

        String[] grupo = {"DAM", "DAW", "ASIR"};

        return grupo[dato - 1];
    }

    private static int pedirDato(int min, int max) {
        Scanner scanner = new Scanner(System.in);

        try {
            int retornoDato = scanner.nextInt();

            if (retornoDato < min || retornoDato > max) {
                System.out.println("Warning: Dato fuera de rango, intentalo otra vez");
                retornoDato = pedirDato(min, max);
            }
            return retornoDato;
        } catch (InputMismatchException num) {
            System.out.println("ERROR: dato no valido, intentalo otra vez");
            return pedirDato(min, max);
        }
    }

    private static int pedirIdAlumno() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el Id del Alumno");
        int id = scanner.nextInt();

        if (id >= 0 || id <= 100) {
            return id;
        } else {
            return pedirIdAlumno();
        }
    }

    private static String pedirNombre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del alumno");
        return scanner.nextLine();
    }

    private static String pediApellido() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el apellido del alumno");
        return scanner.nextLine();
    }

    private static Date pedirFecha() {
        Scanner scanner = new Scanner(System.in);
        int[] años31 = {1, 3, 5, 6, 7, 9, 11};

        System.out.println("Ingrese el año");
        int año = pedirDato(1998, 2000);
        System.out.println("Ingresa el mes");
        int mes = pedirDato(1, 12);
        int dia = 0;

        System.out.println("Ingresa el dia");
        if (mes == 2) {
            if (año % 4 == 0) {
                dia = pedirDato(1, 29);
            } else {
                dia = pedirDato(1, 28);
            }
        } else {
            for (int fecha : años31) {
                if (mes == fecha) {
                    dia = pedirDato(1, 31);
                }
            }
            if (dia == 0) {
                dia = pedirDato(1, 30);
            }
        }

        LecturaDOM.convertirFecha(dia + "/" + mes + "/" + año);
        return null;
    }

    private static int seleccionarBD(){
        Scanner scanner = new Scanner(System.in);

        try{

            int baseDeDatos = scanner.nextInt();

            if (baseDeDatos == 1 || baseDeDatos == 2){
                return baseDeDatos;
            }else{
                System.out.println("Warning: numero fuera de rango, prueba otra vez");
                seleccionarBD();
            }
        }catch (InputMismatchException num) {
            System.out.println("ERROR: dato no valido, intentalo otra vez");
            return seleccionarBD();
        }
        return -1;
    }
}
