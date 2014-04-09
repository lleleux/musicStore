<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
	
<h1>Library of "${site.name}"</h1>
<table class="table">
	<thead>
		<tr>
			<th>Title</th>
			<th>Artist</th>
			<th>Album</th>
			<th>Price</th>
			<th>Rating</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty tracks}">
				<tr>
					<td colspan="5">No tracks yet...</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="t" items="${tracks}">
					<tr>
						<td>${t.title}</td>
						<td>${t.artist}</td>
						<td>${t.album}</td>
						<td>${t.price}&euro;</td>
						<c:choose>
							<c:when test="${t.numberOfRatings eq 0}">
								<td>
									<div id="star${t.id}"></div> (${t.numberOfRatings})
									<script>
										$('#star${t.id}').raty({
											readOnly: true,
											path: '<c:url value="/resources/img/" />'
										});
									</script>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<div id="star${t.id}"></div> (${t.numberOfRatings})
									<script>
										$('#star${t.id}').raty({
											score: '${t.rating}',
											readOnly: true,
											path: '<c:url value="/resources/img/" />'
										});
									</script>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="footer.jsp" %>