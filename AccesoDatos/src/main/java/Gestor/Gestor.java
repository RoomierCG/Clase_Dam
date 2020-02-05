package Gestor;

import Gestor.IngresoDatos.DatosHibernate;
import Gestor.IngresoDatos.DatosMongo;
import Gestor.IngresoDatos.DatosSql;
import Gestor.POJO.Alumno;
import Gestor.readFiles.LecturaDOM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gestor {

    public static void main(String[] args) {
        /*
        Este Gestor puede añadir, borrar, consultar, etc... a varias base de datos en la que sera transparente al
        usuario las consultas desde esta clase, solo hace falta llamar a la clase menuOpciones ya que es la unica que
        hace llamada a los otros metodos y clases
         */
        menuOpciones();
    }

    private static void menuOpciones() {
        /*
            eleecion sera la variable donde seleccionaremos la BD para que despues en el gestor saber a que BD atacar,
            hay que tener en cuenta que si la BD no esta abirta, las cosultas no podran realizarse
         */
        int eleccion;
        System.out.println("=======================\n" +
                "1) MySQL\n" +
                "2) MongoDB\n" +
                "3) Hibernate\n");
        final int baseDeDatos = pedirDato(1,3);

        //Este do{}while() dejara de repetirse hasta que el usuario ingrese 8
        do {
            System.out.println("=======================\n" +
                    "1) Seleccionar todos los alumnos\n" +
                    "2) Seleccionar alumno por id\n" +
                    "3) Seleccionar alumnos por apellidos\n" +
                    "4) Crear un alumno\n" +
                    "5) Actualizar un alumno\n" +
                    "6) Eliminar un alumno\n" +
                    "7) Insertar todos los alumnos\n" +
                    "8) Eliminar todos los alumnos\n" +
                    "[9] SALIR");
            eleccion = pedirDato(1, 9);

            /*
                Esta parte de codigo es muy importante para entender el funcionamiento del gestor, llamamos a las clases
                necesarias que son las que contienen los metedos necesarios para interactuar en la base de datos.
                Estos empiezan en null ya que si los incializamos estos empezaran a crear la conexion a la Base de Datos
                mediante el constructor vacio (El que no tiene parametros, EJ: new DatosSql() ), por eso hay un
                condicional abajo que dependeindo de la eleccion anterior de la base de datos hara una conexion u otra
             */
            DatosSql datosSql = null;
            DatosMongo datosMongo = null;
            DatosHibernate datosHibernate = null;

            switch (baseDeDatos){
                case 1: datosSql = new DatosSql();break;
                case 2: datosMongo = new DatosMongo();break;
                case 3: datosHibernate = new DatosHibernate();break;
            }


            switch (eleccion) {
                case 1:
                    List<Alumno> alumnos = new ArrayList<>();
                    if (baseDeDatos == 1) {
                        alumnos = datosSql.seleccionAlumnos();
                    } else if (baseDeDatos == 2) {
                        alumnos = datosMongo.seleccionAlumnos();
                    } else if (baseDeDatos == 3){
                        alumnos = datosHibernate.seleccionAlumnos();
                    }

                    if (alumnos == null) {
                        System.out.println("ERROR: no se ha podido ejecutar la consultas");
                    } else {
                        for (Alumno alumno : alumnos) {
                            System.out.println(alumno.toString());
                        }
                    }
                    break;

                case 2:

                    Alumno alumno = null;
                    if (baseDeDatos == 1) {
                        alumno = datosSql.seleccionAlumnoId(pedirIdAlumno());
                    } else if (baseDeDatos == 2) {
                        alumno = datosMongo.seleccionAlumnoId(pedirIdAlumno());
                    }

                    if (alumno == null) {
                        System.out.println("ERROR: no se ha podido encontrar el alumo");
                    } else {
                        System.out.println(alumno.toString());
                    }
                    break;

                case 3:

                    List<Alumno> alumnoSalida = new ArrayList<>();
                    if (baseDeDatos == 1) {
                        alumnoSalida = datosSql.seleccionAlumnoApellido(pediApellido());
                    } else if (baseDeDatos == 2) {
                        alumnoSalida = datosMongo.seleccionAlumnoApellido(pediApellido());
                    } else if (baseDeDatos == 3){
                        alumnoSalida = datosHibernate.seleccionAlumnoApellido(pediApellido());
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

                    List<Alumno> listaTamaño = new ArrayList<>();

                    if (baseDeDatos == 1) {
                        listaTamaño = datosSql.seleccionAlumnos();
                    } else if (baseDeDatos == 2) {
                        listaTamaño = datosMongo.seleccionAlumnos();
                    }

                    alumno = new Alumno(listaTamaño.size(), pedirNombre(), pediApellido(), pedirGrupo(), pedirFecha());

                    if (baseDeDatos == 1) {
                        datosSql.crearAlumno(alumno);
                    } else if (baseDeDatos == 2) {
                        datosMongo.crearAlumno(alumno);
                    } else if (baseDeDatos == 2) {
                        datosHibernate.crearAlumno(alumno);
                    }
                    else {
                        System.out.println("ERROR: Algo inesperado, I'm a surgeon");
                    }
                    break;

                case 5:

                    listaTamaño = null;
                    if (baseDeDatos == 1) {
                        listaTamaño = datosSql.seleccionAlumnos();
                    } else if (baseDeDatos == 2) {
                        listaTamaño = datosMongo.seleccionAlumnos();
                    } else if (baseDeDatos == 3){
                        listaTamaño = datosHibernate.seleccionAlumnos();
                    }

                    int id;
                    do {
                        System.out.println("Ingrese el id del alumno");
                        id = pedirDato(0, listaTamaño.size() - 1);
                        if (baseDeDatos == 1) {
                            System.out.println("Es este alumno? " + datosSql.seleccionAlumnoId(id).getNombre() + " " + datosSql.seleccionAlumnoId(id).getApellido());
                        } else if (baseDeDatos == 2) {
                            System.out.println("Es este alumno? " + datosMongo.seleccionAlumnoId(id).getNombre() + " " + datosMongo.seleccionAlumnoId(id).getApellido());
                        }
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

                    if (baseDeDatos == 1) {
                        datosSql.modificarAlumno(alumno);
                        System.out.println("Se ha acutlizado Windows");

                    } else if (baseDeDatos == 2) {
                        datosMongo.modificarAlumno(alumno);
                        System.out.println("Se ha acutlizado Windows");

                    } else {
                        System.out.println("HOLD........");
                    }
                    break;

                case 6:

                    listaTamaño = null;
                    if (baseDeDatos == 1) {
                        listaTamaño = datosSql.seleccionAlumnos();
                    } else if (baseDeDatos == 2) {
                        listaTamaño = datosMongo.seleccionAlumnos();
                    }else if (baseDeDatos == 2){
                        listaTamaño = datosHibernate.seleccionAlumnos();
                    }

                    do {
                        System.out.println("Ingrese el id del alumno que desea ELIMINAR");
                        id = pedirDato(0, listaTamaño.size() - 1);
                        if (baseDeDatos == 1) {
                            System.out.println("Es este alumno? " + datosSql.seleccionAlumnoId(id).getNombre() + " " + datosSql.seleccionAlumnoId(id).getApellido());
                        } else if (baseDeDatos == 2) {
                            System.out.println("Es este alumno? " + datosMongo.seleccionAlumnoId(id).getNombre() + " " + datosMongo.seleccionAlumnoId(id).getApellido());
                        }
                        System.out.println("1) Si\n2)no");
                    } while (2 == pedirDato(1, 2));

                    if (baseDeDatos == 1) {
                        datosSql.eliminarAlumno(id);
                        System.out.println("Objetivo Eliminado correcto, it's me MARIO");
                    } else if (baseDeDatos == 2) {
                        datosMongo.eliminarAlumno(id);
                        System.out.println("Objetivo Eliminado correcto, it's me MARIO");
                    } else {
                        System.out.println("Ha escapaddo del Gulag");
                    }

                    break;

                case 7:

                    List<Alumno> alumnosEntrada = null;
                    Boolean control = true;
                    alumnosEntrada = LecturaDOM.creacionLits();

                    if (baseDeDatos == 1) {
                        control = datosSql.insertarAlumnos(alumnosEntrada);
                    } else if (baseDeDatos == 2) {
                        control = datosMongo.insertarAlumnos(alumnosEntrada);
                    }else if(baseDeDatos == 3){
                        control = datosHibernate.insertarAlumnos(alumnosEntrada);
                    }
                    if (!control) {
                        System.out.println("ERROR: no se han creado los alumnos");
                    } else {
                        System.out.println("Todos se ingreso correctamente");
                    }
                    break;
                case 8:
                    if(baseDeDatos == 1){
                        datosSql.borrarAlumnos();
                        System.out.println(" ** Se han borrado todos los alumnos con exito ** ");

                    }else if (baseDeDatos == 2){
                        datosMongo.borrarAlumnos();
                        System.out.println(" ** Se han borrado todos los alumnos con exito ** ");
                    } else if (baseDeDatos == 3) {
                        datosHibernate.borrarAlumnos();
                        System.out.println(" ** Se han borrado todos los alumnos con exito ** ");
                    }


                        break;

            }
        } while (eleccion != 9);
    }

    private static String pedirGrupo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige un grupo\n1) DAM\n2) DAW\n3) ASIR");
        int dato = pedirDato(1, 3);

        String[] grupo = {"DAM", "DAW", "ASIR"};

        return grupo[dato - 1];
    }

    /*
        Todos los metodos a partir de esta seccion x
     */
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
        return LecturaDOM.convertirFecha(dia + "/" + mes + "/" + año);

    }

    private static int pedirAlumno(int BD) {

        DatosSql datosSql = null;
        DatosMongo datosMongo = null;
        DatosHibernate datosHibernate = null;
        List<Alumno> listaTamaño = null;

        if (BD == 1) {
            datosSql = new DatosSql();
            listaTamaño = datosSql.seleccionAlumnos();
        } else if (BD == 2) {
            datosMongo = new DatosMongo();
            listaTamaño = datosMongo.seleccionAlumnos();
        }

        int id;
        do {
            System.out.println("Ingrese el id del alumno que desea ELIMINAR");
            id = pedirDato(0, listaTamaño.size() - 1);

            if (BD == 1) {
                System.out.println("Es este alumno? " + datosSql.seleccionAlumnoId(id).getNombre() + " " + datosSql.seleccionAlumnoId(id).getApellido());
            } else if (BD == 2) {
                System.out.println("Es este alumno? " + datosMongo.seleccionAlumnoId(id).getNombre() + " " + datosMongo.seleccionAlumnoId(id).getApellido());
            }
            System.out.println("1) Si\n2)no");
        } while (2 == pedirDato(1, 2));

        return id;
    }

    private static int seleccionarBD() {
        Scanner scanner = new Scanner(System.in);

        try {

            int baseDeDatos = scanner.nextInt();

            if (baseDeDatos == 1 || baseDeDatos == 2) {
                return baseDeDatos;
            } else {
                System.out.println("Warning: numero fuera de rango, prueba otra vez");
                seleccionarBD();
            }
        } catch (InputMismatchException num) {
            System.out.println("ERROR: dato no valido, intentalo otra vez");
            return seleccionarBD();
        }
        return -1;
    }
}
