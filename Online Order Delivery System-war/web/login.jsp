<%-- 
    Document   : login
    Created on : Nov 6, 2024, 4:46:40 PM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>     
        <form action="Login" method="POST">
            <table>
                <tr>
                    <td>User Login </td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="x" size="20"</td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="y" size="20"</td>
                </tr>
            </table>
            
            <p><input type="submit" value="Login"></p>
        </form> 
        <br>
        <a href="register.jsp">New User Registration</a>
    </body>
</html>
