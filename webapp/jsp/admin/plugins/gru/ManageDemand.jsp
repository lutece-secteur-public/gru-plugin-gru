<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="managedemand" scope="session" class="fr.paris.lutece.plugins.gru.web.ManageDemandJspBean" />

<% managedemand.init( request, managedemand.RIGHT_MANAGEDEMAND ); %>
<%= managedemand.getManageDemandHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
