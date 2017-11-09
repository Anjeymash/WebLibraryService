<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="WEB-INF/jsp/error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<h3>
		<fmt:message key="label.orderprofile" />
	</h3>

	<div class="navbar navbar-inverse">
		<div class="container">
			<div class="row">
				<div class="navbar-header">


					<c:if test="${not empty requestScope.errorMessage }">
						<div class="alert alert-warning">
							<c:out value="${requestScope.errorMessage}"></c:out>
						</div>
					</c:if>

					
						<table>

							<tr>
								<th><img src="img/ch.jpg" width="100" height="100"
									class="img-thumbnail"></th>

							</tr>

							<tr>
							
								<th><fmt:message key="label.title" />:</th>
								<td>${book.title}</td>
							</tr>
							<tr>
								<th><fmt:message key="label.author" />:</th>
								<td>${book.author}</td>
							</tr>
							<tr>
								<th><fmt:message key="label.genre" />:</th>
								<td>${book.genre}</td>
							</tr>
							<tr>
								<th><fmt:message key="label.year" />:</th>
								<td>${book.year}</td>
							</tr>
							
						

						</table><br/><br/>
				



					<form method="get" action="Controller" class="navbar-form">
						<input type="hidden" name="command" value="saverent" /> 
						<!-- <input	type="hidden" name="rentId" value="${requestScope.rent.rentId}" />
						<input	type="hidden" name="userRole" value="${requestScope.user.role}"/> -->
						<input	type="hidden" name="id" value="${requestScope.book.id}"/>

						<table>
							<tbody>
								<tr>
									<td><label><fmt:message key="label.start" /> (*):</label></td>
									<td><input type="date" class="form-control"
										name="startDate" value="${requestScope.rent.start}"
										placeholder="startDate" required="required" /></td>
								</tr>


								<tr>
									<td><label><fmt:message key="label.end" /> (*):</label></td>
									<td><input type="date" class="form-control" name="endDate"
										value="${requestScope.rent.end}" placeholder="endDate"
										required="required" /></td>
								</tr>




								<tr>
									<td><button class="btn btn-success"
											onclick="if (!(confirm('Are you sure you want to save changes?'))) return false"
											type="submit" value="Save" class="save">
											<fmt:message key="label.bookin" />
										</button></td>
								</tr>


							</tbody>
						</table>
					</form>

					<a href="Controller?command=listbook&bookGenre=${book.genre}">
						<button class="btn btn-primary">
							<fmt:message key="label.return" />
						</button>
					</a>


				</div>
			</div>
		</div>
	</div>
</body>
</html>