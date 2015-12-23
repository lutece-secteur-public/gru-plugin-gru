<jsp:useBean id="manageadmingruExtrasAttributes" scope="session" class="fr.paris.lutece.plugins.gru.web.ExtrasAttributesJspBean" />
<% String strContent = manageadmingruExtrasAttributes.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
