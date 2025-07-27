<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Staff</title>
</head>
<body>
    <h1>Add Staff</h1>
    <form action="AddStaff" method="post">
        <jsp:include page="managedstafflinks.jsp"/>
        <br>
        <table>
            <br>
            <tr>
                <td>ID: </td>
                <td><input type="text" name="id" required></td>
            </tr>
            <tr>
                <td>Password: </td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td>IC: </td>
                <td><input type="text" name="ic" required></td>
            </tr>
            <tr>
                <td>Gender: </td>
                <td>
                    <select name="gender" required>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Email: </td>
                <td><input type="email" name="email" required></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><input type="text" name="address" required></td>
            </tr>
            <tr>
                <td>Phone Number: </td>
                <td><input type="text" name="phonenumber" required></td>
            </tr>
            <tr>
                <td>Role: </td>
                <td>
                    <select name="role" required>
                        <option value="admin">Admin</option>
                        <option value="delivery_staff">Delivery Staff</option>
                    </select>
                </td>
            </tr>

        </table>
        <p><input type="submit" value="Add Staff"></p>
    </form>

    <br><br>
    <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p style="color:red;"><%= errorMessage %></p>
    <% 
        }
    %>

    <br>
    <a href="mStafflinks.jsp">Back to Admin Dashboard</a>
</body>
</html>
