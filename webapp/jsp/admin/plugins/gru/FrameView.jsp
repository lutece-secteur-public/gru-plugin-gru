<jsp:useBean id="frameview" scope="session" class="fr.paris.lutece.plugins.gru.web.FrameViewJspBean" />

<% String strContent = frameview.processController ( request , response ); %>

<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%= strContent %>

<%@ include file="../../AdminFooter.jsp" %>
