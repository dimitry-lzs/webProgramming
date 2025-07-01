<%@ include file="/admin/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <head>
        <title>Update Program</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
        <script>
            let timeout;

            function updateProgram() {
                const url = "<%=request.getContextPath()%>/programs?id=${program.getId()}"
                const form = document.getElementById('updateForm');

                if (timeout) {
                    clearTimeout(timeout);
                    timeout = null;
                }

                document.getElementById("updateButton").innerHTML = "Updating...";

                fetch(url, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        programName: form.programName.value,
                        callTime: form.callTime.value,
                        charge: form.charge.value,
                        fee: form.fee.value,
                        benefits: form.benefits.value
                    })
                })
                .then(response => {
                    if (response.ok) {
                        document.getElementById("updateButton").innerHTML = "Updated!";
                        timeout = setTimeout(() => {
                            document.getElementById("updateButton").innerHTML = "Update";
                        }, 2000);
                    } else {
                        document.getElementById("updateButton").innerHTML = "Error!";
                        timeout = setTimeout(() => {
                            document.getElementById("updateButton").innerHTML = "Update";
                        }, 2000);
                    }
                })
                .catch(error => {
                    document.getElementById("updateButton").innerHTML = "Error!";
                    timeout = setTimeout(() => {
                        document.getElementById("updateButton").innerHTML = "Update";
                    }, 2000);
                })
                .finally(() => {
                    timeout = setTimeout(() => {
                        document.getElementById("updateButton").innerHTML = "Update";
                    }, 2000);
                })
            }
        </script>
    </head>

    <body>
        <section>
            <% for(int i = 0; i < 252; i++) { %>
                <span></span>
            <% } %>
             class="header">
                <div class="home">
                    <a href="<%=request.getContextPath()%>/admin/menu.jsp">Home</a>
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
                    <h2>Update Program</h2>
                    <form id="updateForm" class="form">
                        <div class="inputBox">
                            <input type="text" id="programName" name="programName" value="${program.getName()}" required>
                            <i>Program Name</i>
                        </div>
                        <div class="inputBox">
                            <input type="number" id="callTime" name="callTime"  value="${program.getCallTime()}" required>
                            <i>Call Time</i>
                        </div>
                        <div class="inputBox">
                            <input type="number" id="charge" name="charge" value="${program.getChargePerSecond()}" required>
                            <i>Charge per second</i>
                        </div>
                        <div class="inputBox">
                            <input type="number" id="fee" name="fee" value="${program.getFee()}" required>
                            <i>Fee</i>
                        </div>
                        <div class="inputBox">
                            <textarea id="benefits" name="benefits" required>${program.getBenefits()}</textarea>
                            <i>Benefits</i>
                        </div>
                    </form>
                    <div id="updateButton" class="button" onclick="updateProgram()">Update Program</div>
                    <div class="links"><a href="<%=request.getContextPath()%>/programs">Back to List</a></div>
                </div>
            </div>
        </section>
    </body>
</html>