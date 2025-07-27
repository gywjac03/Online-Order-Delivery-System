<%-- 
    Document   : dStafflinks
    Created on : Nov 11, 2024, 12:20:16 AM
    Author     : ganye
--%>

<%@page import="model.OrderItems"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delivery Staff Page</title>
    </head>
    <body>
        <h1>Delivery Staff Home</h1>
        <a href="DStaffAssignedOrders">Home</a> | <a href="dStaffEditProfile.jsp">Edit Profile</a> |
        <a href="ViewAssignedOrders">View Assigned Orders</a> | 
        <a href="dsRatings">Feedback</a> | <a href="Logout">Logout</a> |

        <h2>Assigned Orders</h2>
        <%
            // Retrieve the orders list from the request attribute
            List<OrderItems> orders = (List<OrderItems>) request.getAttribute("orders");

            if (orders != null && !orders.isEmpty()) {
        %>
            <table border="1">
                <tr>
                    <th>Order ID</th>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Order Date</th>
                    <th>Shipping Address</th>
                    <th>Delivery Status</th>
                </tr>
                <% for (OrderItems order : orders) { %>
                    <tr>
                        <td><%= order.getId() %></td>
                        <td><%= order.getProduct().getProductName() %></td>
                        <td><%= order.getQuantity() %></td>
                        <td><%= order.getPrice() %></td>
                        <td><%= order.getOrderDate() %></td>
                        <td><%= order.getShippingAddress() %></td>
                        <td><%= order.getDeliveryStatus() %></td>
                    </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>No orders assigned to you yet.</p>
        <% } %>
    </body>
</html>

