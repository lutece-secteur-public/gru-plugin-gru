<jsp:useBean id="managecustomers" scope="session" class="fr.paris.lutece.plugins.gru.web.CustomerJspBean" />
<% String strContent = managecustomers.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
