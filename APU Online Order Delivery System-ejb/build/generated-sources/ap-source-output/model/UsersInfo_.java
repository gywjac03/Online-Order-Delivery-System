package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.OrderItems;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-29T12:42:12")
@StaticMetamodel(UsersInfo.class)
public class UsersInfo_ { 

    public static volatile SingularAttribute<UsersInfo, String> password;
    public static volatile SingularAttribute<UsersInfo, String> address;
    public static volatile SingularAttribute<UsersInfo, String> gender;
    public static volatile SingularAttribute<UsersInfo, String> roles;
    public static volatile SingularAttribute<UsersInfo, String> phonenumber;
    public static volatile SingularAttribute<UsersInfo, String> ic;
    public static volatile ListAttribute<UsersInfo, OrderItems> orders;
    public static volatile SingularAttribute<UsersInfo, String> id;
    public static volatile SingularAttribute<UsersInfo, String> email;

}