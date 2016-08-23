<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<b>Home</b>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h1>${pageContext.request.userPrincipal.name} is at home.</h1>
		<a href='<c:url value="/j_spring_security_logout" />'> Logout</a>
		<sec:authorize access="hasRole('ADMIN')">
			<a href="userlist">List Users</a>
		</sec:authorize>
	</c:if>
</body>
</html>
