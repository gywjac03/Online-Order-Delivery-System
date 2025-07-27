<%-- 
    Document   : addproduct
    Created on : Nov 26, 2024, 12:46:48 AM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <h1>Add New Product</h1>
        <form action="AddProduct" method="post">
            <table>
                <tr>
                    <td>Product Name: </td>
                    <td><input type="text" name="productName" required></td>
                </tr>
                <tr>
                    <td>Price: </td>
                    <td><input type="number" name="price" step="0.01" required></td>
                </tr>
                <tr>
                    <td>Quantity: </td>
                    <td><input type="number" name="quantity" required></td>
                </tr>
                <tr>
                    <td>Category: </td>
                    <td>
                        <select name="category" required>
                            <option value="">Select Category</option>
                            <option value="Fresh Foods">Fresh Foods</option>
                            <option value="Household Items">Household Items</option>
                            <option value="Personal Care">Personal Care</option>
                            <option value="Medical">Medical</option>
                            <option value="Food and Beverages">Food and Beverages</option>
                            <option value="Tech and Stationaries">Tech and Stationaries</option>
                            <option value="Miscellaneous">Miscellaneous</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><textarea name="description" rows="4" cols="50" required></textarea></td>
                </tr>
            </table>
            <p><input type="submit" value="Add Product"></p>
        </form>

        <%-- Display success or error message if available --%>
        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
            <p style="color:green;"><%= message %></p>
        <%
                session.removeAttribute("message");
            }
        %>
        <br>
        <a href="mStafflinks.jsp">Back to Dashboard</a>
    </body>
</html>


