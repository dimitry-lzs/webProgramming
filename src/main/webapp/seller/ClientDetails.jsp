<%@ include file="/seller/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>Clients</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
        <script>
            let timeout = null;

            function updatePhoneNumber() {
                let program_id = document.getElementById("program").value;
                let number = "${client.getPhoneNumberValue()}";
                let url = "<%=request.getContextPath()%>/phonenumbers";

                fetch(url, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        program_id,
                        number
                    })
                })
                .then(response => {
                    if (response.ok) {
                        document.getElementById("updateButton").innerHTML = "Updated!";
                        if (timeout) {
                            clearTimeout(timeout);
                        }
                        timeout = setTimeout(() => {
                            document.getElementById("updateButton").innerHTML = "Update";
                        }, 2000);
                    }
                })
                .catch(error => {
                    document.getElementById("updateButton").innerHTML = "Error!";
                    if (timeout) {
                        clearTimeout(timeout);
                    }
                    timeout = setTimeout(() => {
                        document.getElementById("updateButton").innerHTML = "Update";
                    }, 2000);
                });
            }
        </script>
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
                    <h2>Client Details</h2>
                    <div class="client-data">
                        <div class="row">AFM: ${client.getAfm()}</div>
                        <div class="row">Name: ${client.getName()}</div>
                        <div class="row">Surname: ${client.getSurname()}</div>
                        <div class="row">Username: ${client.getUsername()}</div>
                        <div class="row">Phone Number: ${client.getPhoneNumberValue()}</div>
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

                            <div id="issueBillButton" class="button" style="cursor: pointer;" onclick="window.location.href='<%=request.getContextPath()%>/bills?clientId=${client.getId()}'"> View Client's Bills </div>

                        </div>
                    </div>
                    <div class="links"><a href='<%=request.getContextPath()%>/clients';>Back to List</a></div>
                </div>
            </div>
        </section>
    </body>
</html>