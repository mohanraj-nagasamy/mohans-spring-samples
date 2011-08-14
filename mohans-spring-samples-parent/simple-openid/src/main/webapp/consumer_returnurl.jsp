<%@ page session="true" %>
<%@ page import="org.openid4java.discovery.Identifier, org.openid4java.discovery.DiscoveryInformation, org.openid4java.message.ax.FetchRequest, org.openid4java.message.ax.FetchResponse, org.openid4java.message.ax.AxMessage, org.openid4java.message.*, org.openid4java.OpenIDException, java.util.List, java.io.IOException, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.openid4java.consumer.ConsumerManager, org.openid4java.consumer.InMemoryConsumerAssociationStore, org.openid4java.consumer.VerificationResult" %>

<%

    ConsumerManager manager=(ConsumerManager) pageContext.getAttribute("consumermanager", PageContext.APPLICATION_SCOPE);
try
        {
            // --- processing the authentication response

            // extract the parameters from the authentication response
            // (which comes in as a HTTP request from the OpenID provider)
            System.out.println("request.getParameterMap() -> " + request.getParameterMap());
            ParameterList responselist =
                    new ParameterList(request.getParameterMap());
            System.out.println("--------------------------------------------------");
            System.out.println("responselist -> " + responselist);

            // retrieve the previously stored discovery information
            DiscoveryInformation discovered = (DiscoveryInformation) session.getAttribute("openid-disco");
            System.out.println("discovered -> " + discovered);
            

            // extract the receiving URL from the HTTP request
            StringBuffer receivingURL = request.getRequestURL();
            System.out.println("discreceivingURLovered -> " + receivingURL);
            
            String queryString = request.getQueryString();
            System.out.println("--------------------------------------------------");
            System.out.println("queryString -> " + queryString);
            
            if (queryString != null && queryString.length() > 0)
                receivingURL.append("?").append(request.getQueryString());

            // verify the response; ConsumerManager needs to be the same
            // (static) instance used to place the authentication request
            VerificationResult verification = manager.verify( receivingURL.toString(), responselist, discovered);
            System.out.println("--------------------------------------------------");
            System.out.println("verification -> " + verification);
            

            // examine the verification result and extract the verified identifier
            Identifier verified = verification.getVerifiedId();
            System.out.println("--------------------------------------------------");
            System.out.println("verified -> " + verification);
            
            if (verified != null)
            {
                AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();
                
                session.setAttribute("authSuccess", authSuccess);
                session.setAttribute("openid", authSuccess.getIdentity());
                session.setAttribute("openid-claimed", authSuccess.getClaimed());
                response.sendRedirect(".");  // success                
            }
            else
            {
%>
            Failed to login!
<%
            }
        }
        catch (OpenIDException e)
        {
%>
            Login error!
<%
            // present error to the user
        }

%>
