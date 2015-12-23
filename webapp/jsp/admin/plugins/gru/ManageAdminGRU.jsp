<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="manageadmingru" scope="session" class="fr.paris.lutece.plugins.gru.web.ManageAdminGRUJspBean" />

<% manageadmingru.init( request, manageadmingru.RIGHT_MANAGEADMINGRU ); %>
<%= manageadmingru.getManageAdminGRUHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
