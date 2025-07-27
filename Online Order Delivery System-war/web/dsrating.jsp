<%-- 
    Document   : dsrating
    Created on : Nov 29, 2024, 2:44:29 AM
    Author     : ganye
--%>

<%-- 
    Document   : allproducts
    Created on : Nov 29, 2024, 2:39:16 AM
    Author     : ganye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>All Ratings and Comments</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 8px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <h1>All Ratings and Comments</h1>
        
        <table>
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Rating</th>
                    <th>Comments</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="rating" items="${dsRatings}">
                    <tr>
                        <td>${rating.username}</td>
                        <td>${rating.rating} / 5</td>
                        <td>${rating.comments}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br>
        <a href="DStaffAssignedOrders">Back to Home</a>
    </body>
</html>

