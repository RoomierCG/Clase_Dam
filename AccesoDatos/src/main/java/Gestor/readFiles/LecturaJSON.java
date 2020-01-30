package Gestor.readFiles;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Gestor.POJO.Alumno;

public class LecturaJSON {


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("D:\\Repositorios\\ClaseDam\\AccesoDatos\\src\\main\\resources\\ficherosGenerados\\alumnos.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach( alumno -> parsealumnosObejeto( (JSONObject) alumno ) );

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    private static void parsealumnosObejeto(JSONObject employee)
    {
        //Get employee object within list
        JSONObject alumnosObejeto = (JSONObject) employee.get("alumno");

        Alumno alumno = new Alumno( Integer.parseInt(alumnosObejeto.get("id").toString()),
                                    alumnosObejeto.get("nombre").toString(),
                                    alumnosObejeto.get("apellidos").toString(),
                                    alumnosObejeto.get("grupo").toString(),
                                    convertirFecha(alumnosObejeto.get("fechaNacimiento").toString()));
    }

    private static java.sql.Date convertirFecha(String fecha){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = dateFormat.parse(fecha);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return new java.sql.Date(timestamp.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
