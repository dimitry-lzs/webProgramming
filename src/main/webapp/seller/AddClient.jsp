<%@ include file="/common.jsp" %>
<%@ page import="com.webProgramming.src.Login" %>

<%
    Login login = (Login) session.getAttribute("user");
    if (login == null) {
        response.sendRedirect("/vietnam/error.jsp?errorMessage=You are not logged in!");
        return;
    }
    String username = login.getUsername();
    String type = login.getType().name();
    long id = login.getId();
%>

<html>

    <head>
        <title>Create Form</title>
        <link rel="stylesheet" href="./style.css">
        <script>
            function validateForm() {
                const username = document.getElementById('username').value;
                const password = document.getElementById('password').value;
                const AFM = document.getElementById('afm').value;
                const confirmPassword = document.getElementById('confirmPassword').value;

                if(password !== confirmPassword){
                    alert("Passwords do not match");
                    return false;
                }

                if (username.trim() === "" || password.trim() === "" || AFM.trim() === ""){
                    alert("Username, password, phoneNumber and AFM cannot be empty or spaces");
                    return false;
                }

                return true;
            }
        </script>
    </head>

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
                    <h2>Create Client</h2>
                    <form class="form" action="/vietnam/client" method="post" onsubmit="return validateForm()">
                        <div class="inputBox">
                            <input type="text" id="name" name="name" required>
                            <i>Name</i>
                        </div>
                        <div class="inputBox">
                            <input type="text" id="surname" name="surname" required>
                            <i>Surname</i>
                        </div>
                        <div class="inputBox">
                            <input type="text" id="afm" name="afm" required>
                            <i>AFM</i>
                        </div>
                        <div class="inputBox">
                            <input type="text" id="username" name="username" required>
                            <i>Username</i>
                        </div>
                        <div class="inputBox">
                            <input type="password" id="password" name="password" required>
                            <i>Password</i>
                        </div>
                        <div class="inputBox">
                            <input type="password" id="confirmPassword" name="confirmPassword" required>
                            <i>Confirm Password</i>
                        </div>
                        <div class="inputBox">
                            <input type="submit" value="create">
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>

</html>