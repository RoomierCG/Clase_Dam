import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AplicationCalculation {

    public static int number;
    private static Operators operator;

    public static void main(String[] args) {
        //number = Integer.parseInt(args[0]);
        number = 1;

        Runnable calculateSquare= () ->  {System.out.println("Reultado: "+ operator.square(number));};
        Runnable calculateCube= () ->  {System.out.println("Resultado: "+ operator.cube(number));};
        Runnable calculateSquareRoot= () ->  {System.out.println("Resultado: "+ operator.squareRoot(number));};
        Runnable calculateFactorial= () ->  {System.out.println("Resultado: "+ operator.factorial(number));};

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(calculateSquare);
        executor.submit(calculateCube);
        executor.submit(calculateSquareRoot);
        executor.submit(calculateFactorial);

        executor.shutdown();
    }
}
