
package com.gmq.psp.soap.manageschool;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idAsignature" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DNI" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idAsignature",
    "dni"
})
@XmlRootElement(name = "AsignatureRequest")
public class AsignatureRequest {

    @XmlElement(required = true)
    protected String idAsignature;
    @XmlElement(name = "DNI")
    protected int dni;

    /**
     * Obtiene el valor de la propiedad idAsignature.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAsignature() {
        return idAsignature;
    }

    /**
     * Define el valor de la propiedad idAsignature.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAsignature(String value) {
        this.idAsignature = value;
    }

    /**
     * Obtiene el valor de la propiedad dni.
     * 
     */
    public int getDNI() {
        return dni;
    }

    /**
     * Define el valor de la propiedad dni.
     * 
     */
    public void setDNI(int value) {
        this.dni = value;
    }

}
