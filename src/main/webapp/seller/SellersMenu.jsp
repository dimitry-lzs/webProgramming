<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% if (request.getAttribute("username") == null) {
    response.sendRedirect("../index.html");
    //kanonika testari an yparhei o seller pou sindeete

} %>

<%
String username = (String) request.getAttribute("username");
String table = (String) request.getAttribute("table");
%>
<html>

<head>
<title>Seller's Menu</title>
<link rel="stylesheet" href="./style.css"/>
</head>

<script>

    function login() {
        window.location.href = "/vietnam/";
    }
    function logout() {
        sessionStorage.clear();
        window.location.href = "/vietnam/";
        //edw prepei i kapou allou na na ton kanei na min boresei na girisei
    }

</script>

<body>
    <section> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
        <span></span>
        <div class="signin">
            <div class="content">
                <h2>Hello <% out.println(username); %></h2>
                <div>
                <button class="button" onclick="location.href='seller/AddClient.jsp?username=<%=username%>';">
                    Add Client
                </button>
                <button class="button" onclick="location.href='http://localhost:8080/vietnam/assignProgram';">
                    Assign Program
                </button>
                <button class="button" onclick="location.href='http://localhost:8080/vietnam/viewPrograms';">
                    View Programs
                </button>
                </div>
                <div class="links"><a href="index.html">Back to Homepage</a></div>
            </div>
        </div>


<!--     
    <div class="user rectangleTop">
        <p class="textButton">Hello <% out.println(username); %></p>
    </div>
    <button class="login rectangleTop" onclick="login()">
        <p class="textButton">login</p>
    </button>
    <button class="logout rectangleTop" onclick="logout()">
        <p class="textButton">logout</p>
    </button>

    <button onclick="location.href='http://localhost:8080/vietnam/assignProgram';" class="rectangleButton" style="top: 70px"><p>assignProgram</p></button>
    <a href="seller/AddClient.jsp?username=<%=username%>"><div class="rectangleButton" style="top: 150px"><p>Add Client</p></div></a>
    <button onclick="location.href='http://localhost:8080/vietnam/viewPrograms';" class="rectangleButton" style="top: 230px"><p>viewPrograms</p></button> -->
    </section>
</body>

</html>