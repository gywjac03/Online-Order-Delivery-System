<%-- 
    Document   : searchcustomer
    Created on : Nov 25, 2024, 1:18:16 PM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Customer</title>
        <style>
            .error { color: red; }
            .success { color: green; }
        </style>
    </head>
    <body>
        <h1>Manage Customer</h1>
        <a href="mStafflinks.jsp">Home</a>
        <!-- Search Form -->
        <form method="post" action="SearchCustomer">
            <label for="id">Enter Customer ID:</label>
            <input type="text" id="id" name="id" required>
            <button type="submit">Search</button>
        </form>

        <hr/>

        <!-- Display Customer Details after a Successful Search -->
        <c:if test="${not empty customer}">
            <h3>Customer Details</h3>
            <p>ID: ${customer.id}</p>
            <p>IC: ${customer.ic}</p>
            <p>Gender: ${customer.gender}</p>
            <p>Email: ${customer.email}</p>
            <p>Address: ${customer.address}</p>
            <p>Phone Number: ${customer.phonenumber}</p>
            <p>Roles: ${customer.roles}</p>
            
            <!-- Update Button -->
            <form method="post" action="UpdateCustomerProfile">
                <input type="hidden" name="id" value="${customer.id}">
                <button type="submit" style="background-color: blue; color: white;">Update Customer Profile</button>
            </form>

            <br>

            <!-- Delete Button -->
            <form method="post" action="DeleteCustomer">
                <input type="hidden" name="id" value="${customer.id}">
                <button type="submit" style="background-color: red; color: white;">Delete Customer</button>
            </form>
            <br>
        </c:if>

        <!-- Show success or error message after deleting -->
        <c:if test="${not empty deleteMessage}">
            <p style="color: green;">${deleteMessage}</p>
        </c:if>

        <!-- Show error message if customer is not found or search was invalid -->
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>

    </body>
</html>

