<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp" %>
	
<h1>Sites</h1>
<table class="table">
	<thead>
		<tr>
			<th>Name</th>
			<th>Pub</th>
			<th>API</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty sites}">
				<tr>
					<td colspan="5">No sites yet...</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="s" items="${sites}">
					<tr>
						<td>${s.name}</td>
						<td>${s.pub}</td>
						<td>${s.api}</td>
						<td>
							<form class="pull-left" method="POST" action="<c:url value="/sites/delete" />" role="form">
								<input type="hidden" name="siteId" value="${s.id}">
								<button class="btn btn-default btn-xs" data-toggle="modal" data-target="#confirm${s.id}">
									<span class="glyphicon glyphicon-trash"></span> Delete
								</button>
								<div class="modal fade" id="confirm${s.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
												<h4 class="modal-title">Are you sure ?</h4>
											</div>
											<div class="modal-body">
												<p>Are you sure to delete this site ?</p>
												<b>Name : </b> ${s.name}<br>
												<b>Pub : </b> ${s.pub}<br>
												<b>API : </b> ${s.api}<br>
												<br>
												<p>After, the pub would no more be displayed on the store, and the library cannot be accessed...</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
												<button type="submit" class="btn btn-primary">Delete</button>
											</div>
										</div>
									</div>
								</div>
							</form>
							<form class="pull-left" style="margin-left: 5px;" method="GET" action="<c:url value="/sites/${s.id}/tracks" />" role="form">
								<button type="submit" class="btn btn-default btn-xs">
									<span class="glyphicon glyphicon-music"></span> View library
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>

<br><br>

<button class="btn btn-default" data-toggle="modal" data-target="#addSite">
	<span class="glyphicon glyphicon-plus"></span> Add a new Site
</button>

<form method="POST" action="<c:url value="/sites" />" role="form">
	<div class="modal fade" id="addSite" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Add a new Site</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Name</label>
							<input type="text" class="form-control" name="name">
						</div>
						<div class="form-group">
							<label>Pub</label>
							<input type="text" class="form-control" name="pub">
						</div>
						<div class="form-group">
							<label>API</label>
							<input type="text" class="form-control" name="api">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-primary">Add site</button>
					</div>
			</div>
		</div>
	</div>
</form>


<%@ include file="footer.jsp" %>