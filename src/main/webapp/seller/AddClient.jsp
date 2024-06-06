<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% if (request.getParameter("username") == null) {
    response.sendRedirect("../index.html");
    //kanonika testari an yparhei o seller pou sindeete
} %>

<html>  
    <head>
        <title>Add Client</title>
    </head>
    <body>
        <form action="/SellerServlet.java" method="post">
            <label for="sellerId">Seller ID:</label><br>
            <input type="text" id="sellerId" name="sellerId"><br>

            <label for="name">Name:</label><br>
            <input type="text" id="name" name="name"><br>

            <label for="surname">Surname:</label><br>
            <input type="text" id="surname" name="surname"><br>

            <label for="afm">AFM:</label><br>
            <input type="text" id="afm" name="afm"><br>

            <label for="phoneNumber">Phone Number:</label><br>
            <input type="text" id="phoneNumber" name="phoneNumber"><br>

            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username"><br>

            <label for="pasword">Pasword:</label><br>
            <input type="text" id="pasword" name="pasword"><br>

            <-- o tipos benei user pada--><br>

            <input type="submit" value="Submit">
        </form>
    </body>
</html>