<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
	
<h1>Add new track</h1>
<form class="col-xs-3" method="POST" action="<c:url value="/tracks" />" role="form">
	<div class="form-group">
		<label>Title</label>
		<input type="text" class="form-control" value="${formTitle}" name="title">
	</div>
	<div class="form-group">
		<label>Artist</label>
		<input type="text" class="form-control" value="${formArtist}" name="artist">
	</div>
	<div class="form-group">
		<label>Album</label>
		<input type="text" class="form-control" value="${formAlbum}" name="album">
	</div>
	<div class="form-group">
		<label>Track path</label>
		<input type="text" class="form-control" value="${formPath}" name="path">
	</div>
	<div class="form-group">
		<label>Price</label>
		<input type="text" class="form-control" value="${formPrice}" name="price">
	</div>
	<button type="submit" class="btn btn-default">Add track</button>
</form>
		
<%@ include file="footer.jsp" %>