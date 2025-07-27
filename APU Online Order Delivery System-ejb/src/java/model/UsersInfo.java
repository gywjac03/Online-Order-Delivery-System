/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ganye
 */
@Entity
public class UsersInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String password;
    private String ic;
    private String gender;
    private String email;
    private String address;
    private String phonenumber;
    private String roles;

    @OneToMany(mappedBy = "user") // This establishes the one-to-many relationship with OrderItems
    private List<OrderItems> orders;

    public UsersInfo() {
    }

    public UsersInfo(String id, String password, String ic, String gender, String email, String address, String phonenumber, String roles) {
        this.id = id;
        this.password = password;
        this.ic = ic;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
        this.roles = roles;
    }

    

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    

    // Getter and setter methods for your fields...

    public List<OrderItems> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItems> orders) {
        this.orders = orders;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getPhonenumber() {
        return phonenumber;
    }
    
    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }
    
    public String getRoles() {
        return roles;
    }
    
    public void setRoles(String roles){
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersInfo)) {
            return false;
        }
        UsersInfo other = (UsersInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CustomerInfo[ id=" + id + " ]";
    }
    
}
