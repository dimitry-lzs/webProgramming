<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%String username = (String) request.getAttribute("username");%>
<%String password = (String) request.getAttribute("password");%>

<html>
<head>
    <title>Seller Page</title>
    <style>
        
        a {
            text-decoration: none;
        }
        p{
            font-size: 20px;
            font-family: sans-serif;
        }
        .textButton{
            color: white;
            text-align: center;
            margin-top: 10px;
        }
        .rectangleTop {
            border-color: transparent;
            height: 50px;
            width: 70px;
            cursor: pointer;
            position: absolute;
            right: 0;
            top: 0;
        }
        .logout {

            background-color: rgb(193, 60, 60);
        }
        .login {
            right: 70px;
            background-color: rgb(72, 143, 197);
        }
        .user {
            left: 0px;
            width: 100vw;
            background-color: rgb(118, 117, 117);
            cursor: default;
        }
        .rectangleButton{
        
            background-color: rgb(168, 168, 168);
            border-color: transparent;
            color: rgb(72, 72, 72);
            margin-right: 20px;
            width: 500px;
            height: 70px;
            font-size: 20px;
            text-align: center;
            cursor: pointer;
            position: absolute;
        }
        
    </style>

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
</head>
<body>
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
    <form form class="form" action="/vietnam/seller" method="post">
        <input type="hidden" id="username" name="username" value="<%= username%>">
        <input type="hidden" id="password" name="password" value="<%= password%>">
        <input type="hidden" id="controler" name="controler" value="CLIENT">

        <button class="rectangleButton" style="top: 150px" type="submit" >Create Client</button>

    </form>
    <button onclick="location.href='http://localhost:8080/vietnam/viewPrograms';" class="rectangleButton" style="top: 230px"><p>viewPrograms</p></button>


</body>
</html>
