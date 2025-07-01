<%@ include file="/admin/common.jsp" %>

<html>
    <head>
        <title>Create Program</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
    </head>

    <body>
        <section>
            <% for(int i = 0; i < 252; i++) { %>
                <span></span>
            <% } %>
            <header class="header">
                <div class="home">
                    <a href="<%=request.getContextPath()%>/admin/menu.jsp">Home</a>
                </div>
                <div class="sessionTools">
                    <div class="user">
                        <div class="username"><% out.println(username); %></div>
                        <div class="role"><% out.println(type); %></div>
                    </div>
                    <a href="<%=request.getContextPath()%>/logout">
                        <div class="logout">
                            Logout
                        </div>
                    </a>
                </div>
            </header>
            <div class="signin">
                <div class="content">
                    <h2>Create Program</h2>
                    <form class="form" action="<%=request.getContextPath()%>/programs?option=Create_Program" method="post">
                        <div class="inputBox">
                            <input type="text" id="programName" name="programName" required>
                            <i>Program Name</i>
                        </div>
                        <div class="inputBox">
                            <input type="number" id="callTime" name="callTime" required>
                            <i>Call Time</i>
                        </div>
                        <div class="inputBox">
                            <input type="number" id="charge" name="charge" required>
                            <i>Charge per second</i>
                        </div>
                        <div class="inputBox">
                            <input type="number" id="fee" name="fee" required>
                            <i>Fee</i>
                        </div>
                        <div class="inputBox">
                            <textarea id="benefits" name="benefits" required></textarea>
                            <i>Benefits</i>
                        </div>
                        <div class="inputBox">
                            <input type="submit" value="Create Program">
                        </div>
                    </form>
                    <div class="links"><a href="<%=request.getContextPath()%>/admin/menu.jsp">Back to Menu</a></div>
                </div>
            </div>
        </section>
    </body>
</html>