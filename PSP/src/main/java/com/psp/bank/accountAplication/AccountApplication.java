package com.psp.bank.accountAplication;

import java.util.ArrayList;
import com.psp.bank.objects.Partner;

public class AccountApplication {

    private static ArrayList <Partner> partnerArrayList = new ArrayList <Partner>();

    public static void main(String[] args) {

        String numberOfPartners = args[0];
        minParnet(Integer.parseInt(numberOfPartners));

        for (int i = 0; i < Integer.parseInt(numberOfPartners); i++) {
            Partner partner = new Partner(i);
            partnerArrayList.add(partner);
        }

        for(Partner partner : partnerArrayList){
            System.out.println("numero : "+partner.getCarnetNumber()
            +" dinero "+ partner.getCarnetCash());
        }
    }

    private static int minParnet(int numberOfPartners){

        return numberOfPartners;
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