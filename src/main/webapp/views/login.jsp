<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>

<h1>Log in</h1>
<form class="col-xs-3" method="POST" action="<c:url value="/login" />">
	<div class="form-group">
		<label>Login</label>
		<input type="text" class="form-control" name="login" value="${formLogin}">
	</div>
	<div class="form-group">
		<label>Password</label>
		<input type="password" class="form-control"name="password">
	</div>
	<button type="submit" class="btn btn-default">Log in</button>
</form>
		
<%@ include file="footer.jsp" %>