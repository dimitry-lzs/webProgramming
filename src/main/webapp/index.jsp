<%@ include file="/common.jsp" %>
<%
    Login login = (Login) session.getAttribute("user");
    if (login != null) {
        String redirectLink = request.getContextPath() + login.getType().getRedirectPath();
        response.sendRedirect(redirectLink);
    }
%>

<html>
    <head>
    <title>Welcome!</title>
    <link rel="stylesheet" href="./style.css">
    <link rel="apple-touch-icon" sizes="180x180" href="./favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="./favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="./favicon/favicon-16x16.png">
    <link rel="manifest" href="./favicon/site.webmanifest">
    </head>

    <body>
        <section>
            <% for(int i = 0; i < 252; i++) { %>
                <span></span>
            <% } %>
            <div class="signin">
                <div class="content">
                    <h2>Sign in as:</h2>
                    <div>
                    <button class="button" onclick="location.href='<%=request.getContextPath()%>/loginPage.jsp?type=CLIENT';">
                        Client
                    </button>
                    <button class="button" onclick="location.href='<%=request.getContextPath()%>/loginPage.jsp?type=SELLER';">
                        Seller
                    </button>
                    <button class="button" onclick="location.href='<%=request.getContextPath()%>/loginPage.jsp?type=ADMIN';">
                        Admin
                    </button>
                    </div>
                    <div class="links"><a href="<%=request.getContextPath()%>/register.jsp">Register as Admin</a></div>
                </div>
            </div>
        </section>
    </body>

</html>