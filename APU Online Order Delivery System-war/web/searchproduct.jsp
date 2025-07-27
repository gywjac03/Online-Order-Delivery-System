<%-- 
    Document   : searchproduct
    Created on : Nov 26, 2024, 1:41:06 AM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, model.Product"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Products</title>
</head>
<body>
    <h1>Search Products</h1>
    <a href="mStafflinks.jsp">Home</a>
    <form action="SearchProduct" method="get">
        <label for="search">Search by Name:</label>
        <input type="text" name="query" id="search">
        <input type="submit" value="Search">

        <br><br>

        <label for="category">Search by Category:</label>
        <select name="category" id="category">
            <option value="">-- Select Category --</option>
            <option value="All">All Categories</option>
            <option value="Fresh Foods">Fresh Foods</option>
            <option value="Household Items">Household Items</option>
            <option value="Personal Care">Personal Care</option>
            <option value="Medical">Medical</option>
            <option value="Food and Beverages">Food and Beverages</option>
            <option value="Tech and Stationaries">Tech and Stationaries</option>
            <option value="Miscellaneous">Miscellaneous</option>
        </select>
        <input type="submit" value="Search by Category">
    </form>
    
    <br>
    
    <%-- Display delete message --%>
<%
    String deleteMessage = (String) request.getAttribute("deleteMessage");
    if (deleteMessage != null) {
%>
    <p style="color: green;"><%= deleteMessage %></p>
<%
    }
%>


    <br>

    <%-- Display search results --%>
    <%
        List<Product> results = (List<Product>) request.getAttribute("results");
        if (results != null && !results.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Category</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
    <%
            for (Product product : results) {
    %>
            <tr>
                <td><%= product.getId() %></td>
                <td><%= product.getProductName() %></td>
                <td><%= product.getPrice() %></td>
                <td><%= product.getQuantity() %></td>
                <td><%= product.getCategory() %></td>
                <td><%= product.getDescription() %></td>
                <td>
                    <form action="UpdateProduct" method="get" style="display:inline;">
                        <input type="hidden" name="id" value="<%= product.getId() %>">
                        <input type="submit" value="Update Product">
                    </form>
                    <form action="DeleteProduct" method="post">
                        <input type="hidden" name="id" value="<%= product.getId() %>">
                        <input type="submit" value="Delete Product">
                    </form>
                </td>
            </tr>
    <%
            }
        } else {
    %>
        <p style="color: red;">No products found.</p>
    <%
        }
    %>
        </table>
</body>
</html>





