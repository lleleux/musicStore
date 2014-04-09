<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
	
<h1>My Orders</h1>
<table class="table">
	<thead>
		<tr>
			<th>Title</th>
			<th>Artist</th>
			<th>Album</th>
			<th>Remaining listen time</th>
			<th>Rating</th>
			<th>Listen</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty orders}">
				<tr>
					<td colspan="5">No orders yet...</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="o" items="${orders}">
					<tr>
						<td>${o.track.title}</td>
						<td>${o.track.artist}</td>
						<td>${o.track.album}</td>
						<td>${fn:substringBefore(o.remainingSeconds / 60, '.')}' ${o.remainingSeconds % 60}"</td>
						<c:choose>
							<c:when test="${o.track.numberOfRatings eq 0}">
								<td>
									<div id="star${o.id}"></div> (${o.track.numberOfRatings})
									<script>
										$('#star${o.id}').raty({
											readOnly: <c:choose><c:when test="${empty sessionScope.login}">true</c:when><c:otherwise>false</c:otherwise></c:choose>,
											path: '<c:url value="/resources/img/" />', 
											click: function(score) {$(location).attr('href','<c:url value="/tracks/${o.track.id}/rating/" />' + score);}
										});
									</script>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<div id="star${o.id}"></div> (${o.track.numberOfRatings})
									<script>
										$('#star${o.id}').raty({
											score: '${o.track.rating}',
											readOnly: <c:choose><c:when test="${empty sessionScope.login}">true</c:when><c:otherwise>false</c:otherwise></c:choose>,
											path: '<c:url value="/resources/img/" />',
											click: function(score) {$(location).attr('href','<c:url value="/tracks/${o.track.id}/rating/" />' + score);}
										});
									</script>
								</td>
							</c:otherwise>
						</c:choose>
						<td>
							<c:choose>
								<c:when test="${o.remainingSeconds > 0}">
									<audio controls>
										<source src="<c:url value="/resources/music/${o.track.path}" />" type="audio/ogg">
									</audio>
								</c:when>
								<c:otherwise>
									Time elapsed...
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="footer.jsp" %>