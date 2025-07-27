<%-- 
    Document   : error
    Created on : Nov 28, 2024, 2:43:19 PM
    Author     : ganye
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8d7da;
                color: #721c24;
                margin: 50px;
            }
            .error-container {
                border: 1px solid #f5c6cb;
                background-color: #f8d7da;
                padding: 20px;
                border-radius: 5px;
                max-width: 600px;
                margin: 0 auto;
            }
            h1 {
                color: #721c24;
            }
            p {
                font-size: 18px;
            }
            a {
                text-decoration: none;
                color: #0056b3;
                font-weight: bold;
            }
            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="error-container">
            <h1>Oops! Something went wrong</h1>
            <p>We encountered an error while processing your request. Please try again or contact support if the problem persists.</p>

            <p>Here are some possible reasons:</p>
            <ul>
                <li>Insufficient balance for the transaction.</li>
                <li>Invalid card details (if paying by card).</li>
                <li>Stock availability issues (for the product you ordered).</li>
                <li>Unexpected server error.</li>
            </ul>

            <p>You can try the following actions:</p>
            <ul>
                <li><a href="viewproducts.jsp">Return to the product list</a></li>
                <li><a href="Checkout">Go back to the checkout page</a></li>
            </ul>
        </div>
    </body>
</html>

