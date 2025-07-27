<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.OrderItems" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.UsersInfo" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Orders</title>
</head>
<body>
    <h1>Your Orders</h1>

    <a href="customerlinks.jsp">Back to Home</a> <!-- Link to the home page or wherever appropriate -->

    <!-- Search form to search by order ID or product name -->
    <form method="GET" action="ViewOrders">
        <label for="searchQuery">Search by Order ID or Product Name: </label>
        <input type="text" id="searchQuery" name="searchQuery" placeholder="Enter Order ID or Product Name" />
        <button type="submit">Search</button>
    </form>

    <!-- Check if the 'orders' list is not empty -->
    <c:if test="${not empty orders}">
        <table border="1" cellpadding="10">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Order Date</th>
                    <th>Shipping Address</th>
                    <th>Payment Status</th>
                    <th>Delivery Status</th>
                    <th>Deliverer</th> <!-- New column for deliverer -->
                    <th>Action</th> <!-- New column for delete button -->
                    <th>Receipt</th> <!-- New column for generate receipt button -->
                </tr>
            </thead>
            <tbody>
                <!-- Iterate over the 'orders' list and display each order's details -->
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.product.productName}</td>
                        <td>${order.quantity}</td>
                        <td>${order.price}</td>
                        <td>${order.quantity * order.price}</td> <!-- Total price (quantity * price) -->
                        <td>${order.orderDate}</td>
                        <td>${order.shippingAddress}</td>
                        <td>${order.paymentStatus}</td>
                        <td>${order.deliveryStatus}</td>
                        <td>${order.deliverer != null ? order.deliverer : "Not Assigned"}</td> <!-- Display the deliverer, or "Not Assigned" if null -->

                        <!-- Show Delete button only if delivery status is 'Pending' and deliverer is not assigned -->
                        <c:if test="${order.deliveryStatus == 'Pending' && order.deliverer == 'Pending'}">
                            <td>
                                <form action="DeleteOrder" method="POST">
                                    <input type="hidden" name="orderId" value="${order.id}" />
                                    <button type="submit" onclick="return confirm('Are you sure you want to delete this order?');">Delete</button>
                                </form>
                            </td>
                        </c:if>

                        <!-- Show Generate Receipt button if the payment status is 'Done' and delivery status is 'Delivered' -->
                        <c:if test="${order.paymentStatus == 'Done' && order.deliveryStatus == 'Delivered'}">
                            <td>
                                <a href="CustomerReceipt?orderId=${order.id}">
                                    <button type="button">View Receipt</button>
                                </a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- If the 'orders' list is empty, display a message -->
    <c:if test="${empty orders}">
        <p>You have no orders yet.</p>
    </c:if>

</body>
</html>
