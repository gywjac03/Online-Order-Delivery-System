<%-- 
    Document   : updatecustomer
    Created on : Nov 25, 2024, 3:06:42 PM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customer Profile</title>
    </head>
    <body>
        <h1>Update Customer Profile</h1>      
        <form action="UpdateCustomerProfile" method="post">
            <input type="hidden" name="id" value="${customer.id}" readonly> <!-- Customer ID passed from search result -->
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" value="${customer.id}"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="email" value="${customer.email}"></td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td><input type="text" name="address" value="${customer.address}"></td>
                </tr>
                <tr>
                    <td>Phone Number: </td>
                    <td><input type="text" name="phonenumber" value="${customer.phonenumber}"></td>
                </tr>

            </table>
            <p><input type="submit" value="Update Profile"></p>
        </form>
        
        <%-- Display error message if it exists --%>
        <%
            String errorMessage5 = (String) request.getAttribute("errorMessage5");
            if (errorMessage5 != null) {
        %>
            <p style="color:red;"><%= errorMessage5 %></p>
        <%
            }
        %>

        <%-- Display success or no change message if it exists --%>
        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
            <p style="color: green;"><%= message %></p>
            <%
                session.removeAttribute("message");  // Remove message after displaying
            }
        %>

        <br>
        <a href="searchcustomer.jsp">Back to Manage Customer</a>
    </body>
</html>

