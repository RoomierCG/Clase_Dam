package sqlmongo.writeFiles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import static sqlmongo.writeFiles.EscrituraXML.*;

public class EscrituraJson {



    public static void main(String[] args) {
        cargarListas();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("alumnos",crearAlumno());

        //creamos el fichero
        try  {
            FileWriter file = new FileWriter("D:\\Repositorios\\ClaseDam\\AccesoDatos\\src\\main\\resources\\ficherosGenerados\\alumnos.json");
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray crearAlumno(){

        JSONArray jsonArray= new JSONArray();

        for (int i = 0; i < 100; i++) {
            JSONObject alumno = new JSONObject();
            alumno.put("id",i);
            alumno.put("nombre",datosAlumno(listAlumnos));
            alumno.put("apellidos",datosAlumno(listApellidos));
            alumno.put("grupo",grupoAleatorio());
            alumno.put("fechaNacimiento",fechaAleatoria());

            //añadimos el alumno al Array
            jsonArray.add(alumno);
        }

        return  jsonArray;
    }
}
