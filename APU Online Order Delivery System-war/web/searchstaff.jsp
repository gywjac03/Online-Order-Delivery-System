<%-- 
    Document   : searchstaff
    Created on : Nov 25, 2024, 10:24:48 PM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Staff</title>
        <style>
            .error { color: red; }
            .success { color: green; }
        </style>
    </head>
    <body>
        <h1>Manage Staff</h1>
        <jsp:include page="managedstafflinks.jsp"/>
        <br><br>

        <!-- Search Form -->
        <form method="post" action="SearchStaff">
            <label for="id">Enter Staff ID:</label>
            <input type="text" id="id" name="id" required>
            <label for="role">Select Role:</label>
            <select id="role" name="role">
                <option value="delivery_staff">Delivery Staff</option>
                <option value="admin">Admin</option>
            </select>
            <button type="submit">Search</button>
        </form>

        <hr/>

        <!-- Display Staff Details after a Successful Search -->
        <c:if test="${not empty staff}">
            <h3>Staff Details</h3>
            <p>ID: ${staff.id}</p>
                        <p>IC: ${staff.ic}</p>
            <p>Gender: ${staff.gender}</p>
            <p>Email: ${staff.email}</p>
            <p>Address: ${staff.address}</p>
            <p>Phone Number: ${staff.phonenumber}</p>
            <p>Roles: ${staff.roles}</p>
            
            <!-- Update Button -->
            <form method="post" action="UpdateStaff">
                <input type="hidden" name="id" value="${staff.id}">
                <button type="submit" style="background-color: blue; color: white;">Update Staff Profile</button>
            </form>

            <br>

            <!-- Delete Button -->
            <form method="post" action="DeleteStaff">
                <input type="hidden" name="id" value="${staff.id}">
                <button type="submit" style="background-color: red; color: white;">Delete Staff</button>
            </form>
            <br>
        </c:if>

        <!-- Show success or error message after deleting -->
        <c:if test="${not empty deleteMessage}">
            <p class="success">${deleteMessage}</p>
        </c:if>

        <!-- Show error message if staff is not found or search was invalid -->
        <c:if test="${not empty errorMessage}">
            <p class="error">${errorMessage}</p>
        </c:if>

    </body>
</html>

