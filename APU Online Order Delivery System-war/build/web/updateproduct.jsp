<%-- 
    Document   : updateproduct
    Created on : Nov 26, 2024, 1:45:11 AM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        <h1>Update Product Profile</h1>      
        <form action="UpdateProduct" method="post">
            <input type="hidden" name="id" value="${product.id}" readonly> <!-- Product ID -->
            <table>
                <tr>
                    <td>Product Name: </td>
                    <td><input type="text" name="productName" value="${product.productName}"></td>
                </tr>
                <tr>
                    <td>Price: </td>
                    <td><input type="text" name="price" value="${product.price}"></td>
                </tr>
                <tr>
                    <td>Quantity: </td>
                    <td><input type="number" name="quantity" value="${product.quantity}"></td>
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
                    <td><textarea name="description">${product.description}</textarea></td>
                </tr>
            </table>
            <p><input type="submit" value="Update Product"></p>
        </form>
        
        <%-- Display error or success messages --%>
        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
            <p style="color: green;"><%= message %></p>
            <%
                session.removeAttribute("message");
            }
        %>
        <%-- Error Message --%>
        <%
            String errorMessage5 = (String) request.getAttribute("errorMessage5");
            if (errorMessage5 != null) {
        %>
            <p style="color:red;"><%= errorMessage5 %></p>
        <%
            }
        %>
        <br>
        <a href="searchproduct.jsp">Back to Manage Products</a>
    </body>
</html>



