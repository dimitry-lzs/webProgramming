<%

String user = (String) request.getAttribute("user");
//available types are (admin, seller, client)
//use variable user to find the credentials into the database

%>

<html>

    <head>
        <title>Login Page</title>
        <style>
            
        </style>
        <script>
            function validateForm() {
                var username = document.getElementById('username').value;
                var password = document.getElementById('password').value;
            
                if (username.trim() === "" || password.trim() === "") {
                    alert("Username and password cannot be empty or spaces");
                    return false;
                }
            
                return true;
            }
        </script>
    </head>

    <body>
        <form action="http://localhost:8080/vietnam/Seller" method="post" onsubmit="return validateForm()">
            <p>Login</p>
            <input type="text" id="username" name="username" placeholder="Username">
            <input type="password" id="password" name="password" placeholder="Password">
            <input type="submit" value="Login">
            <p> 
                <%
                    out.println("User type is: " + user);
                %>
            </p>
        </form>

    </body>

</html>