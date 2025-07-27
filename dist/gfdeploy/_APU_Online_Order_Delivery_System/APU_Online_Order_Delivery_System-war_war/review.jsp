<%-- 
    Document   : review
    Created on : Nov 29, 2024, 2:16:55 AM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submit Review</title>
    </head>
    <body>
        <h2>Submit Your Review</h2>
        <form action="SubmitReview" method="POST">
            <table>
                <tr>
                    <td>Rating:</td>
                    <td>
                        <select name="rating" required>
                            <option value="1">1 Star</option>
                            <option value="2">2 Stars</option>
                            <option value="3">3 Stars</option>
                            <option value="4">4 Stars</option>
                            <option value="5">5 Stars</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Comment:</td>
                    <td><textarea name="comments" rows="4" cols="50" required></textarea></td>
                </tr>
            </table>
            <br>
            <p><input type="submit" value="Submit Review"></p>
        </form>
        <br>
        <a href="customerlinks.jsp">Back to Home</a>
    </body>
</html>

