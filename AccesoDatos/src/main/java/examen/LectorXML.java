package examen;

import examen.objetos.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LectorXML {

    //creacion de objetos necesario para poder leer un fichero xml Globales
    static Document document = null;
    static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    static DocumentBuilder builder;

    //Variable donde se almacenara la raiz del archivo XML
    static Element root;

    //Lista de todos los hijos tipo Nodo y List
    static NodeList childsRoot;
    static List<String> itemChild;

    //Lista de archivos analizados
    static List<Cliente> arrCliente = new ArrayList<>();
    static List<Producto> arrProducto = new ArrayList<>();
    static List<Pedido> arrPedido = new ArrayList<>();

    private static void leerFichero() {
        //cargamos el fichero en el objeto document el cual sera el que leeremos
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File("D:\\Repositorios\\ClaseDam\\AccesoDatos\\src\\main\\resources\\ficherosGenerados\\bbdd.xml"));
            //cogemos el elemento raiz del documento
            root = document.getDocumentElement();
        } catch (Exception e) {
            System.out.println("Error al leer el fichero");
            e.printStackTrace();
        }
    }

    private static void nodosFicheros(){
        //Almacenamos en una lista todos los hijos que contenga la raiz
        childsRoot = root.getChildNodes();
        //Lista de nombres de tipos de datos
        itemChild = new ArrayList<String>();
        List<String> itemChildNumbers = new ArrayList<String>();
        /*
        * Navegamos entre los hijos, el primer bucle recorra sobre los hijos del fichero, el otro sobre la Lista donde
        * Almacenaremos todos los distintos tipos de nodos, compararemos si el nombre del hijo es igual al alguno de los
        * nodos ya incluidos en nuestra coleccion de nodos
        * */
        for (int i = 0; i <childsRoot.getLength() ; i++) {
            Boolean encontrado = false;
            for (int j = 0; j < itemChild.size(); j++) {
                if (childsRoot.item(i).getNodeName().equals(itemChild.get(j))){
                    encontrado = true;
                }
            }
            if (!encontrado && !(childsRoot.item(i).getNodeName().equals("#text"))){
                itemChild.add(childsRoot.item(i).getNodeName());
            }
        }

        for (int i = 0; i < itemChild.size(); i++) {
            int numeroItems = 0;
            for (int j = 0; j <childsRoot.getLength() ; j++) {
                if (itemChild.get(i).equals(childsRoot.item(j).getNodeName())){
                    numeroItems++;
                }
            }
            itemChildNumbers.add(itemChild.get(i)+": "+numeroItems);
        }

        for (int i = 0; i < itemChildNumbers.size(); i++) {
            System.out.println(itemChildNumbers.get(i));
        }
    }

    public static List buscadorNodos(String nodoEntrada){
        /*
            Primero leemos el fichero, despues encontramos los nodos
         */
        leerFichero();
        nodosFicheros();

        int posicionNodo =0;

        //Buscamos la posicion del NodoEntrada
        for (int i = 0; i <itemChild.size() ; i++) {
            if (itemChild.get(i).equals(nodoEntrada)){
                posicionNodo = i;
            }
        }
        /*
         *  Una vez sabemos la posicion de NodoEntrada, sacaremos los datos que contengan metiendolos en una lista
         *  Usaremos un variable temporal que contara el id de cada nodo Hijo
         */
        int identificador = 0;

        for (int i = 0; i <childsRoot.getLength() ; i++) {
            if (childsRoot.item(i).getNodeName().equals(itemChild.get(posicionNodo))){
                identificador++;
                //Con este nodo iremos iterando sobre el XML
                Node node = childsRoot.item(i);
                /*
                * Ahora que sabemos la etiqueta que recorres mediante un switch creamos los objetos que queremos y los meteremos en listas
                * dependiendo de su tipo que queremos
                * con Elemente cogeremos el contenido del nodo del archivo XML
                */
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;

                    switch (nodoEntrada){
                        case "cliente":
                            Cliente cliente = new Cliente(
                                    identificador,
                                    eElement.getElementsByTagName("nombre").item(0).getTextContent(),
                                    eElement.getElementsByTagName("apellidos").item(0).getTextContent(),
                                    eElement.getElementsByTagName("direccion").item(0).getTextContent()
                            );
                            arrCliente.add(cliente);
                            break;
                        case "producto":
                            Producto producto = new Producto(
                                    identificador,
                                    eElement.getElementsByTagName("nombre").item(0).getTextContent(),
                                    Float.parseFloat(eElement.getElementsByTagName("precio").item(0).getTextContent())
                            );
                            arrProducto.add(producto);
                            break;
                        case "pedido":
                            /*
                                Como pedido tiene una cantidad variable de producto, con este while meto en un lista
                                la cantidad de productos que contiene cada pedido
                             */
                            //TODO Coger lo datos de los productos
                            NodeList listaProductos = node.getChildNodes();
                            List<String> listProductos = new ArrayList<>();

                            for (int j = 0; j <listaProductos.getLength() ; j++) {
                                if (listaProductos.item(j).getNodeName().equals("producto")) {
                                    listProductos.add(listaProductos.item(j).getTextContent());
                                }
                            }
                            Pedido pedido = new Pedido(
                                    identificador,
                                    Integer.parseInt(eElement.getElementsByTagName("cliente").item(0).getTextContent()),
                                    listProductos,
                                    eElement.getElementsByTagName("fecha").item(0).getTextContent()
                            );
                            arrPedido.add(pedido);
                            break;
                        default:
                            System.out.println("No existe ese Nodo");
                            break;
                    }
                }
            }
        }

        //Devolvemos el Array
        switch (nodoEntrada){
            case "cliente":
                return arrCliente;
            case "producto":
                return arrProducto;
            case "pedido":
                return arrPedido;
        }

        return null;
    }

}
