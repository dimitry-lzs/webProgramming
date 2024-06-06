<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <title>Admin Login</title>
    </head>

    <body>
        <form action="http://localhost:8080/vietnam/Admin" method="post">
            <p>Login (only seller)</p>
            <input type="text" id="username" name="username" placeholder="Username">
            <input type="password" id="password" name="password" placeholder="Password">
            <input type="submit" value="Login">
        </form>
    </body>

    </html>