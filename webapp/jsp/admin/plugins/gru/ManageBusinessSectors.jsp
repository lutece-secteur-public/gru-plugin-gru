<jsp:useBean id="managedomaingruBusinessSector" scope="session" class="fr.paris.lutece.plugins.gru.web.domain.BusinessSectorJspBean" />
<% String strContent = managedomaingruBusinessSector.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
