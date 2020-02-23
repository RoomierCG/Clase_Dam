
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
 *         &lt;element name="Asiganature" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Student" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Grade" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "asiganature",
    "student",
    "grade"
})
@XmlRootElement(name = "EvaluationRequest")
public class EvaluationRequest {

    @XmlElement(name = "Asiganature", required = true)
    protected String asiganature;
    @XmlElement(name = "Student", required = true)
    protected String student;
    @XmlElement(name = "Grade")
    protected int grade;

    /**
     * Obtiene el valor de la propiedad asiganature.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsiganature() {
        return asiganature;
    }

    /**
     * Define el valor de la propiedad asiganature.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsiganature(String value) {
        this.asiganature = value;
    }

    /**
     * Obtiene el valor de la propiedad student.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudent() {
        return student;
    }

    /**
     * Define el valor de la propiedad student.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudent(String value) {
        this.student = value;
    }

    /**
     * Obtiene el valor de la propiedad grade.
     * 
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Define el valor de la propiedad grade.
     * 
     */
    public void setGrade(int value) {
        this.grade = value;
    }

}
