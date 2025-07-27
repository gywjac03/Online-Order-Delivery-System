<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
    <body>
        <form action="Register" method="POST">
            <table>
                <tr>
                    <td>User Registration</td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="username" size="20" required></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password" size="20" required></td>
                </tr>
                <tr>
                    <td>IC: </td>
                    <td><input type="text" name="ic" size="20" required></td>
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
                    <td><input type="email" name="email" size="20" required></td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td><input type="text" name="address" size="100" required></td>
                </tr>
                <tr>
                    <td>Phone Number: </td>
                    <td><input type="text" name="phonenumber" size="20" required></td>
                </tr>
                
            </table>           
            <p><input type="submit" value="Register"></p>
        </form>
        <br>
        <a href="login.jsp">Login</a>
    </body>
</html>
