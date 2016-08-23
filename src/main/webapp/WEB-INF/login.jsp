<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>
    <body>
        <div id="login-panel">
            <b>Login</b>
            <form name='loginForm'
                  action="<c:url value='/j_spring_security_check'/>" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='username' required="required"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password' required="required" /></td>
                    </tr>
                    <tr>
                        <td colspan='4'><input name="submit" type="submit"
                                               value="submit" /></td>
                    </tr>
                </table>
            </form> 
                  <a href="register">Register</a>
        </div>
    </body>
</html>
