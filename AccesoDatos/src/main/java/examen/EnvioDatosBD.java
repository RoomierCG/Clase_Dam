package examen;

import examen.objetos.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static examen.LectorXML.buscadorNodos;

public class EnvioDatosBD {

    private static PreparedStatement preparedStatement;
    private static Connection connection;
    //Son variables globales para usar una query y conectar con la base de datos


    public static void main(String[] args) {
        conexionBD();

        /*
            Ahora haremos la insercion de datos a la BD por metodos para mayor organizacion.
            Los metodos comentados significan que ya estan integrados en la base de datos, por mejor flujo de trabajo los dejo comentados
         */
        //insertarCliente();
        //insertarProducto();
        //insertarPedido();

        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void insertarCliente() {

        //Almacenamos en una lista que nos retorna le metodo buscarNodo para poder meterlo en la base de datos mas facilmente
        List<Cliente> arrCliente = LectorXML.buscadorNodos("cliente");
        String query = "INSERT INTO `items`.`cliente` (`Nombre`,`Apellidos`,`Direccion`) VALUES (?,?,?)";

        //con prepared statement ingresaremos los datos leidos en la base de datos
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Cliente cliente : arrCliente) {
                preparedStatement.setString(2, cliente.getApellido());
                preparedStatement.setString(3, cliente.getDireccion());
                preparedStatement.setString(1, cliente.getNombre());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarProducto() {

        //Almacenamos en una lista que nos retorna le metodo buscarNodo para poder meterlo en la base de datos mas facilmente
        List<Producto> arrProducto = LectorXML.buscadorNodos("producto");
        String query = "INSERT INTO `items`.`producto` (`idProducto`,`Nombre`,`Precio`) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Producto producto : arrProducto) {

                preparedStatement.setInt(1, producto.getId());
                preparedStatement.setString(2, producto.getNombre());
                preparedStatement.setFloat(3, producto.getPrecio());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarPedido() {

        //Almacenamos en una lista que nos retorna le metodo buscarNodo para poder meterlo en la base de datos mas facilmente
        //Usamos 2 querys por que en una hacemos la tabla de pedido y la query2 lo mete en otra tabla donde se alamacenaran por
        // cada pedido cuantos productos se compran
        List<Pedido> arrPedido = LectorXML.buscadorNodos("pedido");
        String query = "INSERT INTO `items`.`pedido` (`idPedido`,`cliente`) VALUES (?,?)";
        String query2 = "INSERT INTO `items`.`pedidoProducto` (`pedidoCliente`,`producto`) VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);

            for (int i = 0; i < arrPedido.size(); i++) {
                preparedStatement.setInt(1, arrPedido.get(i).getId());
                preparedStatement.setInt(2, arrPedido.get(i).getCliente());

                //todo el bucle que inserte en la otra tabla todas las compras
                for (int j = 0; j < arrPedido.get(i).getProducto().size(); j++) {
                    preparedStatement2.setInt(1, arrPedido.get(i).getCliente());
                    preparedStatement2.setInt(2, Integer.parseInt(arrPedido.get(i).getProducto().get(j).toString()));

                    preparedStatement2.executeUpdate();
                }

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void conexionBD() {
        String urlBD = "jdbc:mysql://localhost:3306/alumnos?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            //preparamos la conexion y la creamos, dando por parametros ruta, usuario y contraseña
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(urlBD, "root", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
