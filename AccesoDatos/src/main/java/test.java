import sqlmongo.Alumno;
import sqlmongo.readFiles.LecturaDOM;

import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Alumno> tester = null;
        for(int i = 0;i!=10;i++) {
            Alumno alumno = new Alumno(i, "pep", "guo", "Nope", LecturaDOM.convertirFecha("10/10/1999");
        }
        System.out.println(tester.get(i));
    }
}
