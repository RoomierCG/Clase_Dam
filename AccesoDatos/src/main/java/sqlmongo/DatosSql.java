package sqlmongo;

import sqlmongo.readFiles.LecturaDOM;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatosSql implements MetodosBaseDeDatos {

    private static PreparedStatement preparedStatement;
    private static Connection connection;
    //Son variables globales para usar una query y conectar con la base de datos

    @Override
    public List<Alumno> seleccionAlumnos() {

        List<Alumno> alumnosSalida = new ArrayList<Alumno>();
        String query = "Select * from alumnos";

        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            //recorrera el puntero la tabla de SQL, retornara false cuando no encuentre mas datos
            while (resultSet.next()) {
                alumnosSalida.add(new Alumno(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5)));
            }
            return alumnosSalida;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Alumno seleccionAlumnoId(int id) {
        String query = "Select * from alumnos where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Alumno alumno = null;
            while (resultSet.next()) {
                alumno = new Alumno(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5)
                );
            }
            return alumno;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Alumno> seleccionAlumnoApellido(String apellido) {

        String query = "Select * from alumnos where apellido like ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + apellido + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Alumno> alumnoSalida = new ArrayList<>();

            while (resultSet.next()) {
                alumnoSalida.add(new Alumno(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5)
                ));
            }
            return alumnoSalida;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean crearAlumno(Alumno alumno) {

        String query = "INSERT INTO alumnos (`id`,`nombre`,`apellido`,`grupo`,`fechaNacimiento`)VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, alumno.getId());
            preparedStatement.setString(2, alumno.getNombre());
            preparedStatement.setString(3, alumno.getApellido());
            preparedStatement.setString(4, alumno.getGrupo());
            preparedStatement.setDate(5, (Date) alumno.getFecha_nacimiento());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean modificarAlumno(Alumno alumno) {
        String query = "UPDATE alumnos SET nombre=?,apellido=?,grupo=?,fechaNacimiento=? where id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, alumno.getNombre());
            preparedStatement.setString(2, alumno.getApellido());
            preparedStatement.setString(3, alumno.getGrupo());
            preparedStatement.setDate(4, (Date) alumno.getFecha_nacimiento());
            preparedStatement.setInt(5, alumno.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean eliminarAlumno(int id) {
        String query = "DELETE FROM ALUMNOS WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean insertarAlumnos(List<Alumno> alumnosEntrada) {
        String query = "INSERT INTO alumnos (`id`,`nombre`,`apellido`,`grupo`,`fechaNacimiento`)VALUES(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            for (Alumno alumno : alumnosEntrada) {
                preparedStatement.setInt(1, alumno.getId());
                preparedStatement.setString(2, alumno.getNombre());
                preparedStatement.setString(3, alumno.getApellido());
                preparedStatement.setString(4, alumno.getGrupo());
                preparedStatement.setDate(5, (Date) alumno.getFecha_nacimiento());

                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public DatosSql() {
        String urlBD = "jdbc:mysql://localhost:3306/alumnos?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(urlBD, "root", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
