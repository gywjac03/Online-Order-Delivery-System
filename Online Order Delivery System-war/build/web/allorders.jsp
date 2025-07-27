<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="model.OrderItems" %>
<%@ page import="java.util.List" %>
<%@ page import="model.UsersInfo" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Orders</title>
</head>
<body>
    <h1>All Orders</h1>

<c:if test="${not empty allOrders}">
    <table border="1" cellpadding="10">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>User ID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th>Order Date</th>
                <th>Shipping Address</th>
                <th>Payment Status</th>
                <th>Delivery Status</th>
                <th>Deliverer</th>

            </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${allOrders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.user.id}</td>
                <td>${order.product.productName}</td>
                <td>${order.quantity}</td>
                <td>${order.price}</td>
                <td>${order.quantity * order.price}</td>
                <td>${order.orderDate}</td>
                <td>${order.shippingAddress}</td>
                <td>${order.paymentStatus}</td>
                <td>${order.deliveryStatus}</td>
                <td>
                    <c:if test="${order.deliverer == 'Pending'}">
                        <form action="assignDeliverer" method="post">
                            <input type="hidden" name="orderId" value="${order.id}"/>
                            <select name="deliverer">
                                <c:forEach var="staff" items="${deliveryStaff}">
                                    <option value="${staff.id}">${staff.id}</option>
                                </c:forEach>
                            </select>
                            <button type="submit">Assign</button>
                        </form>
                    </c:if>
                    <c:if test="${order.deliverer != 'Pending'}">
                        ${order.deliverer}
                    </c:if>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty allOrders}">
    <p>No orders found.</p>
</c:if>

    <br>
    <a href="mStafflinks.jsp">Back to Home</a>
</body>
</html>
