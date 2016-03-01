<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:include page="../../AdminHeader.jsp" />

<jsp:useBean id="managedomaingru" scope="session" class="fr.paris.lutece.plugins.gru.web.domain.ManageDomainGruJspBean" />

<% managedomaingru.init( request, managedomaingru.RIGHT_MANAGEDOMAINGRU ); %>
<%= managedomaingru.getManageDomainGruHome ( request ) %>

<%@ include file="../../AdminFooter.jsp" %>
