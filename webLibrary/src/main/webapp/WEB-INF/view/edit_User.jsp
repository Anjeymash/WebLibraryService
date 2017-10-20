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
	<h3><fmt:message key="label.userprofile" /></h3>

	<div class="navbar navbar-inverse">
		<div class="container">
			<div class="row">
				<div class="navbar-header">


					<c:if test="${not empty requestScope.errorMessage }">
						<div class="alert alert-warning">
							<c:out value="${requestScope.errorMessage}"></c:out>
						</div>
					</c:if>


					<form method="post" action="Controller" class="navbar-form">
						<input type="hidden" name="command" value="saveuser" /> <input
							type="hidden" name="userId" value="${requestScope.user.id}" /> <input
							type="hidden" name="userRole" value="${requestScope.user.role}" />

						<table>
							<tbody>
								<tr>
									<td><label><fmt:message key="label.login" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="userLogin" value="${requestScope.user.login}"
										placeholder="login" required="required" /></td>
								</tr>


								<tr>
									<td><label><fmt:message key="label.name" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="userName" value="${requestScope.user.name}"
										placeholder="name" required="required" /></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.surname" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="userSurName" value="${requestScope.user.surname}"
										placeholder="surname" required="required" /></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.password" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="userPassword" value="${requestScope.user.password}"
										placeholder="password" required="required" /></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.email" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="userEMail" value="${requestScope.user.eMail}"
										placeholder="eMail" required="required" /></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.tel" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="userTelNumber" value="${requestScope.user.tel}"
										placeholder="tel" required="required" /></td>
								</tr>

								<tr>
									<td><label><fmt:message key="label.location" /> (*):</label></td>
									<td><input type="text" class="form-control"
										name="userLocation" value="${requestScope.user.location}"
										placeholder="location" required="required" /></td>
								</tr>

								
								<tr>
									<td><button class="btn btn-success" onclick="if (!(confirm('Are you sure you want to save changes?'))) return false"
										type="submit" value="Save" class="save"><fmt:message key="label.save" /></button></td>
								</tr>


							</tbody>
						</table>
					</form>

					<a href="index.jsp">
						<button class="btn btn-primary"><fmt:message key="label.return" /></button>
					</a>
					
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>