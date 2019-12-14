package sqlmongo.readFiles;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sqlmongo.Alumno;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LecturaJSON {
    static List<Alumno>alumnoArray =new ArrayList<>();
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        //JSON parser para leer el fichero
        JSONParser jsonParser = new JSONParser();


        try (FileReader reader = new FileReader("D:\\Repositorios\\ClaseDam\\AccesoDatos\\src\\main\\resources\\ficherosGenerados\\alumnos.json")){
            //Read JSON file parser
            Object obj = jsonParser.parse(reader);
            JSONObject alumnoObjetojson = (JSONObject)obj;

            int id = (int) alumnoObjetojson.get("id");
            System.out.println(id);

            String nombre = (String) alumnoObjetojson.get("nombre");
            System.out.println(nombre);

            String apellido = (String) alumnoObjetojson.get("apellido");
            System.out.println(apellido);

            String grupo = (String) alumnoObjetojson.get("grupo");
            System.out.println(grupo);

            Date fechaN = convertirFecha((String) alumnoObjetojson.get("fechaNacimiento"));
            System.out.println(fechaN);


        }
         catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private static java.sql.Date convertirFecha(String fecha){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = dateFormat.parse(fecha);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return new java.sql.Date(timestamp.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
