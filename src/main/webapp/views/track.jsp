<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>

<h1>${track.artist} - ${track.title}</h1>
<p>
	id: ${track.id}<br>
	title: ${track.title}<br>
	artist: ${track.artist}<br>
	album: ${track.album}<br>
	price: ${track.price}
</p>
		
<%@ include file="footer.jsp" %>