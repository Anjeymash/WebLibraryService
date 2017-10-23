<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.bean.Book" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ErrorPage</title>
</head>
<body>
Error Page

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