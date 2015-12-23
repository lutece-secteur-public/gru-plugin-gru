<jsp:useBean id="managedemandDemandType" scope="session" class="fr.paris.lutece.plugins.gru.web.DemandTypeJspBean" />
<% String strContent = managedemandDemandType.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
