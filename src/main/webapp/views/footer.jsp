<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		</div>
		
		<div id="footer">
			<div class="container">

				<c:if test="${!empty pubs}">
				
					<div id="carousel-example-generic" class="carousel slide" data-interval="2000" data-ride="carousel" style="width: 468px; height: 60px; margin: 0 auto">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="p" items="${pubs}">
								<c:choose>
									<c:when test="${count == 0}">
										<li data-target="#carousel-example-generic" data-slide-to="${count}" class="active"></li>
									</c:when>
									<c:otherwise>
										<li data-target="#carousel-example-generic" data-slide-to="${count}"></li>
									</c:otherwise>
								</c:choose>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</ol>
						
						<!-- Wrapper for slides -->
						<div class="carousel-inner">
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="p" items="${pubs}">
								<c:choose>
									<c:when test="${count == 0}">
										<div class="item active">
											<iframe frameborder="0" scrolling="no" width="468" height="60" src="${p}"></iframe>
										</div>							</c:when>
									<c:otherwise>
										<div class="item">
											<iframe frameborder="0" scrolling="no" width="468" height="60" src="${p}"></iframe>
										</div>							</c:otherwise>
								</c:choose>
								<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
						</div>
						
						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left"></span>
						</a>
						<a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</div>
					
				</c:if>
		
			</div>
		</div>

	</body>
	
</html>