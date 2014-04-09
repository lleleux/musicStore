<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>

<h1>Sign in</h1>
<form class="col-xs-3" method="POST" action="<c:url value="/signIn" />">
	<div class="form-group">
		<label>Name</label>
		<input type="text" class="form-control" name="name" value="${formName}">
	</div>
	<div class="form-group">
		<label>Last name</label>
		<input type="text" class="form-control" name="lastName" value="${formLastName}">
	</div>
	<div class="form-group">
		<label>E-Mail</label>
		<input type="text" class="form-control" name="email" value="${formEmail}">
	</div>
	<div class="form-group">
		<label>Login</label>
		<input type="text" class="form-control" name="login" value="${formLogin}">
	</div>
	<div class="form-group">
		<label>Password</label>
		<input type="password" class="form-control" name="password" value="${formPassword}">
	</div>
	<div class="form-group">
		<label>Re-type password</label>
		<input type="password" class="form-control" name="password2" value="${formPassword2}">
	</div>
	<button type="submit" class="btn btn-default">Sign in</button>
</form>
		
<%@ include file="footer.jsp" %>