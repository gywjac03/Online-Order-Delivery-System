<%-- 
    Document   : updatestaff
    Created on : Nov 25, 2024, 10:36:52 PM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Staff Profile</title>
    </head>
    <body>
        <h1>Update Staff Profile</h1>      
        <form action="UpdateStaff" method="post">
            <input type="hidden" name="id" value="${staff.id}" readonly> <!-- Staff ID passed from search result -->
            <table>
                <tr>
                    <td>Staff ID: </td>
                    <td><input type="text" name="id" value="${staff.id}"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" name="email" value="${staff.email}"></td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td><input type="text" name="address" value="${staff.address}"></td>
                </tr>
                <tr>
                    <td>Phone Number: </td>
                    <td><input type="text" name="phonenumber" value="${staff.phonenumber}"></td>
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
        <a href="searchstaff.jsp">Back to Manage Staff</a>
    </body>
</html>
