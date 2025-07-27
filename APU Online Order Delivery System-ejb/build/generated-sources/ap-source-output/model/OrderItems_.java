package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Product;
import model.UsersInfo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-29T12:42:12")
@StaticMetamodel(OrderItems.class)
public class OrderItems_ { 

    public static volatile SingularAttribute<OrderItems, Product> product;
    public static volatile SingularAttribute<OrderItems, Integer> quantity;
    public static volatile SingularAttribute<OrderItems, Double> price;
    public static volatile SingularAttribute<OrderItems, String> shippingAddress;
    public static volatile SingularAttribute<OrderItems, Long> id;
    public static volatile SingularAttribute<OrderItems, String> deliverer;
    public static volatile SingularAttribute<OrderItems, Date> deliveryDate;
    public static volatile SingularAttribute<OrderItems, Date> orderDate;
    public static volatile SingularAttribute<OrderItems, UsersInfo> user;
    public static volatile SingularAttribute<OrderItems, String> paymentStatus;
    public static volatile SingularAttribute<OrderItems, String> deliveryStatus;

}