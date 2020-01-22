package sqlmongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class MetodosLista {
    public static Alumno busqueda(int id, List<Alumno> alumnos) {
        Alumno retorno = null;
        for (Alumno i : alumnos) {
            if (i.getId() == id) {
                retorno = i;
            }
        }
        return retorno;
    }

    public static int extractPosicion(int id, List<Alumno> alumnos) {
        int result = -1;
        for (int i = 0; i != alumnos.size(); i++) {
            if (alumnos.get(i).getId() == id) {
                result = i;
            }
        }
        return result;
    }

    public static List<Alumno> busqueda(String apellido, List<Alumno> alumnos) {
        List<Alumno> retorno = null;
        for (Alumno i : alumnos) {
            if (i.getApellido().equals(apellido)) {
                retorno.add(i);
            }
        }
        return retorno;
    }

    public static boolean vacioNuevo(int id) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase db = mongoClient.getDatabase("alumnos");
        MongoCollection collection = db.getCollection("libres");
        DatosSql datosSql = null;
        DatosMongo datosMongo = null;
        datosMongo = new DatosMongo();
        Document document = new Document();
        document.put("id", id);
        return true;
    }
}
