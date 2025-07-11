<%@ include file="common.jsp" %>
<%
    String message = (String) request.getAttribute("message");
    String title = (String) request.getAttribute("title");
    String link = (String) request.getAttribute("link");

    if (link == null) {
        link = request.getContextPath() + "/index.jsp";
    }
%>
<html>
    <head>
        <title>${title}</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
    </head>
    <body>
        <section>
            <% for(int i = 0; i < 252; i++) { %>
                <span></span>
            <% } %>
            <div class="signin">
                <div class="content">
                    <h2>${message}</h2>
                    <div class="links">
                        <a href="${link}">Back</a>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>