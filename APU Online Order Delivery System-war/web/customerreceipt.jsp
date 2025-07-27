<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Receipt for Order #${order.id}</title>
</head>
<body>
    <h1>Receipt for Order #${order.id}</h1>
    <form>
        <table border="1" cellpadding="10">
            <tr>
                <td>Order ID</td>
                <td>${order.id}</td>
            </tr>
            <tr>
                <td>Customer</td>
                <td>${order.user != null ? order.user.id : 'N/A'}</td>
            </tr>
            <tr>
                <td>Product Name</td>
                <td>${order.product != null ? order.product.productName : 'N/A'}</td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td>${order.quantity}</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>${order.price}</td>
            </tr>
            <tr>
                <td>Total</td>
                <td><fmt:formatNumber value="${order.quantity * order.price}" /></td>
            </tr>
            <tr>
                <td>Order Date</td>
                <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            <tr>
                <td>Shipping Address</td>
                <td>${order.shippingAddress}</td>
            </tr>
            <tr>
                <td>Payment Status</td>
                <td>${order.paymentStatus}</td>
            </tr>
            <tr>
                <td>Delivery Status</td>
                <td>${order.deliveryStatus}</td>
            </tr>
            <tr>
                <td>Deliverer</td>
                <td>${order.deliverer != null ? order.deliverer : 'N/A'}</td>
            </tr>
            <tr>
                <td>Delivery Date</td>
                <td><fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
        </table>
    </form>
    <br>
    <a href="customerlinks.jsp">Back to Home</a>
</body>
</html>
