<%@ include file="/seller/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>Client Bills</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
    </head>

    <body>
        <section> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span>
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
                    <h2>Client's Bills</h2>
                    <div class="client-data">
                        <div class="row">${client.getAfm()}</div>
                        <div class="row">${client.getName()}</div>
                        <div class="row">${client.getSurname()}</div>
                        <div class="row">${client.getUsername()}</div>
                        <div class="row">${client.getPhoneNumberValue()}</div>
                        <h3>Client's Program</h3>
                        <div class="row">
                            <div class="select-style">
                                <select name="program" id="program">
                                    <option value="null">Select Program</option>
                                    <c:forEach var="program" items="${programs}">
                                        <c:choose>
                                            <c:when test="${not empty client.getPhoneNumber() and not empty client.getPhoneNumber().getProgram() and client.getPhoneNumber().getProgram().getId() == program.getId()}">
                                                <option value="${program.getId()}" selected>${program.getName()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${program.getId()}">${program.getName()}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div id="updateButton" class="button" onclick="updatePhoneNumber()">Update</div>
                            <div id="issueBillButton" class="button" style="cursor: pointer;" onclick="window.location.href='<%=request.getContextPath()%>/clients?id=${client.getId()}&fromjsp=viewclientbills'"> Issue Bill </div>
                        </div>
                    </div>
                    <div class="links"><a href='<%=request.getContextPath()%>/clients';>Back to List</a></div>
                    <p> ${referer} </p>
                </div>
            </div>
        </section>
    </body>
</html>