<%@ include file="common.jsp" %>

<html>
    <head>
        <title>Register Success</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
    </head>
    <body>
        <section>
            <% for(int i = 0; i < 252; i++) { %>
                <span></span>
            <% } %>
            <div class="signin">
                <div class="content">
                    <h2>Admin registered successfully</h2>
                    <div class="links"><a href="<%=request.getContextPath()%>/index.jsp">Back</a></div>
                </div>
            </div>
        </section>
    </body>
</html>