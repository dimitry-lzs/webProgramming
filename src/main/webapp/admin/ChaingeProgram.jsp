<%@ include file="/admin/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>

    <head>
        <title>programs</title>
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
                    <a href="<%=request.getContextPath()%>/admin/menu.jsp">Home</a>
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
                    <h2>Programs</h2>
                    <div class="table">
                        <div class="table-header">
                            <div class="header-cell">Benefits</div>
                            <div class="header-cell">Call_Time</div>
                            <div class="header-cell">Charge_Per_Second</div>
                            <div class="header-cell">Fee</div>
                            <div class="header-cell">Name</div>
                        </div>
                        <div class="table-content" var="program" items="${program}">
                            <div class="table-row">
                                <div class="table-data">${program.getBenefits()}</div>
                                <div class="table-data">${program.getCallTime()}</div>
                                <div class="table-data">${program.getChargePerSecond()}</div>
                                <div class="table-data">${program.getFee()}</div>
                                <div class="table-data">${program.getName()}</div>
                            </div>
                        </div>
                    </div>
                    <form class="form" action="<%=request.getContextPath()%>/programs?option=Ubdate_Program&amp;id=${program.getId()}" method="post">
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
                            <input type="submit" value="Ubdate Program">
                        </div>
                    </form>
                    <div class="links"><a href="<%=request.getContextPath()%>/admin/menu.jsp">Back to List</a></div>
                </div>
            </div>
        </section>
    </body>
</html>