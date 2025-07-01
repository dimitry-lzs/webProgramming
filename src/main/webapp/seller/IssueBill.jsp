<%@ include file="/seller/common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.LocalDate" %>
<%
int month = LocalDate.now().getMonthValue();
%>

<html>

    <head>
        <title>Bill Information</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
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
                            <h3>Client Details</h3>
                            <div class="row">AFM: ${client.getAfm()}</div>
                            <div class="row">Name: ${client.getName()}</div>
                            <div class="row">Surname: ${client.getSurname()}</div>
                            <div class="row">Username: ${client.getUsername()}</div>

                            <div></div><div></div>

                            <h3>Bill Details</h3>
                            <div class="row">Phonenumber: ${client.getPhoneNumberValue()}</div>
                            <div class="row">Month: <%=month%></div>
                            <div class="row">Package Name: ${client.getPhoneNumber().getProgram().getName()}</div>
                            <div class="row">Total Call Duration: ${totalCallDuration}m</div> <!--Somewhere, the total duration will be calculated.-->
                            <div class="row">Total Cost: ${amount}$</div> <!--Somewhere, the total cost will be calculated.-->

                        </div>

                        <form class="form" action="<%=request.getContextPath()%>/bills" method="post">
                            <input type="hidden" name="_method" value="PUT">
                            <!-- If field is disabled, getParameter in BillController will be null. I removed disabled attributem and all the other fields like name, surname etc, and getParameter is not null anymore. -->
                            <input type="text" name="phonenumber" id="phonenumber" hidden value=${client.getPhoneNumberValue()}>
                            <input type="text" name="client_id" id="client_id" hidden value=${client.getId()}>
                            <input type="text" name="selectedmonthint" id="selectedmonthint" hidden value="<%=month%>">
                            <input type="text" name="totalCost" id="totalCost" hidden value=${amount}>
                            <input type="text" name="callDuration" id="callDuration" hidden value=${totalCallDuration}>
                            <input type="text" name="program_name" id="program_name" hidden value=${client.getPhoneNumber().getProgram().getName()}>
                            <input class="button" type="submit" value="Issue Bill">
                        </form>
                        <div class="links"><a onclick="window.history.back()">Back</a></div>
                </div>
            </div>
        </section>
    </body>
</html>

