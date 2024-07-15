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
                            <div class="row">Total Call Duration: ${TotalCallDuration}m</div> <!--Somewhere, the total duration will be calculated.-->
                            <div class="row">Total Cost: ${TotalCost}$</div> <!--Somewhere, the total cost will be calculated.-->

                        </div>

                        <form class="form" action="<%=request.getContextPath()%>/bills" method="post">
                        
                            <!-- If field is disabled, getParameter in BillController will be null. I removed disabled attributem and all the other fields like name, surname etc, and getParameter is not null anymore. -->
                            <input type="text" name="phonenumber" id="phonenumber" hidden value=${client.getPhoneNumberValue()}> 
                            <input type="text" name="client_id" id="client_id" hidden value=${client.getId()}> 
                            <input type="text" name="selectedmonthint" id="selectedmonthint" hidden value="<%=month%>"> 

                            <input class="button" type="submit" value="Issue Bill">
                        </form>
                       

                    <div></div>
                   <!--<div id="issueBillButton" class="button" style="cursor: pointer;" onclick="window.location.href='<%=request.getContextPath()%>/bills?client_id=${client.getId()}&phonenumber=${client.getPhoneNumberValue()}&fromjsp=issuebill'"> Issue Bill Old </div>-->
                    
                    
                </div>
            </div>
        </section>
    </body>
</html>

