package sql;
import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;

public class EscribirSql {

    private static PreparedStatement preparedStatement;
    private static Connection connection;
    //Son variables locales para usar una query y conectar con la base de datos

    public static void main(String[] args) {

        File file = new File("C:\\Users\\ALUMNO.AULA4-PC5\\Desktop\\haba\\ficheros\\alumnos.xml");
        //leemos el fichero, para usar con los DOM.

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
            String nombre,apellido,grupo,fecha_nacimiento;

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                    nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                    apellido = eElement.getElementsByTagName("apellido").item(0).getTextContent();
                    grupo = eElement.getElementsByTagName("grupo").item(0).getTextContent();
                    fecha_nacimiento = eElement.getElementsByTagName("dob").item(0).getTextContent();

                    Alumno alumno = new Alumno(id,nombre,apellido,grupo, fecha_nacimiento);
                    arrayAlumnos.add(alumno);
                    //añadimos al final de leer todos los datos al arrayli.
                }

            }
            //para leerlo usamos un bucle y accedemos a cada elemento 1 a 1 por cada nodo que encuentra.

        } catch(Exception e) {
            e.printStackTrace();
        }
        //Leemos el fichero y lo metemos en el Array.
    }

    private static void insertaBD(){

        String urlBD = "jdbc:mysql://localhost:3306/acesodatos?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        //Esta sera la ruta a mi base de datos.

        try {
            //cargas en memoria el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Crear el objero de conxion (la ulr es la variable, el usuario y la contrase�a)
            Connection connection = DriverManager.getConnection(urlBD, "root","1234");

            //creamos un objeto statement
            Statement statement = connection.createStatement();

            //creamosla query
            String query = "INSERT INTO alumnos (`id`, `nombre`, `apellidos`, `grupo`, `fecha_nacimiento`) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            //crear el objeto para almacenar el resulatado
            ResultSet resultSet = statement.executeQuery(query);

            //Iterar sobre el resultado de la consulta
            while(resultSet.next()) {
                System.out.println("ALUMNO:"
                        +"ID: "+ resultSet.getInt(1)
                        +" Nombre: " + resultSet.getString(2)
                        +" Apellidos: " + resultSet.getString(3)
                        +" Grupo: " + resultSet.getString(4)
                        +" Fecha_Nacimiento: " + resultSet.getString(5)
                );
            }

            //Cerrar los objetos utilizados
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Date convertirFecha(String fecha){
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