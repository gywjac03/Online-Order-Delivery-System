<%-- 
    Document   : payment
    Created on : Nov 28, 2024, 1:47:20 PM
    Author     : ganye
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.OrderItems" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Payment Page</title>
    </head>
    <body>
        <h1>Payment Page</h1>

        <%
            // Retrieve the single order from the request attribute
            OrderItems order = (OrderItems) request.getAttribute("order");
            if (order != null) {
        %>

        <h3>Order Details:</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Order Date</th>
                    <th>Shipping Address</th>
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
                </tr>
            </tbody>
        </table>


        <h3>Select Payment Method</h3>
        <form action="ProcessPayment" method="POST">
            <input type="hidden" name="orderId" value="<%= order.getId() %>">
            <input type="radio" id="cash" name="paymentMethod" value="cash" checked>
            <label for="cash">Cash</label><br>
            
            <input type="radio" id="card" name="paymentMethod" value="card">
            <label for="card">Card</label><br><br>
            
            <!-- Show card details input if card option is selected -->
            <div id="cardDetails" style="display:none;">
                <h4>Enter Card Details:</h4>
                <label for="cardNumber">Card Number:</label><br>
                <input type="text" id="cardNumber" name="cardNumber" pattern="\d{16}" placeholder="Enter 16-digit card number"><br><br>
                
                <label for="cardName">Name on Card:</label><br>
                <input type="text" id="cardName" name="cardName" placeholder="Enter name on card"><br><br>
                
                <label for="cvv">CVV:</label><br>
                <input type="text" id="cvv" name="cvv" pattern="\d{3}" placeholder="3-digit CVV"><br><br>
            </div>

            <input type="submit" value="Pay">
        </form>

        <script>
            // Show/hide card details input based on selected payment method
            document.querySelectorAll('input[name="paymentMethod"]').forEach((elem) => {
                elem.addEventListener("change", function() {
                    if (this.value === "card") {
                        document.getElementById("cardDetails").style.display = "block";
                    } else {
                        document.getElementById("cardDetails").style.display = "none";
                    }
                });
            });
        </script>

        <%
            } else {
        %>
            <p>No order details found.</p>
        <%
            }
        %>
    </body>
</html>

