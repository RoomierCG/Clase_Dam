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
            file.write(crearAlumno().toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray crearAlumno(){
        JSONArray alumnoArr = new JSONArray();

        for (int i = 0; i < 100; i++) {
            JSONObject alumno = new JSONObject();
            JSONObject itemAlumno = new JSONObject();
            itemAlumno.put("id",i);
            itemAlumno.put("nombre",datosAlumno(listAlumnos));
            itemAlumno.put("apellidos",datosAlumno(listApellidos));
            itemAlumno.put("grupo",grupoAleatorio());
            itemAlumno.put("fechaNacimiento",fechaAleatoria());
            alumno.put("alumno",itemAlumno);
            //añadimos el alumno al Array
            alumnoArr.add(alumno);
        }

        return  alumnoArr;
    }
}
