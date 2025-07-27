<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assigned Orders</title>
</head>
<body>
    <h1>Assigned Orders</h1>

    <c:if test="${not empty orders}">
        <form method="post">
            <table border="1" cellpadding="10">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Customer</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Order Date</th>
                        <th>Shipping Address</th>
                        <th>Payment Status</th>
                        <th>Delivery Status</th>
                        <th>Delivery Date</th> <!-- Added column for Delivery Date -->
                        <th>Deliverer</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.user != null ? order.user.id : 'N/A'}</td>
                            <td>${order.product != null ? order.product.productName : 'N/A'}</td>
                            <td>${order.quantity}</td>
                            <td>${order.price}</td>
                            <td><fmt:formatNumber value="${order.quantity * order.price}" /></td>
                            <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>${order.shippingAddress}</td>
                            <td>${order.paymentStatus}</td>
                            <td>${order.deliveryStatus}</td>
                            <td>
                                <c:if test="${order.deliveryDate != null}">
                                    <fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </c:if>
                            </td>
                            <td>${order.deliverer != null ? order.deliverer : 'N/A'}</td>
                            <td>
                                <!-- Mark as Paid button -->
                                <c:if test="${order.paymentStatus == 'Pending'}">
                                    <button type="submit" name="paymentOrderId" value="${order.id}">Mark as Paid</button>
                                </c:if>

                                <!-- Mark as Delivered button -->
                                <c:if test="${order.deliveryStatus == 'Shipped'}">
                                    <button type="submit" name="deliveredOrderId" value="${order.id}">Mark as Delivered</button>
                                </c:if>

                                <!-- Generate Receipt button (visible after both statuses are updated) -->
                                <c:if test="${order.paymentStatus == 'Done' && order.deliveryStatus == 'Delivered'}">
                                    <a href="GenerateReceipt?orderId=${order.id}">Generate Receipt</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </c:if>

    <c:if test="${empty orders}">
        <p>No orders assigned to you.</p>
    </c:if>

    <br>
    <a href="DStaffAssignedOrders">Back to Home</a>
</body>
</html>
