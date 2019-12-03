package com.psp.bank.accountAplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.psp.bank.objects.Partner;

public class AccountApplication {

    private static ArrayList <Partner> partnerArrayList = new ArrayList <Partner>();
    private final static Contribution contribution = new Contribution();
    private static Partner partner;

    @SuppressWarnings("unchecked")
    public static void main(final String[] args) {
        int numberOfPartners = partnerNumControl(Integer.parseInt(args[0]));

            if (numberOfPartners != 1) {
            for (int i = 0; i < numberOfPartners; i++) {
                partner = new Partner(i);
                partnerArrayList.add(partner);
            }
        }
        /*
        for(Partner partner : partnerArrayList){
            System.out.println("numero : "+partner.getCarnetNumber()
                    +" dinero "+ partner.getCarnetCash());
        }
        */
        for (Partner partner: partnerArrayList) {
            Runnable sprint1 = () -> {System.out.println("Partner "+partner.getCarnetNumber() + " contributed with "+ contribution.firtsContribution() +", now the banckAccount is "+contribution.fourContribution());};
            Runnable sprint2 = () -> {System.out.println("Partner "+partner.getCarnetNumber() + " contributed with "+ contribution.secondContribution()+", now the banckAccount is "+contribution.fourContribution());};
            Runnable sprint3 = () -> {System.out.println("Partner "+partner.getCarnetNumber() + " contributed with "+ contribution.thirdContribution()+", now the banckAccount is "+contribution.fourContribution());};


            Set<Runnable> threads = new HashSet();
            threads.add(sprint1);
            threads.add(sprint2);
            threads.add(sprint3);

            threads.parallelStream().forEach(t -> t.run());

        }
    }

    private static int partnerNumControl(int number){
        if (number >= 10 &&  number <= 20) {
            System.out.println("funciona");
            return number;
        }else{
            System.out.println("Number of partners invalid");
            return 1;
        }
    }
}

/*
        Para compilar la esta clase nos dara ciertos problemas ya que llama a otros paquetes de nuestro proyecto
        Nos situamos en la carpeta raíz donde está el proyecto como por ejemplo, haciendo esto:
        *cd D:\GitHub\Clase_Dam\PSP\src\main\java
        *javac com\psp\bank\accountAplication\AccountApplication.java: Esto se separa en varias secciones:
            *javac: compila el .java
            *com\psp\bank\accountAplication\AccountApplication.java: [com\psp\bank\accountAplication]\[AccountApplication.java]
                                                                              Ruta del paquete          Clase que queremos compilar, terminada en java

        Línea para ejecutar la clase por consola (casa) ya compilada.
        java -cp D:\GitHub\Clase_Dam\PSP\src\main\java com.psp.bank.accountAplication.AccountApplication 10
        Explicación:
        *java: ejecutara la clase
        *-cp: ClassPath indicaremos donde esta nuestro proyecto, si este está en un paquete apuntaremos a la raíz
        *d:\GitHub\Clase_Dam\PSP\src\main\java: la ruta raíz donde está el proyecto java.
        *com.psp.bank.accountAplication.AccountApplication 10: esto se divide en 2 secciones, [com.psp.bank.accountAplication].[AccountApplication] [10]
                                                                                           paquete                      clase        parametros
        RESUMEN DE CÓDIGOS:
        *D:\GitHub\Clase_Dam\PSP\src\main\java> javac com\psp\bank\accountAplication\AccountApplication.java
        *D:\GitHub\Clase_Dam\PSP\src\main\java> java -cp d:\GitHub\Clase_Dam\PSP\src\main\java com.psp.bank.accountAplication.AccountApplication 10
*/