<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<!DOCTYPE html>
<html lang="${language}">
<head>

	<meta charset="utf-8">
	<title>error</title>
</head>
<body>
Error Page<br/>
The page is temporarily not available 

<div class="container">
		<div class="row">
					<c:if test="${not empty requestScope.errorMessage }">
				<div class="alert alert-warning">
					<c:out value="${requestScope.errorMessage}"></c:out>
				</div>
			</c:if>
		</div>
	</div>
<a href="index.jsp">Return to main menue</a>
</body>
</html>