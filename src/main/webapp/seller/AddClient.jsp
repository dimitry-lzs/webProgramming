<%@ include file="/seller/common.jsp" %>

<html>

    <head>
        <title>Create Client</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
        <script>
            function validateForm() {
                const username = document.getElementById('username').value;
                const password = document.getElementById('password').value;
                const confirmPassword = document.getElementById('confirmPassword').value;

                if (password !== confirmPassword) {
                    alert("Passwords do not match");
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
            <header class="header">
                <div class="home">
                    <a href="<%=request.getContextPath()%>/seller/menu.jsp">Home</a>
                </div>
                <div class="sessionTools">
                    <div class="user">
                        <div class="username"><% out.println(username); %></div>
                        <div class="role"><% out.println(type); %></div>
                    </div>
                    <div class="logout">
                        <a href="<%=request.getContextPath()%>/logout">Logout</a>
                    </div>
                </div>
            </header>
            <div class="signin">
                <div class="content">
                    <h2>Create Client</h2>
                    <form class="form" action="<%=request.getContextPath()%>/clients" method="post" onsubmit="return validateForm()">
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
                            <input type="submit" value="Create">
                        </div>
                    </form>
                    <div class="links"><a href="<%=request.getContextPath()%>/seller/menu.jsp">Back to Menu</a></div>
                </div>
            </div>
        </section>
    </body>
</html>