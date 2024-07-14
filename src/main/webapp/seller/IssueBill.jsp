<%@ include file="/seller/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>Bill Information</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
        <script>
           

            function issueBill() {

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
                    <h2>Bill Information</h2>
                    <form action="<%=request.getContextPath()%>/bills" method="post">
                        
                        
                        <input type="text" name="phonenumber" id="phonenumber" disabled value=${client.getPhoneNumberValue()}> 
                        
                        <div class="client-data">
                            <h3>Client Details</h3>
                            <div class="row">AFM: ${client.getAfm()}</div>
                            <div class="row">Name: ${client.getName()}</div>
                            <div class="row">Surname: ${client.getSurname()}</div>
                            <div class="row">Username: ${client.getUsername()}</div>
                            <div class="row" id="phonenumber0" name="phonenumber0" value=1234567890>Phonenumber: ${client.getPhoneNumberValue()}</div>
                            <div class="row" id="client_id" name="client_id" value="1" hidden></div>

                            <div></div>

                            <h3>Bill Details</h3>
                            <div class="row" id="selectedmonthint" name="selectedmonthint" value=22>Month: ${SelectedMonthText}</div>
                            <div class="row">Package Name: ${client.getPhoneNumber().getProgram().getName()}</div>

                            <div class="row">Total Call Duration:</div> <%--Somewhere, the total duration will be calculated.--%>
                            <div class="row">Total Cost:</div> <%--Somewhere, the total cost will be calculated.--%>

                        </div>
                        <input class="button" type="submit" value="Issue Bill">
                    </form>

                    <div></div>
                   <%-- <div id="issueBillButton" class="button" style="cursor: pointer;" onclick="window.location.href='<%=request.getContextPath()%>/bills?client_id=${client.getId()}&selectedmonthint=${SelectedMonthInt}&phonenumber=${client.getPhoneNumberValue()}&fromjsp=issuebill'"> Issue Bill </div> --%>
                    
                    
                    <p> ${referer} </p>
                </div>
            </div>
        </section>
    </body>
</html>

