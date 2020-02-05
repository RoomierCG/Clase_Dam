package Rmi;

import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {


    public static void main(String ... args) {
        BigDecimalStringConverter converterBigDecimal = new BigDecimalStringConverter();
        BigDecimalStringConverter converterString = new BigDecimalStringConverter();
        BigDecimal response = null;

        BigDecimal value1 = converterBigDecimal.fromString(args[0]);
        BigDecimal value2 = converterBigDecimal.fromString(args[1]);

        try {
            Registry registry = LocateRegistry.getRegistry();
            RMICalculator stub = (RMICalculator) registry.lookup("calculator");

            response = stub.sum(value1,value2);
            System.out.println("Sum: " + converterString.toString(response));

            response = stub.substract(value1,value2);
            System.out.println("Subt: " + converterString.toString(response));

            response = stub.multiply(value1,value2);
            System.out.println("Mult: " + converterString.toString(response));

            response = stub.divide(value1,value2);
            System.out.println("Div: " + converterString.toString(response));

        } catch (Exception e) {
            System.err.println("RMIClient exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
