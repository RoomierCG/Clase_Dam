package Gestor.readFiles;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import Gestor.POJO.Alumno;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LecturaDOM {

    public static void main(String[] args) {
        creacionLits();
    }

    public static List creacionLits() {
        List<Alumno> alumnos = new ArrayList<>();

        //creacion de objetos necesario para poder leer un fichero xml
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        //cargamos el fichero en el objeto document el cual sera que leeremos
        try {
            builder = factory.newDocumentBuilder();
            String path = Paths.get("").toAbsolutePath().toString();
            System.out.println(path);
            document = builder.parse(new File(path+"\\src\\main\\resources\\ficherosGenerados\\alumnosXML.xml"));
        } catch (Exception e) {
            System.out.println("Error al leer el fichero");
            e.printStackTrace();
        }

        NodeList nList = document.getElementsByTagName("alumno");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                Alumno alumno = new Alumno(
                        Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()),
                        eElement.getElementsByTagName("nombre").item(0).getTextContent(),
                        eElement.getElementsByTagName("apellidos").item(0).getTextContent(),
                        eElement.getElementsByTagName("grupo").item(0).getTextContent(),
                        convertirFecha(eElement.getElementsByTagName("fechaNacimiento").item(0).getTextContent())
                );

                alumnos.add(alumno);
            }
        }
        return alumnos;
    }

    public static Date convertirFecha(String fecha) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            java.util.Date parsedDate = dateFormat.parse(fecha);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return new Date(timestamp.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

