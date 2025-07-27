<%-- 
    Document   : checkoutConfirmation
    Created on : Nov 28, 2024, 12:29:15 AM
    Author     : ganye
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.OrderItems" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Checkout Confirmation</title>
    </head>
    <body>
        <h1>Checkout Confirmation</h1>

        <%
            // Retrieve the single order from the request attribute
            OrderItems order = (OrderItems) request.getAttribute("order");
            if (order != null) {
        %>

        <p>Your order has been successfully placed! Here are the details:</p>

        <table border="1">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Order Date</th>
                    <th>Shipping Address</th>
                    <th>Payment Status</th>  <!-- Added column for payment status -->
                    <th>Delivery Status</th> <!-- Added column for delivery status -->
                    <th>Deliverer</th> <!-- Added column for deliverer -->
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= order.getProduct().getProductName() %></td>
                    <td>$<%= order.getPrice() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td>$<%= String.format("%.2f", order.getPrice() * order.getQuantity()) %></td>
                    <td><%= order.getOrderDate() %></td>
                    <td><%= order.getShippingAddress() %></td>
                    <td><%= order.getPaymentStatus() %></td>  <!-- Display payment status -->
                    <td><%= order.getDeliveryStatus() %></td>  <!-- Display delivery status -->
                    <td><%= order.getDeliverer() != null ? order.getDeliverer() : "Not Assigned" %></td> <!-- Display deliverer -->
                </tr>
            </tbody>
        </table>

        <p>Thank you for shopping with us!</p>

        <%
            } else {
        %>
            <p>No orders found.</p>
        <%
            }
        %>

        <a href="viewproducts">Continue Shopping</a> | <a href="customerlinks.jsp">Home</a>
    </body>
</html>
