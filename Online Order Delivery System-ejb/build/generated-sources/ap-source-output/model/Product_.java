package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.OrderItems;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-29T12:42:12")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> quantity;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile ListAttribute<Product, OrderItems> orders;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, String> category;
    public static volatile SingularAttribute<Product, String> productName;

}