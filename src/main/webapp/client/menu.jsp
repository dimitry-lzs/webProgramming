<%@ include file="/client/common.jsp" %>

<html>

<head>
    <title>Menu</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>

<body>
    <section>
        <% for(int i = 0; i < 252; i++) { %>
            <span></span>
        <% } %>
        <header class="header">
            <div class="home">
                <a href="<%=request.getContextPath()%>/client/menu.jsp">Home</a>
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
                <h2>Hello <% out.println(username); %></h2>
                <div class="buttons">
                    <button class="button" onclick="location.href='<%=request.getContextPath()%>/bills';">
                        View Bills
                    </button>
                    <button class="button" onclick="location.href='<%=request.getContextPath()%>/calls';">
                        View Call History
                    </button>
                </div>
            </div>
        </div>
    </section>
</body>

</html>