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
                    <button class="button" onclick="location.href='http://localhost:8080/vietnam/loginPage.jsp?type=CLIENT';">
                        Client
                    </button>
                    <button class="button" onclick="location.href='http://localhost:8080/vietnam/loginPage.jsp?type=SELLER';">
                        Seller
                    </button>
                    <button class="button" onclick="location.href='http://localhost:8080/vietnam/loginPage.jsp?type=ADMIN';">
                        Admin
                    </button>
                    </div>
                    <div class="links"><a href="http://localhost:8080/vietnam/register.jsp">Register as Admin</a></div>
                </div>
            </div>
        </section>
    </body>

</html>