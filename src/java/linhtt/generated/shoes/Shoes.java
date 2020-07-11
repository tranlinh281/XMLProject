
package linhtt.generated.shoes;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="linkProduct" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="imgSrc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "linkProduct",
    "gender",
    "brand",
    "name",
    "description",
    "price",
    "imgSrc"
})
@XmlRootElement(name = "Shoes", namespace = "https://linhtt.org/schema/shoes")
public class Shoes {

    @XmlElement(namespace = "https://linhtt.org/schema/shoes", required = true)
    protected String linkProduct;
    @XmlElement(namespace = "https://linhtt.org/schema/shoes", required = true)
    protected String gender;
    @XmlElement(namespace = "https://linhtt.org/schema/shoes", required = true)
    protected String brand;
    @XmlElement(namespace = "https://linhtt.org/schema/shoes", required = true)
    protected String name;
    @XmlElement(namespace = "https://linhtt.org/schema/shoes", required = true)
    protected String description;
    @XmlElement(namespace = "https://linhtt.org/schema/shoes", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger price;
    @XmlElement(namespace = "https://linhtt.org/schema/shoes", required = true)
    protected String imgSrc;

    public Shoes() {
    }

    public Shoes(String linkProduct, String gender, String brand, String name, String description, BigInteger price, String imgSrc) {
        this.linkProduct = linkProduct;
        this.gender = gender;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgSrc = imgSrc;
    }

    /**
     * Gets the value of the linkProduct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkProduct() {
        return linkProduct;
    }

    /**
     * Sets the value of the linkProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkProduct(String value) {
        this.linkProduct = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrand(String value) {
        this.brand = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPrice(BigInteger value) {
        this.price = value;
    }

    /**
     * Gets the value of the imgSrc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * Sets the value of the imgSrc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgSrc(String value) {
        this.imgSrc = value;
    }

}
