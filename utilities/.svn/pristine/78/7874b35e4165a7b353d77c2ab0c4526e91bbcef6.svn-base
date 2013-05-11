<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shibboleth.net Account Password Change Tool</title>
    </head>
    
    <body>
        <img src="<%= request.getContextPath() %>/images/logo.jpg" />
        <h3>Shibboleth.net Password Change Tool</h3>
        
        <% 
            if(request.getAttribute(net.shibboleth.infra.passwd.PasswordChangeServlet.INFO_ATTRIB) != null){
        %>
        <p><font color="blue"><%= request.getAttribute(net.shibboleth.infra.passwd.PasswordChangeServlet.INFO_ATTRIB) %></font></p>
        <% 
            }
        
            if(request.getAttribute(net.shibboleth.infra.passwd.PasswordChangeServlet.ERROR_ATTRIB) != null){
        %>
        <p><font color="red"><%= request.getAttribute(net.shibboleth.infra.passwd.PasswordChangeServlet.ERROR_ATTRIB) %></font></p>
        <%
            }
        %>
        
        <form action="<%= request.getContextPath() %>/ChangePassword" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input name="<%= net.shibboleth.infra.passwd.PasswordChangeServlet.USERID_PARAM %>" type="text" tabindex="1"/></td>
                </tr>
                <tr>
                    <td>Current Password:</td>
                    <td><input name="<%= net.shibboleth.infra.passwd.PasswordChangeServlet.CURRENT_PASS_PARM %>" type="password"" tabindex="2"/></td>
                </tr>
                <tr>
                    <td>New Password:</td>
                    <td><input name="<%= net.shibboleth.infra.passwd.PasswordChangeServlet.NEW_PASS_PARM %>" type="password" tabindex="3"/></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Change Password" tabindex="4" /></td>
                </tr>
            </table>
        </form>

        <p>Your new password adhere to the following rules:</p>
        <ul>
            <li>Be between 8 and 16 characters</li>
            <li>Contain no whitespace</li>
            <li>Contain no keyboard sequences (e.g., 12345, 1qaz, lkjhg)</li>
            <li>Contain no dictionary word spelled forwards or backwards</li>
            <li>Contain characters from at least 3 of the following 4 sets:
                <ul>
                    <li>Upper case alphabetic characters</li>
                    <li>Lower case alphabetic characters</li>
                    <li>Numeric characters</li>
                    <li>Special/punctuation characters</li>
                </ul>
            </li>
        </ul>
        
    </body>
</html>
