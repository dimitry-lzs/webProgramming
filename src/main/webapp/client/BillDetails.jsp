<%@ include file="/client/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>Bill Information</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
        <script>
            let timeout = null;

            function payBill(billID) {
                let url = "<%=request.getContextPath()%>/bills";

                fetch(url, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        billID
                    })
                })
                .then(response => {

                    if (response.ok) {
                        location.reload();
                    }
                })
                .catch(error => {
                    document.getElementById("payButton").innerHTML = "Error!";
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
                    <h2>Bill Information</h2>

                        <div class="client-data">
                            <h3>Bill Details</h3>
                            <div class="row">Phonenumber: ${bill.getPhonenumber().getNumber()}</div>
                            <div class="row">Month: ${bill.getMonth()}</div>
                            <div class="row">Program Name: ${bill.getProgramName()}</div>
                            <div class="row">Total Call Duration: ${bill.getCallDuration()}m</div> <!--Somewhere, the total duration will be calculated.-->
                            <div class="row">Total Cost: ${bill.getCharge()}$</div> <!--Somewhere, the total cost will be calculated.-->
                            <div class="row">
                                <c:choose>
                                    <c:when test="${bill.isPaid()}">Paid</c:when>
                                    <c:otherwise>Not Paid</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${!bill.isPaid()}">
                                 <div class="button" name="payButton" id="payButton" onclick="payBill('${bill.getID()}')" value="Pay Bill">Pay Bill</div>
                            </c:when>
                        </c:choose>


                    <div class="links"><a href="<%=request.getContextPath()%>/bills">Back to Bills</a></div>
                </div>
            </div>
        </section>
    </body>
</html>

