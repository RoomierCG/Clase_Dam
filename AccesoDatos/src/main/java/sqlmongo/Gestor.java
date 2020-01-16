package sqlmongo;

import sqlmongo.readFiles.LecturaDOM;

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
                "2) MongoDB\n");
        final int baseDeDatos = seleccionarBD();

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
                    "[8] SALIR");
            eleccion = pedirDato(1, 8);

            /*
                Esta parte de codigo es muy importante para entender el funcionamiento del gestor, llamamos a las clases
                necesarias que son las que contienen los metedos necesarios para interactuar en la base de datos.
                Estos empiezan en null ya que si los incializamos estos empezaran a crear la conexion a la Base de Datos
                mediante el constructor vacio (El que no tiene parametros, EJ: new DatosSql() ), por eso hay un
                condicional abajo que dependeindo de la eleccion anterior de la base de datos hara una conexion u otra
             */
            DatosSql datosSql = null;
            DatosMongo datosMongo = null;

            if (baseDeDatos == 1) {
                datosSql = new DatosSql();

            } else if (baseDeDatos == 2) {
                datosMongo = new DatosMongo();
            }

            //Inicializamos alumno para todos los casos del switch
            Alumno alumno = null;
            int id;

            switch (eleccion) {
                case 1:
                    List<Alumno> alumnos = new ArrayList<>();

                    if (baseDeDatos == 1) {
                        alumnos = datosSql.seleccionAlumnos();
                    } else if (baseDeDatos == 2) {
                        alumnos = datosMongo.seleccionAlumnos();
                    }

                    if (alumnos == null) {
                        System.out.println("ERROR: no se ha podido ejecutar la consultas");
                    } else {
                        for (Alumno alum : alumnos) {
                            System.out.println(alum.toString());
                        }
                    }
                    break;

                case 2:
                    if (baseDeDatos == 1) {
                        alumno = datosSql.seleccionAlumnoId(pedirIdAlumno());
                    } else if (baseDeDatos == 2) {
                        alumno = datosMongo.seleccionAlumnoId(pedirIdAlumno());
                    }

                    if (alumno == null) {
                        System.out.println("ERROR: no se ha podido crear el alumo");
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
                    }

                    if (alumnoSalida.isEmpty()) {
                        System.out.println("ERROR: no se ha podido encontrar ese alumno");
                    } else {
                        for (Alumno alum : alumnoSalida) {
                            System.out.println(alum.toString());
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
                    } else {
                        System.out.println("ERROR: Algo inesperado, I'm a surgeon");
                    }
                    break;

                case 5:

                    id = pedirAlumno(baseDeDatos);
                    Scanner scanner = new Scanner(System.in);

                    if (baseDeDatos == 1) {
                        alumno = datosSql.seleccionAlumnoId(id);
                    }else if(baseDeDatos == 2){
                        alumno = datosMongo.seleccionAlumnoId(id);
                    }

                    int opcion;
                    do {
                        System.out.println("Que quieres cambiar:\n1. Nombre\n2. Apellido\n3. Grupo\n4. FechaN\n5. He acabado");
                        opcion = pedirDato(1, 5);
                        switch (opcion) {
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
                    } while (opcion != 5);

                    if (baseDeDatos == 1) {
                        datosSql.modificarAlumno(alumno);
                        System.out.println("Se ha actualizado Windows");

                    } else if (baseDeDatos == 2) {
                        datosMongo.modificarAlumno(alumno);
                        System.out.println("Se ha actualizado Windows");

                    } else {
                        System.out.println("HOLD........");
                    }
                    break;

                case 6:
                    id = pedirAlumno(baseDeDatos);

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

                    if (baseDeDatos == 1) {
                        alumnosEntrada = LecturaDOM.creacionLits();
                        control = datosSql.insertarAlumnos(alumnosEntrada);
                    } else if (baseDeDatos == 2) {
                        alumnosEntrada = LecturaDOM.creacionLits();
                        control = datosMongo.insertarAlumnos(alumnosEntrada);
                    }
                    if (control) {
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
        return LecturaDOM.convertirFecha(dia + "/" + mes + "/" + año);

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

    private static int pedirAlumno(int BD){

        DatosSql datosSql = null;
        DatosMongo datosMongo = null;
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
}
