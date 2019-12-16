package examen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sqlmongo.Alumno;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.*;

public class LectorXML {

    //creacion de objetos necesario para poder leer un fichero xml
    static Document document = null;
    static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    static DocumentBuilder builder;


    public static void main(String[] args) {

        //Al iniciar que nos lea el fichero asignado
        leerFichero();
        //Comprobacion de status del contenido de los nodos y su contenido
        cogerCliente();
        cogerPedido();
        //Lanzamos la creacion de Listas
        crearLista();
    }

    public static void leerFichero() {
        //cargamos el fichero en el objeto document el cual sera el que leeremos
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File("C:\\Users\\ALUMNO.AULA4-PC5\\Desktop\\GIT\\Clase_Dam\\AccesoDatos\\src\\main\\resources\\ficherosGenerados\\bbdd.xml"));
        } catch (Exception e) {
            System.out.println("Error al leer el fichero");
            e.printStackTrace();
        }
    }

    //Recogemos el cliente, que este figurara en los items de pedidos
    public static void cogerCliente(){
        //Vemos cuantas etiquetas tiene la raiz
        NodeList nList = document.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            //creamos un objeto que contendra los tipos de nodos hijos de la raiz (En este caso se espean 2)
            Node childNode = nList.item(i);

            //TODO el hijo deberia de cogerse y lanzarse en una nueva lista
            //miramos si la etiqueta hija del nodo principal es la que queremos
            if (childNode.getNodeName().equals("cliente")) {
                //creamos una lista con los clientes que existan
                NodeList nListClientes = childNode.getChildNodes();
                System.out.println("Existen: " + nListClientes.getLength() + " Items distintos");
            }
        }
    }

    public static void cogerPedido(){
        //Cargamos el nodo pedido que queremos leer y comprobamos cuantos hay creados
        NodeList nList = document.getElementsByTagName("pedido");

        System.out.println("Existen: " + nList.getLength() + " Items distintos");

        //Creamos una lista por cada item que de cada pedido que exita
        //TODO crear para cada lista para casda objeto

        //recorremos el archivo buscando el los elemenentos hijo de la raiz especificada en nList
        for (int i = 0; i < nList.getLength(); i++){
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) node;

                //bucamos el contenido que hay en la etiqueta cliente
                String cliente = eElement.getElementsByTagName("cliente").item(0).getTextContent();

                //mientras encuentre etiquetas que coincidan con producto que nos vaya encontrado el contenido
                //creamos la variable autoinclemental para diferencia cada producto
                String producto = "";
                int contadorProducto = 1;
                while(eElement.getElementsByTagName("producto").equals("producto")){
                    eElement.getElementsByTagName("producto").item(0).getTextContent();
                }
                //bucamos el contenido que hay en la etiqueta fecha
                eElement.getElementsByTagName("fecha").item(0).getTextContent();

                //TODO aqui ira la lita autoIncreamental creada anteriormente en la que le pasaremos to items correspondiente a su id en el XML
                //List<String> listaTemporal = new ArrayList<>();
            }
        }
    }

    public static List crearLista(){
        //TODO recogemos los datos que queremos y creamos la lista, esta la lanzamos y creamos las listas
        return null;
    }
}
