<%@ include file="/seller/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>Programs</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
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
                    <a href="<%=request.getContextPath()%>/logout">
                        <div class="logout">
                            Logout
                        </div>
                    </a>
                </div>
            </header>
            <div class="signin">
                <div class="content">
                    <h2>Programs</h2>
                    <div class="table">
                        <div class="table-header">
                            <div class="header-cell">Name</div>
                            <div class="header-cell">Call Time</div>
                            <div class="header-cell">Charge</div>
                            <div class="header-cell">Fee</div>
                            <div class="header-cell">Benefits</div>
                        </div>
                        <div class="table-content">
                            <c:forEach var="program" items="${programs}">
                                <div class="table-row">
                                    <div class="table-data">${program.getName()}</div>
                                    <div class="table-data">${program.getCallTime()}</div>
                                    <div class="table-data">${program.getChargePerSecond()}</div>
                                    <div class="table-data">${program.getFee()}</div>
                                    <div class="table-data">${program.getBenefits()}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="links"><a href="<%=request.getContextPath()%>/seller/menu.jsp">Back to Menu</a></div>
                </div>
            </div>
        </section>
    </body>
</html>