//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.11 at 10:57:16 PM CEST 
//


package fr.neosoft.cvtheque.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for categorieType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="categorieType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="titreCategorie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="libelleCategorie" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categorieType", propOrder = {
    "titreCategorie",
    "libelleCategorie"
})
public class CategorieType {

    @XmlElement(required = true, nillable = true)
    protected String titreCategorie;
    @XmlElement(required = true)
    protected List<String> libelleCategorie;

    /**
     * Gets the value of the titreCategorie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitreCategorie() {
        return titreCategorie;
    }

    /**
     * Sets the value of the titreCategorie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitreCategorie(String value) {
        this.titreCategorie = value;
    }

    /**
     * Gets the value of the libelleCategorie property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the libelleCategorie property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLibelleCategorie().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLibelleCategorie() {
        if (libelleCategorie == null) {
            libelleCategorie = new ArrayList<String>();
        }
        return this.libelleCategorie;
    }

}
