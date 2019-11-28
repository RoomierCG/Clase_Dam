package sql;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class EscribirSql {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\ALUMNO.AULA4-PC5\\Desktop\\haba\\ficheros\\alumnos.xml");
        //leemos el fichero, para usar con los DOM.
        Alumno alumno;
		/*llamamos al objeto alumno que usaremos para meter los alumnos dentro de un array y estos despues subirlos a la
		base de datos*/
        ArrayList<Alumno> arrayAlumnos = new ArrayList<Alumno>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            //la variable file que esta en el parse es la que anteriormente pusimos en File.

            doc.getDocumentElement().normalize();
            //accedemos a la raiz del fichero, y borramos los elementos que esten vacios para optimizar el xml


            NodeList nList = doc.getElementsByTagName("alumno");
            System.out.println("Número de alumnos: " + nList.getLength()+"\n");
            //con node list cargamos la tag o nodo que queremos almacenar para usarlo.
            //para comprobar que funciona, mostramos la cantidad de alumnos que hay en el fichero.

            int id;
            String nombre,apellido,grupo;
            Date fecha_nacimiento;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    id = Integer.parseInt(eElement.getElementsByTagName("id_alumno").item(0).getTextContent());
                    nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                    apellido = eElement.getElementsByTagName("apellidos").item(0).getTextContent();
                    grupo = eElement.getElementsByTagName("grupo").item(0).getTextContent();
                    //fecha_nacimiento = formato.parse(eElement.getElementsByTagName("fecha_nacimiento").item(0).getTextContent());

                    //arrayAlumnos.add(id,nombre,apellido,grupo,fecha_nacimiento);
                    //añadimos al final de leer todos los datos al arrayli.
                }

            }
            //para leerlo usamos un bucle y accedemos a cada elemento 1 a 1 por cada nodo que encuentra.

        } catch(Exception e) {
            e.printStackTrace();
        }



    }
}