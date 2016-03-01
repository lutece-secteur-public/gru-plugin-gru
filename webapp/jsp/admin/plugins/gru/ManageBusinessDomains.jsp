<jsp:useBean id="managedomaingruBusinessDomain" scope="session" class="fr.paris.lutece.plugins.gru.web.domain.BusinessDomainJspBean" />
<% String strContent = managedomaingruBusinessDomain.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
