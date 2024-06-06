<%@ include file="common.jsp" %>

<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <h1>Admin Register</h1>
        <form action="register-admin" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name">
            <br>

            <input type="text" id="surname" name="surname">
            <br>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <br>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <br>

            <input type="submit" value="Register">
        </form>
        <a href="index.html">Back to login</a>
    </body>
</html>