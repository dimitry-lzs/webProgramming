<%@ include file="/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Login login = (Login) session.getAttribute("user");

    if (login == null) {
        response.sendRedirect("/vietnam/error.jsp?errorMessage=You are not logged in!");
    }
    String type = login.getType().name();
    String username = login.getUsername();

    if (!type.equals("SELLER")) {
        response.sendRedirect("/vietnam/error.jsp?errorMessage=Permission denied");
    }
%>


<html>

    <head>
        <title>Clients</title>
        <link rel="stylesheet" href="/vietnam/style.css">
        <script>
            let timeout = null;

            function updatePhoneNumber() {
                let program_id = document.getElementById("program").value;
                let number = "${client.getPhoneNumberValue()}";
                let url = "/vietnam/phonenumbers";

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
            <div class="signin">
                <div class="content">
                    <div class="client-data">
                        <div class="row">${client.getAfm()}</div>
                        <div class="row">${client.getName()}</div>
                        <div class="row">${client.getSurname()}</div>
                        <div class="row">${client.getUsername()}</div>
                        <div class="row">${client.getPhoneNumberValue()}</div>
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
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>