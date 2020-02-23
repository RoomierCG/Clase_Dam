
package com.gmq.psp.soap.manageschool;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.3.5
 * 2020-02-23T18:44:56.993+01:00
 * Generated source version: 3.3.5
 *
 */

public class ManageSchool_ManageSchoolSOAP_Server{

    protected ManageSchool_ManageSchoolSOAP_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new ManageSchoolSOAPImpl();
        String address = "http://localhost:8080/manageSchool/services";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws java.lang.Exception {
        new ManageSchool_ManageSchoolSOAP_Server();
        System.out.println("Server ready...");

        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}