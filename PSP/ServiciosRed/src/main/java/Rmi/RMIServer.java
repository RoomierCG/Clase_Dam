package Rmi;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer implements RMICalculator {
    @Override
    public BigDecimal sum(BigDecimal firtSummand, BigDecimal secondSummand) throws RemoteException {
        BigDecimal total = firtSummand.add(secondSummand);
        return total;
    }

    @Override
    public BigDecimal substract(BigDecimal minuend, BigDecimal subtrahend) throws RemoteException {
        BigDecimal total = minuend.subtract(subtrahend);
        return total;
    }

    @Override
    public BigDecimal multiply(BigDecimal mutiplicand, BigDecimal multiplier) throws RemoteException {
        BigDecimal total = mutiplicand.multiply(multiplier);
        return total;
    }

    @Override
    public BigDecimal divide(BigDecimal dividend, BigDecimal divisor) throws RemoteException {
        BigDecimal total = dividend.divide(divisor);
        return total;
    }

    public static void main(String[] args) {
        try {

            RMIServer rmiServer = new RMIServer();
            RMICalculator rmiHelloWorld = (RMICalculator) UnicastRemoteObject.exportObject(rmiServer,0);

            // Bind the remote object's rm in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("calculator", rmiHelloWorld);

        } catch (Exception e) {
            System.err.println("RMIServer exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
