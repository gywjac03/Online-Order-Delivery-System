<%-- 
    Document   : dStaffEditProfile
    Created on : Nov 26, 2024, 12:14:02 AM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Page - Delivery Staff</title>
    </head>
    <body>
        <h1>Edit Profile</h1>      
        <form action="DStaffEditProfile" method="post">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" value="${user.id}" readonly></td>
                </tr>
                <tr>
                    <td>Old Password: </td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>New Password: </td>
                    <td><input type="password" name="newPassword"></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="email" value="${user.email}"></td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td><input type="text" name="address" value="${user.address}"></td>
                </tr>
                <tr>
                    <td>Phone Number: </td>
                    <td><input type="text" name="phonenumber" value="${user.phonenumber}"></td>
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

        <%-- Display success message if it exists --%>
        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
            <p style="color:green;"><%= message %></p>
        <%
                session.removeAttribute("message");  // Remove success message after displaying
            }
        %>
        <br>
        <a href="DStaffAssignedOrders">Back to Dashboard</a>
    </body>
</html>

