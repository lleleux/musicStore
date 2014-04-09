<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

	<head>
	
		<meta charset="utf-8">
		
		<title>MusicStore</title>
		
		<meta name="description" content="MusicStore">
		<meta name="author" content="Leleux Laurent">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<link rel="shortcut icon" href="<c:url value="/resources/img/favicon.gif" />">
		
		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" type="text/css" media="screen">
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" type="text/css" media="screen">
		
		<script src="<c:url value="/resources/js/jquery-2.0.3.js" />"></script>
		<script src="<c:url value="/resources/js/jquery.raty.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		
	</head>
	
	<body>
		
		<div class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/" />">MusicStore</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav pull-right">
					<li><a href="<c:url value="/tracks" />"><i class="icon-home"></i>Library</a></li>
					<c:choose>
						<c:when test="${not empty sessionScope.login}">
							<li><a href="<c:url value="/addTrack" />"><i class="icon-home"></i>Add Track</a></li>
							<li><a href="<c:url value="/sites" />"><i class="icon-home"></i>Other Sites</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">Welcome ${sessionScope.login} <span class="add-on"><i class="icon-chevron-down"></i></span></a>
								<ul class="dropdown-menu">
									<li><a href="<c:url value="/notifications" />"><span class="glyphicon glyphicon-bell"></span> Notifications <span class="badge">${fn:length(notifications)}</span></a></li>
									<li><a href="<c:url value="/orders" />"><span class="glyphicon glyphicon-barcode"></span> Orders</a></li>
									<li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-off"></span> Disconnect</a></li>
								</ul>
							</li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:url value="/signIn" />">Sign in</a></li>
							<li><a href="<c:url value="/login" />">Log in</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		
		<div id="wrap">
		
			<c:if test="${not empty success}">
				<div class="alert alert-success alert-dismissable">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong>Success!</strong> ${success}
				</div>
			</c:if>
			
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissable">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong>Error!</strong> ${error}
				</div>
			</c:if>