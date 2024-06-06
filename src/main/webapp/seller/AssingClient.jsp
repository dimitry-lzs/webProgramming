<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% if (request.getParameter("username") == null) {
    response.sendRedirect("../index.html");
    //kanonika testari an yparhei o seller pou sindeete

} %>


<html>  
    <head>
        <title>Update Client</title>

    </head>
    <body>
        <form action="/path-to-your-server-endpoint" method="post">
            <label for="afm">AFM:</label><br>
            <select id="afm" name="choices">
                <option value="null">empty</option>
                <%for (int i = 0; i < 10; i++){
                    out.println("<option value=\"afm\">ta afm</option>");}%>
            </select><br>
            <label for="newPhoneNumber">New Phone Number:</label><br>
            <input type="text" id="newPhoneNumber" name="newPhoneNumber" placeholder="Enter new phone number"><br>
            <input type="submit" value="Update">
        </form>
    </body>
</html>