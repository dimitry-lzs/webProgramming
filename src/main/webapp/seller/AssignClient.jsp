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
        <form action="http://localhost:8080/vietnam/AssignProgramToClient" method="post">
            <!-- 
            <label for="afm">AFM:</label><br>
            <select id="afm" name="choices">
                <option value="null">empty</option>
              <%for (int i = 0; i < 10; i++){
                    out.println("<option value=\"afm\">ta afm</option>");}%>
           
            </select><br>
             --> 

             <label for="ClientAFM">Input Client AFM:</label><br>
             <input type="text" id="ClientAFM" name="ClientAFM" placeholder="Enter Client's AFM"><br>

            <label for="newProgram">Input Program ID:</label><br>
            <input type="text" id="newProgram" name="newProgram" placeholder="Enter Program ID"><br>

            <input type="submit" value="Submit">
        </form>
    </body>
</html>