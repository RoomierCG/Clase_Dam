import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Url {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com/");

            System.out.println("Tipo de protocolo "+url.getProtocol()+"\n Puerto "+url.getPort() +"\n UserInfo "+url.getUserInfo() +"\n Authority "+ url.getAuthority());

            // El primer objeto lee la Url que hemos introducido, despues tendremos que leerlo desde java
            InputStreamReader isReader = new InputStreamReader(url.openStream());
            BufferedReader bReader = new BufferedReader(isReader);
            String lineaURL;

            //Usaremos esto para escribirlo en un fichero
            FileWriter fWriter = new FileWriter("D:\\Repositorios\\ClaseDam\\PSP\\ServiciosRed\\src\\main\\resources\\fichero1.html");

            while ((lineaURL = bReader.readLine()) != null) {
                fWriter.write(lineaURL);
            }
            bReader.close();
            isReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
