<jsp:useBean id="managedemandDemandTypeAction" scope="session" class="fr.paris.lutece.plugins.gru.web.DemandTypeActionJspBean" />
<% String strContent = managedemandDemandTypeAction.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
