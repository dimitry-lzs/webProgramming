<%@ include file="/common.jsp" %>

<%
    Login login = (Login) session.getAttribute("user");

    if (login == null) {
        response.sendRedirect("/vietnam/error.jsp?errorMessage=You are not logged in!");
        return;
    }

    String type = login.getType().name();
    String username = login.getUsername();

    if (!type.equals("CLIENT")) {
        response.sendRedirect("/vietnam/error.jsp?errorMessage=Persmission denied");
    }
%>