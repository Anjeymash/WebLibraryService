<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage = "true" %>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!DOCTYPE html>
<html lang="${language}">
<head>

<meta charset="utf-8">
<title>error</title>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="navbar navbar-cent">

		<div class="navbar navbar-inverse">

			Error Page<br /> Service is not available, try later
		</div>
		<a href="index.jsp">Return to main menue</a>
	</div>


	<div class="container">
		<div class="row">
			<c:if test="${not empty requestScope.errorMessage }">
				<div class="alert alert-warning">
					<c:out value="${requestScope.errorMessage}"></c:out>
				</div>
			</c:if>
		</div>
	</div>




</body>
</html>