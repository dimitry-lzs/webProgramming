<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% if (request.getParameter("username") == null) {
    response.sendRedirect("../index.html");
    //kanonika testari an yparhei o seller pou sindeete

} %>




<html>
    <head>
        <title>Tables Clients</title>
    </head>
    <body>
        <table border="1">
            <tr><th>AFM</th><th>Phone Number</th><th>Seller ID</th></tr>
            <%for (int i = 0; i < 10; i++){
                out.println("<tr><td>times</td><td>apo tin</td><td>vasi</td></tr>");
            }
            
            Admin admin = new Admin("the man", "the myth", "the legend");
            out.println(admin.getUsername());
            
            %>
        </table>
    </body>
</html>