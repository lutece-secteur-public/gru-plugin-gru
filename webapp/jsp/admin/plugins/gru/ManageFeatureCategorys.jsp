<jsp:useBean id="manageadmingruFeatureCategory" scope="session" class="fr.paris.lutece.plugins.gru.web.FeatureCategoryJspBean" />
<% String strContent = manageadmingruFeatureCategory.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
