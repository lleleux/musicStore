<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
	
<h1>Library</h1>
<table class="table">
	<thead>
		<tr>
			<th>Title</th>
			<th>Artist</th>
			<th>Album</th>
			<th>Price</th>
			<th>Rating</th>
			<th>Buy</th>
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
											readOnly: <c:choose><c:when test="${empty sessionScope.login}">true</c:when><c:otherwise>false</c:otherwise></c:choose>,
											path: '<c:url value="/resources/img/" />', 
											click: function(score) {$(location).attr('href','<c:url value="/tracks/${t.id}/rating/" />' + score);}
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
											readOnly: <c:choose><c:when test="${empty sessionScope.login}">true</c:when><c:otherwise>false</c:otherwise></c:choose>,
											path: '<c:url value="/resources/img/" />',
											click: function(score) {$(location).attr('href','<c:url value="/tracks/${t.id}/rating/" />' + score);}
										});
									</script>
								</td>
							</c:otherwise>
						</c:choose>
						<td>
							<form method="POST" action="<c:url value="/orders" />" role="form">
								<input type="hidden" name="trackId" value="${t.id}">
								<button class="btn btn-default btn-xs" data-toggle="modal" data-target="#confirm${t.id}">
									<span class="glyphicon glyphicon-shopping-cart"></span> Buy
								</button>
								<div class="modal fade" id="confirm${t.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
												<h4 class="modal-title">Order Confirmation</h4>
											</div>
											<div class="modal-body">
												<p>Are you sure to buy this track ?</p>
												<b>Title : </b> ${t.title}<br>
												<b>Artist : </b> ${t.artist}<br>
												<b>Album : </b> ${t.album}<br>
												<b>Price : </b> ${t.price}<br>
												<br>
												<p>The order is valid during 1H.<br>After this period, the track is no more available for listening...</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
												<button type="submit" class="btn btn-primary">Buy</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<%@ include file="footer.jsp" %>