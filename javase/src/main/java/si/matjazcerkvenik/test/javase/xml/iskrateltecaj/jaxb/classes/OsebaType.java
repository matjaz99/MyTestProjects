//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.04.07 at 06:01:05 PM CEST 
//


package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.jaxb.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OsebaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OsebaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="priimek" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mobitel" type="{http://iskratel.si/imenik}TelefonskaType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OsebaType", propOrder = {
    "ime",
    "priimek",
    "telefon",
    "mobitel"
})
public class OsebaType {

    @XmlElement(required = true)
    protected String ime;
    @XmlElement(required = true)
    protected String priimek;
    @XmlElement(required = true)
    protected String telefon;
    protected String mobitel;
    @XmlAttribute
    protected String id;

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the priimek property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriimek() {
        return priimek;
    }

    /**
     * Sets the value of the priimek property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriimek(String value) {
        this.priimek = value;
    }

    /**
     * Gets the value of the telefon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Sets the value of the telefon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefon(String value) {
        this.telefon = value;
    }

    /**
     * Gets the value of the mobitel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobitel() {
        return mobitel;
    }

    /**
     * Sets the value of the mobitel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobitel(String value) {
        this.mobitel = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
