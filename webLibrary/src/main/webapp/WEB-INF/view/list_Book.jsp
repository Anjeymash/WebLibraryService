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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>

<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">


</head>


<body>
	<div class="navbar navbar-inverse">

		<div class="container">


			<div class="row">

				<div class="navbar-header">


					<ul class="nav navbar-nav">
						<li><a href="index.jsp"><fmt:message key="label.main" />
						</a></li>
						<li><a href="index.jsp"><fmt:message key="label.about" />
						</a></li>
						<li><a href="index.jsp"><fmt:message key="label.contacts" />
						</a></li>

					</ul>
				</div>
				<div class="col-md-6">
					<div class="row">
						<div class="btn-toolbar">
							<div class="btn-group">
								<form method="get" action="Controller"
									class="navbar-form navbar-right">
									<div class="form-group">
										<select required size="1" name="command" class="form-control">
											<option value="searchbytitle"><fmt:message key="label.title" /></option>
											<option value="searchbyauthor"><fmt:message key="label.author" /></option>
											<option value="searchbycontext"><fmt:message key="label.context" /></option>
										</select> <input type="text" name="searchparam" value=""
											placeholder="search" class="form-control" />

										<button type="submit" class="btn btn-primary">
											<fmt:message key="label.search" />
										</button>
									</div>
								</form>
								<form class="navbar-form navbar-right">
									<select required size="1" class="form-control" id="language"
										name="language" onchange="submit()">
										<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
										<option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
									</select>

								</form>


							</div>
						</div>

					</div>

				</div>

				<div class="col-md-1">

					<c:if test="${empty sessionScope.userId }">
						<div class="btn-toolbar">
							<div class="btn-group">
								<table>
									<tr>
										<td>
											<form method="post" action="Controller"
												class="navbar-form navbar-right">
												<div class="form-group">
													<input type="hidden" name="command" value="singin" /> <input
														type="text" class="form-control" name="userLogin" value=""
														placeholder="login" required="required" /> <input
														type="password" class="form-control" name="userPassword"
														value="" placeholder="password" required="required" />
												</div>
												<button type="submit" class="btn btn-primary">
													<fmt:message key="label.signin" />
												</button>
											</form>
										</td>
										<td>

											<div class="btn-group">
												<form method="get" action="Controller"
													class="navbar-form navbar-right">
													<input type="hidden" name="command" value="registration" />
													<button type="submit" class="btn btn-success">
														<fmt:message key="label.registration" />
													</button>
												</form>

											</div>

										</td>
									</tr>
								</table>
							</div>
						</div>

					</c:if>

					<c:if test="${not empty sessionScope.userId }">

						<div class="col-md-6">
							<div class="row">

								<div class="btn-toolbar">
									<div class="btn-group">

										<table>
											<tr>
												<td>${sessionScope.userName}</td>
												<td>
													<form method="get" action="Controller" class="navbar-form">
														<input type="hidden" name="command"
															value="edituserprofile" />
														<button type="submit" class="btn btn-success">
															<fmt:message key="label.editprofile" />
														</button>
													</form>
												</td>

												<td>
													<form method="get" action="Controller" class="navbar-form">
														<input type="hidden" name="command" value="singout" />
														<button type="submit" class="btn btn-primary">
															<fmt:message key="label.signout" />
														</button>
													</form>
												</td>
												<td>
													<form method="get" action="Controller" class="navbar-form">
														<input type="hidden" name="command" value="addbook" />
														<button type="submit" class="btn btn-error">
															<fmt:message key="label.addbook" />
														</button>
													</form>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<c:if test="${not empty requestScope.message }">
				<div class="alert alert-success">
					<c:out value="${requestScope.message}"></c:out>
				</div>
			</c:if>

			<c:if test="${not empty requestScope.errorMessage }">
				<div class="alert alert-warning">
					<c:out value="${requestScope.errorMessage}"></c:out>
				</div>
			</c:if>
		</div>
	</div>
<div class="navbar navbar-default">
		
	<table >
	
		<tr>
			<td><img src="img/ch.jpg" width="200" height="200"
				class="img-thumbnail"></td>
			<td><c:url var="bookinLink" value="Controller?command=bookin&id=${book.id}"></c:url>
				<c:url var="returnLink"
					value="Controller?command=listbook&bookGenre=${book.genre}"></c:url> <c:url
					var="delLink" value="Controller?command=listbook"></c:url> <a
				href="${bookinLink}"
				onclick="if (!(confirm('Are you sure you want to reserve this book?'))) return false"><button
						type="submit" class="btn btn-success">
						<fmt:message key="label.addtocart" />
					</button></a> <a href="${returnLink}"
				onclick="if (!(confirm('Are you sure you want to return to book-list?'))) return false"><button
						type="submit" class="btn btn-primary">
						<fmt:message key="label.returntobooks" />
					</button></a></td>
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
		<tr>
			<th><fmt:message key="label.quantity" />:</th>
			<td>${book.quantity}</td>
		</tr>
		<tr>
			<th><fmt:message key="label.context" />:</th>
			<td>${book.context}</td>
		</tr>

	

	</table>
			
	
	</div>
	

	<div class="navbar navbar-fixed-bottom">
		<div class="navbar navbar-inverse">
			<h5>Copyright Anjeymash 2017. All rights reserved</h5>
		</div>
	</div>
	

</body>
</html>