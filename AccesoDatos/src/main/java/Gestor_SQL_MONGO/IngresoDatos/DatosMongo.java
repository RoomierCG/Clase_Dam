package Gestor_SQL_MONGO.IngresoDatos;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import Gestor_SQL_MONGO.POJO.Alumno;
import Gestor_SQL_MONGO.MetodosBaseDeDatos;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;

public class DatosMongo implements MetodosBaseDeDatos {

    //Con esta variable iteramos con la coleccion
    MongoCollection<Document> collection;
    MongoDatabase db;
    //Crea una variable root tipo logger que es la que inicializa los demas loggers, al ponerlo
    // al nivel OFF desactiva cualquier log que fuese a imprimir a la terminal.
    static Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    static { root.setLevel(Level.OFF); }

    @Override
    public List<Alumno> seleccionAlumnos() {

        /*
        Tenemos que crear un iterable que sera la coleccion en la que estamos, y el cursos sera la posicion dentro del iterable
         */
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> documentMongoCursor = findIterable.iterator();
        //Esta lista es el retorno.
        List<Alumno> arrAlumno = new ArrayList<Alumno>();

        try {
            while (documentMongoCursor.hasNext()) {
                Alumno alumno = new Alumno();
                Document nodoAlumno = documentMongoCursor.next();

                alumno.setId(nodoAlumno.getInteger("id"));
                alumno.setNombre(nodoAlumno.getString("Nombre"));
                alumno.setApellido(nodoAlumno.getString("Apellido"));
                alumno.setGrupo(nodoAlumno.getString("Grupo"));
                alumno.setFecha_nacimiento(nodoAlumno.getDate("fechaNacimiento"));

                arrAlumno.add(alumno);
            }
        } finally {
            documentMongoCursor.close();
        }
        return arrAlumno;
    }

    @Override
    public Alumno seleccionAlumnoId(int id) {
        /*
        Tenemos que crear un iterable que sera la coleccion en la que estamos, y el cursos sera la posicion dentro del iterable
         */
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> documentMongoCursor = findIterable.iterator();
        //Objeto
        Alumno alumno = new Alumno();

        try {

            while (documentMongoCursor.hasNext()) {

                Document nodoAlumno = documentMongoCursor.next();

                if (nodoAlumno.getInteger("id") == id) {
                    alumno.setId(nodoAlumno.getInteger("id"));
                    alumno.setNombre(nodoAlumno.getString("Nombre"));
                    alumno.setApellido(nodoAlumno.getString("Apellido"));
                    alumno.setGrupo(nodoAlumno.getString("Grupo"));
                    alumno.setFecha_nacimiento(nodoAlumno.getDate("fechaNacimiento"));
                }
            }
        } finally {
            documentMongoCursor.close();
        }

        return alumno;
    }

    @Override
    public List<Alumno> seleccionAlumnoApellido(String apellido) {
        /*
        Tenemos que crear un iterable que sera la coleccion en la que estamos, y el cursos sera la posicion dentro del iterable
         */
        Document doc = new Document("Apellido", Pattern.compile(apellido, Pattern.CASE_INSENSITIVE));
        FindIterable<Document> findIterable = collection.find(doc);
        MongoCursor<Document> documentMongoCursor = findIterable.iterator();
        //Objeto
        List<Alumno> arrAlumno = new ArrayList<Alumno>();

        try {

            while (documentMongoCursor.hasNext()) {
                Alumno alumno = new Alumno();

                Document nodoAlumno = documentMongoCursor.next();
                alumno.setId(nodoAlumno.getInteger("id"));
                alumno.setNombre(nodoAlumno.getString("Nombre"));
                alumno.setApellido(nodoAlumno.getString("Apellido"));
                alumno.setGrupo(nodoAlumno.getString("Grupo"));
                alumno.setFecha_nacimiento(nodoAlumno.getDate("fechaNacimiento"));

                arrAlumno.add(alumno);
            }
        } finally {
            documentMongoCursor.close();
        }

        return arrAlumno;

    }

    @Override
    public boolean crearAlumno(Alumno alumno) {

        try {
            Document document = new Document();

            document.put("id", alumno.getId());
            document.put("Nombre", alumno.getNombre());
            document.put("Apellido", alumno.getApellido());
            document.put("Grupo", alumno.getGrupo());
            document.put("fechaNacimiento", alumno.getFecha_nacimiento());

            collection.insertOne(document);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean modificarAlumno(Alumno alumno) {
        //Buscamos al alumno
        Document encontrarAlumnos = (Document) collection.find(new Document("id", alumno.getId())).first();
        if (encontrarAlumnos != null) {

            Document modificacionAlum = new Document();
            modificacionAlum.append("id", alumno.getId())
                    .append("Nombre", alumno.getNombre())
                    .append("Apellido", alumno.getApellido())
                    .append("Grupo", alumno.getGrupo())
                    .append("fechaNacimiento", alumno.getFecha_nacimiento());

            Document update = new Document();
            update.append("$set", modificacionAlum);

            collection.updateOne(encontrarAlumnos, update);
            return true;
        }

        return false;
    }

    @Override
    public boolean eliminarAlumno(int id) {
        try {
            collection.deleteOne(new Document("id",id));
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean insertarAlumnos(List<Alumno> alumnosEntrada) {

        //Creamos un objeto, para poder insertarlo.
        List<Document> arrDocument = new ArrayList<Document>();

        //recorremos la lista de entrada, y los introduciremos en la base de datos
        for (Alumno alumno : alumnosEntrada) {
            Document document = new Document();

            document.put("id", alumno.getId());
            document.put("Nombre", alumno.getNombre());
            document.put("Apellido", alumno.getApellido());
            document.put("Grupo", alumno.getGrupo());
            document.put("fechaNacimiento", alumno.getFecha_nacimiento());

            arrDocument.add(document);
        }

        collection.insertMany(arrDocument);

        return false;
    }

    @Override
    public boolean borrarAlumnos() {
        try {

            collection.deleteMany(Filters.gt("id",-1));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public DatosMongo() {
        //Establecemos una conexion con MongoDB
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        //Seleccionamos la base de datos
        db = mongoClient.getDatabase("alumnos");
        //Seleccionamos la coleccion
        collection = db.getCollection("alumnos");
        //Desactivamos el logger de mongo para deshabilitar los mensajes de informacion que sacaba al utilizarlo






    }

}
