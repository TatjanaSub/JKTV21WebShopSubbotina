/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author pupil
 */
@Entity
public class Purchase implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = {CascadeType.MERGE})
    private Customer customer;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date takeOfProduct;
    @OneToOne (cascade = {CascadeType.MERGE})
    private Product product;
    private int amountCustomer;
    private int priceCustomer;

    public Purchase() {
    }

    public Purchase(Customer customer, Date takeOfProduct, Product product, int amountCustomer, int priceCustomer) {
        this.customer = customer;
        this.takeOfProduct = takeOfProduct;
        this.product = product;
        this.amountCustomer = amountCustomer;
        this.priceCustomer = priceCustomer;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getTakeOfProduct() {
        return takeOfProduct;
    }

    public void setTakeOfProduct(Date takeOfProduct) {
        this.takeOfProduct = takeOfProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmountCustomer() {
        return amountCustomer;
    }

    public void setAmountCustomer(int amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

    public int getPriceCustomer() {
        return priceCustomer;
    }

    public void setPriceCustomer(int priceCustomer) {
        this.priceCustomer = priceCustomer;
    }

    @Override
    public String toString() {
        return "Purchase{" 
                + "\n"
                +  customer 
                + "\n"
                + " takeOfProduct=" + takeOfProduct 
                + "," + product 
                + "\n"
                + "amountCustomer=" + amountCustomer 
                + ", priceCustomer=" + priceCustomer 
                + '}'
                + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.customer);
        hash = 83 * hash + Objects.hashCode(this.takeOfProduct);
        hash = 83 * hash + Objects.hashCode(this.product);
        hash = 83 * hash + this.amountCustomer;
        hash = 83 * hash + this.priceCustomer;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (this.amountCustomer != other.amountCustomer) {
            return false;
        }
        if (this.priceCustomer != other.priceCustomer) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.takeOfProduct, other.takeOfProduct)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    
    
}
