package Gestor_SQL_MONGO.writeFiles;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class EscrituraXML {

    public static List<String> listAlumnos = new ArrayList<String>();
    public static List<String> listApellidos = new ArrayList<String>();

    public static void main(String[] args) {

        //Rellenamos las Listas con los ficheros de nombres y apellidos
        cargarListas();

        try {
            //Creamos el objeto al Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            /*
             * Step 1) Element root sera el que definira de que tipo sera el XML, en resumen el nodo raiz
             * Step 2) despues de crear el root, este tendra varios alumnos asi que lo crearemos con otro element pero
             *         este tendra sera hijo de root (mirar Step 1), no damos cuenta de eso en la linea
             *         root.appendChild(alumnoElement);
             * Step 3) Child Node Elements, ahora trabajaremos con los elementos que contendra la etiqueta en este caso
             *         alumno
             * */
            Element root = document.createElement("alumnos");
            document.appendChild(root);

            //Como tiene que haber 100 alumnos lo meteremos en un bucle
            for (int i = 0; i < 100; i++) {
                //Step 2)
                Element alumnoElement = document.createElement("alumno");
                root.appendChild(alumnoElement);

                //Step 3)
                //elemento id
                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(Integer.toString(i)));
                alumnoElement.appendChild(id);

                //elemento nombre
                Element nombre = document.createElement("nombre");
                nombre.appendChild(document.createTextNode(datosAlumno(listAlumnos)));
                alumnoElement.appendChild(nombre);

                //elemento nombre
                Element apellido = document.createElement("apellidos");
                apellido.appendChild(document.createTextNode((datosAlumno(listApellidos) + " " + datosAlumno(listApellidos))));
                alumnoElement.appendChild(apellido);

                //elemento grupo
                Element grupo = document.createElement("grupo");
                grupo.appendChild(document.createTextNode(grupoAleatorio()));
                alumnoElement.appendChild(grupo);

                //elemento fecha
                Element fecha = document.createElement("fechaNacimiento");
                fecha.appendChild(document.createTextNode(fechaAleatoria()));
                alumnoElement.appendChild(fecha);
            }

            //Despues de Crear todos creamos el XML y lo transformamos en un DOM objeto
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("D:\\Repositorios\\ClaseDam\\AccesoDatos\\src\\main\\resources\\ficherosGenerados\\alumnosXML.xml"));

            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String datosAlumno(List listEntrada) {
        int numeroAleatorio = (int) (Math.random() * listEntrada.size());
        return (String) listEntrada.get(numeroAleatorio);
    }

    public static List<String> leerFicheros(File fichero) {
        List<String> listaRetorno = new ArrayList<String>();

        try {

            int posicionPalabra;
            String palabra = "";
            FileReader ficheroEntrada = new FileReader(fichero);

            while ((posicionPalabra = ficheroEntrada.read()) != -1) {

                if (posicionPalabra == ',' || posicionPalabra == '.') {
                    listaRetorno.add(palabra);
                    palabra = "";
                } else if (posicionPalabra != ' ') {
                    palabra += (char) posicionPalabra;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        ;

        return listaRetorno;
    }

    public static void cargarListas() {
        File fileNombres = new File("D:\\Repositorios\\ClaseDam\\AccesoDatos\\src\\main\\resources\\archivosTxt\\nombres.txt");
        listAlumnos = leerFicheros(fileNombres);

        File fileApellidos = new File("D:\\Repositorios\\ClaseDam\\AccesoDatos\\src\\main\\resources\\archivosTxt\\apellidos.txt");
        listApellidos = leerFicheros(fileApellidos);
    }

    public static String grupoAleatorio() {
        String grupo[] = {"DAM", "ASIR", "DAW"};
        int numeroAleatorio = (int) (Math.random() * 3);
        return grupo[numeroAleatorio];
    }

    public static String fechaAleatoria() {

        String fecha = (
                Integer.toString((int) (Math.random() * 31 + 1)) + "/"
                        + Integer.toString((int) (Math.random() * 12 + 1)) + "/"
                        + Integer.toString((int) (Math.random() * (2001 - 1997) + 1997))
        );

        return fecha;
    }
}
