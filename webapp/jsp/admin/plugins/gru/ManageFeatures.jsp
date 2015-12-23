<jsp:useBean id="manageadmingruFeature" scope="session" class="fr.paris.lutece.plugins.gru.web.FeatureJspBean" />
<% String strContent = manageadmingruFeature.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
