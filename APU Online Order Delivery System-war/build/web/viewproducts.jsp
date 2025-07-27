<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>View Products</title>
        <style>
            /* Optional styling to limit the input width for better layout */
            input[type="number"] {
                width: 60px; /* Adjust the width as necessary */
            }
        </style>
    </head>
    <body>
        <h1>Product Catalog</h1>
        <a href="customerlinks.jsp">Home</a>
        <!-- Search Form -->
        <form action="viewproducts" method="get">
            <label for="searchName">Search by Name:</label>
            <input type="text" id="searchName" name="searchName" 
                   value="<%= request.getParameter("searchName") != null ? request.getParameter("searchName") : "" %>">

            <label for="searchCategory">Search by Category:</label>
            <select name="searchCategory" id="searchCategory">
                <option value="">-- Select Category --</option>
                <option value="All" <%= "All".equals(request.getParameter("searchCategory")) ? "selected" : "" %>>All Categories</option>
                <option value="Fresh Foods" <%= "Fresh Foods".equals(request.getParameter("searchCategory")) ? "selected" : "" %>>Fresh Foods</option>
                <option value="Household Items" <%= "Household Items".equals(request.getParameter("searchCategory")) ? "selected" : "" %>>Household Items</option>
                <option value="Personal Care" <%= "Personal Care".equals(request.getParameter("searchCategory")) ? "selected" : "" %>>Personal Care</option>
                <option value="Medical" <%= "Medical".equals(request.getParameter("searchCategory")) ? "selected" : "" %>>Medical</option>
                <option value="Food and Beverages" <%= "Food and Beverages".equals(request.getParameter("searchCategory")) ? "selected" : "" %>>Food and Beverages</option>
                <option value="Tech and Stationaries" <%= "Tech and Stationaries".equals(request.getParameter("searchCategory")) ? "selected" : "" %>>Tech and Stationaries</option>
                <option value="Miscellaneous" <%= "Miscellaneous".equals(request.getParameter("searchCategory")) ? "selected" : "" %>>Miscellaneous</option>
            </select>

            <input type="submit" value="Search">
        </form>

        <br>

        <!-- Display products table -->
        <table border="1">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Checkout</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        for (Product product : products) {
                %>
                            <tr>
                                <td><%= product.getProductName() %></td>
                                <td><%= product.getCategory() %></td>
                                <td><%= product.getPrice() %></td>
                                <td><%= product.getQuantity() %></td>
                                <td><%= product.getDescription() %></td>

                                <!-- Adjust Quantity and Checkout in a Single Form -->
                                <td colspan="2">
                                    <form action="Checkout" method="post">
                                        <input type="hidden" name="productId" value="<%= product.getId() %>">
                                        <input type="number" name="adjustedQuantity" value="1" min="1" max="<%= product.getQuantity() %>">
                                        <button type="submit">Checkout</button>
                                    </form>
                                </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="7">No products found.</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
