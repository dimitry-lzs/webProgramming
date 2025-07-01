<%@ include file="/client/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>Bills</title>
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
                    <h2>Bills</h2>
                    <div class="table">
                        <div class="table-header">
                            <div class="header-cell">Month</div>
                            <div class="header-cell">Charge</div>
                            <div class="header-cell">Paid</div>
                        </div>
                        <div class="table-content">
                            <c:forEach var="bill" items="${bills}">
                                <div class="table-row" style="cursor: pointer;" onclick="window.location.href='<%=request.getContextPath()%>/bills?&billID=${bill.getID()}'">
                                    <div class="table-data">${bill.getMonth()}</div>
                                    <div class="table-data">${bill.getCharge()}</div>
                                    <div class="table-data">
                                        <c:choose>
                                            <c:when test="${bill.isPaid()}">Paid</c:when>
                                            <c:otherwise>Not Paid</c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="links"><a href="<%=request.getContextPath()%>/client/menu.jsp">Back to Menu</a></div>
                </div>
            </div>
        </section>
    </body>
</html>