<%

String type = (String) request.getAttribute("type");
//available types are (ADMIN, SELLER, CLIENT)
//use variable user to find the credentials into the database

%>

<html>

    <head>
        <title>Login Page</title>
        <style>

        </style>
        <script>
            function validateForm() {
                const username = document.getElementById('username').value;
                const password = document.getElementById('password').value;
                const type = "<%= type %>";

                if (username.trim() === "" || password.trim() === "") {
                    alert("Username and password cannot be empty or spaces");
                    return false;
                }

                if (!type) {
                    alert("User type is not defined");
                    return false;
                }

                document.getElementById('type').value = type;

                return true;
            }
        </script>
    </head>

    <body>
        <form action="http://localhost:8080/vietnam/login" method="post" onsubmit="return validateForm()">
            <p>Login</p>
            <input type="text" id="username" name="username" placeholder="Username">
            <input type="password" id="password" name="password" placeholder="Password">
            <input type="submit" value="Login">
            <input type="hidden" id="type" name="type">
            <p>
                <%
                    out.println("User type is: " + type);
                %>
            </p>
        </form>

    </body>

</html>