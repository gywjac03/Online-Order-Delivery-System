package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrderItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;

    // New field for the user's address
    @Column(name = "shipping_address")
    private String shippingAddress;

    // New field to store the associated user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersInfo user;

    // New fields for payment and delivery status
    @Column(name = "payment_status")
    private String paymentStatus;  // "Pending" or "Done"

    @Column(name = "delivery_status")
    private String deliveryStatus;  // "Pending" or "Shipped"

    // New field for deliverer
    @JoinColumn(name = "deliverer_id")
    private String deliverer;  // Assuming a Deliverer class exists

    // New field for the delivery date
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delivery_date")
    private Date deliveryDate;  // This will store the date when the order is delivered

    public OrderItems() {
        this.orderDate = new Date();
        this.paymentStatus = "Pending";  // Default payment status
        this.deliveryStatus = "Pending";  // Default delivery status
    }

    public OrderItems(Product product, int quantity, double price, String shippingAddress, UsersInfo user) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.shippingAddress = shippingAddress;
        this.orderDate = new Date();
        this.user = user;
        this.deliverer = "Pending";  // Set the deliverer
        this.paymentStatus = "Pending";  // Default payment status
        this.deliveryStatus = "Pending";  // Default delivery status
    }

    // Getters and setters for all fields, including deliveryDate
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public UsersInfo getUser() {
        return user;
    }

    public void setUser(UsersInfo user) {
        this.user = user;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof OrderItems)) {
            return false;
        }
        OrderItems other = (OrderItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Order[ id=" + id + " ]";
    }
}
