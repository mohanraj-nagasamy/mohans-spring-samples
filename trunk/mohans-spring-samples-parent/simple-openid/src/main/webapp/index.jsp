<%@ page session="true" %>
<%@ page import="org.openid4java.discovery.Identifier, org.openid4java.discovery.DiscoveryInformation, org.openid4java.message.ax.*,org.openid4java.message.sreg.*, org.openid4java.message.ax.FetchResponse, org.openid4java.message.ax.AxMessage, org.openid4java.message.*, org.openid4java.OpenIDException, java.util.List, java.io.IOException, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.openid4java.consumer.ConsumerManager, org.openid4java.consumer.InMemoryConsumerAssociationStore, org.openid4java.consumer.VerificationResult" %>

<html>
<body>
<%
    if (request.getParameter("logout")!=null)
    {
        session.removeAttribute("openid");
        session.removeAttribute("openid-claimed");
%>
    Logged out!<p>
<%
    }
	if (session.getAttribute("openid")==null) {
%>
<form method="POST" action="consumer_redirect.jsp">
<strong>OpenID:</strong>
<input type="text" name="openid" size="60"/><br>

for yahoo: type: yahoo.com<br>
for google tyep : https://www.google.com/accounts/o8/id

<input type="submit"/>
</form>
<%	
} else {

%>
Logged in as <%= session.getAttribute("openid") %><p>
<p> openid-claimed <%= session.getAttribute("openid-claimed") %></p>
<%
AuthSuccess authSuccess = (AuthSuccess)session.getAttribute("authSuccess");
out.print("authSuccess -> " + authSuccess + "<br>");
out.print("authSuccess.hasExtension(AxMessage.OPENID_NS_AX) -> " + authSuccess.hasExtension(AxMessage.OPENID_NS_AX) + "<br>");
out.print("authSuccess.hasExtension(SRegMessage.OPENID_NS_SREG) -> " + authSuccess.hasExtension(SRegMessage.OPENID_NS_SREG) + "<br>");

if (authSuccess != null && authSuccess.hasExtension(AxMessage.OPENID_NS_AX))
{
	   MessageExtension ext = authSuccess.getExtension(AxMessage.OPENID_NS_AX);

	    if (ext instanceof FetchResponse)
	    {
	        FetchResponse fetchResp = (FetchResponse) ext;
	        %>

		<p>
			firstName:: <%= fetchResp.getAttributeValue("firstname") %></p>
			namePerson:: <%= fetchResp.getAttributeValue("namePerson") %></p>			
		<p>
			email:: <%= fetchResp.getAttributeValue("email") %></p>
		<p>
			lastName:: <%= fetchResp.getAttributeValue("LastName") %></p>
		<p>
			language:: <%= fetchResp.getAttributeValue("language") %></p>
		<p>
			gender:: <%= fetchResp.getAttributeValue("gender") %></p>
			
		<%        
	    }
}

%>
<a href="?logout=true">Log out</a>

<% } %>
</body>
</html>
