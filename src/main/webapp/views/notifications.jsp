<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
	
<h1>Notifications</h1>
<table class="table">
	<thead>
		<tr>
			<th>Notification</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty notifications}">
				<tr>
					<td colspan="5">All notifications are read...</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="n" items="${notifications}">
					<tr>
						<td>${n.text}</td>
						<td>
							<form method="POST" action="<c:url value="/notifications/${n.id}" />" role="form">
								<button type="submit" class="btn btn-default btn-xs">
									<span class="glyphicon glyphicon-ok"></span> Mark as read
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<br>


<%@ include file="footer.jsp" %>