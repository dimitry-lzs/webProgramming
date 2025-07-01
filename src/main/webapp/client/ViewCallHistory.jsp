<%@ include file="/client/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>

    <head>
        <title>Call History</title>
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
                    <h2>Call History</h2>
                    <div class="table">
                        <div class="table-header">
                            <div class="header-cell">Caller</div>
                            <div class="header-cell">Receiver</div>
                            <div class="header-cell">Started</div>
                            <div class="header-cell">Ended</div>
                            <div class="header-cell">Duration</div>
                        </div>
                        <div class="table-content">
                            <c:forEach var="call" items="${calls}">
                                <div class="table-row">
                                    <div class="table-data">${call.getCaller().getNumber()}</div>
                                    <div class="table-data">${call.getReceiver().getNumber()}</div>
                                    <div class="table-data">${call.getCallTimestamp()}</div>
                                    <div class="table-data">${call.getEndTimestamp()}</div>
                                    <div class="table-data">${call.getDuration()}</div>
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