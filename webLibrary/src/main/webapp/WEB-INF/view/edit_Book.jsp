<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.bean.Book" pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>Insert title here</title>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="navbar navbar-cent">
		<h3>
			<fmt:message key="label.bookform" />
		</h3>
	</div>
	<div class="navbar navbar-inverse">
		<div class="container">
			<div class="row">
				<div class="navbar-header">


					<c:if test="${not empty requestScope.errorMessage }">
						<div class="alert alert-warning">
							<c:out value="${requestScope.errorMessage}"></c:out>
						</div>
					</c:if>
					
					<c:if test="${not empty book.id }">
						<img src="img/${book.id}.jpg" width="200" height="200"
							class="img-responsive">
					</c:if>

					<form method="post" action="Controller" class="navbar-form">
						<input type="hidden" name="command" value="savebook" /> <input
							type="hidden" name="id" value="${requestScope.book.id}" />

						<table>
							<tbody>
								<tr>
									<td><label><fmt:message key="label.title" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="bookTitle" value="${requestScope.book.title}"
										placeholder="title" required="required" /></td>
								</tr>


								<tr>
									<td><label><fmt:message key="label.author" />
											(*):</label></td>
									<td><input type="text" class="form-control"
										name="bookAuthor" value="${requestScope.book.author}"
										placeholder="author" required="required" /></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.genre" /> (*):</label></td>
									<td><select required size="1" name="bookGenre"
										class="form-control">
											<option value="children"><fmt:message key="label.children" /></option>
											<option value="scifi"><fmt:message key="label.scifi" /></option>
											<option value="novels"><fmt:message key="label.novels" /></option>
											<option value="adventures"><fmt:message key="label.adventures" /></option>
									</select></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.year" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="bookYear" value="${requestScope.book.year}"
										placeholder="year" required="required" /></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.quantity" />
											(*):</label></td>
									<td><input type="text" class="form-control"
										name="bookQuantity" value="${requestScope.book.quantity}"
										placeholder="quantity" required="required" /></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.status" />
											(*):</label></td>
									<td><select required size="1" name="bookStatus"
										class="form-control">
											<option value="1"><fmt:message key="label.active" /></option>
											<option value="0"><fmt:message key="label.deleted" /></option>
									</select></td>
								<tr>
									<td><label><fmt:message key="label.context" />
											(*):</label></td>
									<td><textarea cols="100" rows="10" name="bookContext"
											placeholder="context"> ${requestScope.book.context}</textarea></td>
								</tr>




								<tr>
									<td><button class="btn btn-success"
											onclick="if (!(confirm('Are you sure you want to save changes?'))) return false"
											type="submit" value="Save" class="save">
											<fmt:message key="label.save" />
										</button></td>
								</tr>




							</tbody>
						</table>
					</form>

					<a
						href="Controller?command=listbook&bookGenre=${requestScope.book.genre}">
						<button class="btn btn-primary">
							<fmt:message key="label.returntobooks" />
						</button>
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>