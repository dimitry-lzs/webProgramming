<%@ include file="/client/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>Bill Information</title>
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

                        <form class="form" action="<%=request.getContextPath()%>/bills" method="put">
                        
                            <!-- If field is disabled, getParameter in BillController will be null. I removed disabled attributem and all the other fields like name, surname etc, and getParameter is not null anymore. -->
                            <input type="text" name="phonenumber" id="phonenumber" hidden value=${bill.getPhonenumber().getNumber()}> 
                            <input type="text" name="client_id" id="client_id" hidden value=${clientId}> 
                            <input type="text" name="selectedmonthint" id="selectedmonthint" hidden value=${bill.getMonth()}> 
                            <input type="text" name="totalCost" id="totalCost" hidden value=${bill.getCharge()}>
                            <input type="text" name="callDuration" id="callDuration" hidden value=${bill.getCallDuration()}>
                            <input type="text" name="program_name" id="program_name" hidden value=${bill.getProgramName()}>
                            
                            <input type="text" name="billId" id="billId" hidden value=${billID}>
                            <input class="button" type="submit" value="Pay Bill">
                        </form>

                    <div></div>

                    

                    <div class="links"><a href="<%=request.getContextPath()%>/bills">Back to Bills</a></div>
                </div>
            </div>
        </section>
    </body>
</html>

