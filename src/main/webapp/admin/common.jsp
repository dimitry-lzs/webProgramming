<%@ include file="/common.jsp" %>

<%
    Login login = (Login) session.getAttribute("user");

    if (login == null) {
        response.sendRedirect("/vietnam/error.jsp?errorMessage=You are not logged in!");
        return;
    }

    String type = login.getType().name();
    String username = login.getUsername();

    if (!type.equals("ADMIN")) {
        String redirectLink = request.getContextPath() + "/error.jsp";
        redirectLink += "?errorMessage=Permission denied";
        redirectLink += "&link=" + request.getContextPath() + login.getType().getRedirectPath();
        response.sendRedirect(redirectLink);
    }
%>