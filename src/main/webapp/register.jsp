<%@ include file="/common.jsp" %>

<html>
    <head>
        <title>Create Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
        <script>
            function validateForm() {
                const username = document.getElementById('username').value;
                const password = document.getElementById('password').value;
                const confirmPassword = document.getElementById('confirmPassword').value;

                if(password !== confirmPassword){
                    alert("Passwords do not match");
                    return false;
                }

                if (username.trim() === "" || password.trim() === "") {
                    alert("Username and password cannot be empty or spaces");
                    return false;
                }

                return true;
            }
        </script>
    </head>

    <body>
        <section>
            <% for(int i = 0; i < 252; i++) { %>
                <span></span>
            <% } %>
            <div class="signin">
                <div class="content">
                    <h2>Register as Admin</h2>
                    <form class="form" action="<%=request.getContextPath()%>/register-admin" method="post" onsubmit="return validateForm()">
                        <div class="inputBox">
                            <input type="text" id="name" name="name" required>
                            <i>Name</i>
                        </div>
                        <div class="inputBox">
                            <input type="text" id="surname" name="surname" required>
                            <i>Surname</i>
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

                            <input type="submit" value="Sign Up">

                        </div>
                    </form>
                    <div class="links"><a href="<%=request.getContextPath()%>/index.jsp">Back</a></div>
                </div>
            </div>
        </section>
    </body>

</html>