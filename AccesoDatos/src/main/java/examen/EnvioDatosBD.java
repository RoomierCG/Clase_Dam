package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnvioDatosBD {

    private static PreparedStatement preparedStatement;
    private static Connection connection;
    //Son variables globales para usar una query y conectar con la base de datos


    public static void main(String[] args) {

        //TODO coger lista

    }

    private static void conexionBD(){
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
