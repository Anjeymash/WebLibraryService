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
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index</title>

<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

</head>

</head>


<body>


	<div class="navbar navbar-inverse">

		<div class="container">


			<div class="row">

				<div class="navbar-header">


					<ul class="nav navbar-nav">
						<li><a href="index.jsp"><fmt:message key="label.main" />
						</a></li>
						<li><a
							href="https://www.linkedin.com/in/andrey-mashkouski-6079a7101"><fmt:message
									key="label.about" /> </a></li>
						<li><a
							href="https://www.linkedin.com/in/andrey-mashkouski-6079a7101"><fmt:message
									key="label.contacts" /> </a></li>

					</ul>
				</div>
				<div class="col-md-6">
					<div class="row">
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

									<option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
									<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
								</select>

							</form>


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
												<c:if test="${sessionScope.userRole eq 'admin'}">
													<td>
														<form method="get" action="Controller" class="navbar-form">
															<input type="hidden" name="command" value="addbook" />
															<button type="submit" class="btn btn-error">
																<fmt:message key="label.addbook" />
															</button>
														</form>
													</td>
												</c:if>
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

	<!--  -->
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

	<div class="navbar navbar-cent">
		<div class="container">
			<div class="navbar navbar-cent">
				<h1>
					<a href="index.jsp"><fmt:message key="label.books" /> >></a>

					<c:if test="${requestScope.bookGenre eq 'children'}">
						<fmt:message key="label.children" />
					</c:if>
					<c:if test="${requestScope.bookGenre eq 'scifi'}">
						<fmt:message key="label.scifi" />
					</c:if>
					<c:if test="${requestScope.bookGenre eq 'novels'}">
						<fmt:message key="label.novels" />
					</c:if>
					<c:if test="${requestScope.bookGenre eq 'adventures'}">
						<fmt:message key="label.adventures" />
					</c:if>
				</h1>
			</div>

		

			<div class="row">
				<div class="col-md-8">

					<c:if test="${empty requestScope.listbook }">

						<br />

						<div class="container">
							<table>

								<tr>
									<td>
										<div class="row">
											<div class="col-md-7">
												<p>
													<img
														src="https://s3-images.ozstatic.by/landing/1400/575/833/2335/2335833575_0_1509801768.jpg"
														class="img-responsive">

												</p>
											</div>
											<div class="col-md-7">
												<h2>
													<fmt:message key="label.children" />
												</h2>
												<form method="get" action="Controller">
													<input type="hidden" name="command" value="listbook" /> <input
														type="hidden" name="bookGenre" value="children" />
													<button type="submit"
														class="btn btn-success btn-lg btn-block">
														<fmt:message key="label.look" />
													</button>
												</form>
											</div>
										</div>
									</td>

									<td>
										<div class="row">
											<div class="col-md-7">
												<p>

													<img
														src="https://s4-images.ozstatic.by/landing/1400/76/443/358/358443076_0_1508840023.jpg"
														class="img-responsive">
												</p>
											</div>
											<div class="col-md-7">
												<h2>
													<fmt:message key="label.scifi" />
												</h2>
												<form method="get" action="Controller">
													<input type="hidden" name="command" value="listbook" /> <input
														type="hidden" name="bookGenre" value="scifi" />
													<button type="submit"
														class="btn btn-success btn-lg btn-block">
														<fmt:message key="label.look" />
													</button>
												</form>
											</div>
										</div>
									</td>
									<td>
										<div class="row">
											<div class="col-md-7">
												<p>
													<img
														src="https://s4-images.ozstatic.by/landing/1400/257/974/4231/4231974257_0_1510214775.jpg"
														class="img-responsive">


												</p>
											</div>
											<div class="col-md-7">
												<h2>
													<fmt:message key="label.novels" />
												</h2>
												<form method="get" action="Controller">
													<input type="hidden" name="command" value="listbook" /> <input
														type="hidden" name="bookGenre" value="novels" />
													<button type="submit"
														class="btn btn-success btn-lg btn-block">
														<fmt:message key="label.look" />
													</button>
												</form>
											</div>
										</div>
									</td>
									<td>
										<div class="row">
											<div class="col-md-7">
												<p>
													<img
														src="https://s1-images.ozstatic.by/landing/1400/741/179/305/305179741_0_1507281074.jpg"
														class="img-responsive">

												</p>
											</div>
											<div class="col-md-7">
												<h2>
													<fmt:message key="label.adventures" />
												</h2>
												<form method="get" action="Controller">
													<input type="hidden" name="command" value="listbook" /> <input
														type="hidden" name="bookGenre" value="adventures" />
													<button type="submit"
														class="btn btn-success btn-lg btn-block">
														<fmt:message key="label.look" />
													</button>
												</form>
											</div>
										</div>
									</td>

								</tr>

							</table>

						</div>

					</c:if>
				</div>

				<c:if test="${not empty requestScope.listbook }">

					<table class="table">
						<tr>
							<th></th>
							<th><fmt:message key="label.title" /></th>
							<th><fmt:message key="label.author" /></th>
							<th><fmt:message key="label.genre" /></th>
							<th><fmt:message key="label.year" /></th>
							<th><fmt:message key="label.quantity" /></th>
						</tr>
						<c:forEach var="book" items="${requestScope.listbook}">
							<tr>

								<td><img src="img/${book.id}.jpg" width="100" height="100"></td>
								<td>${book.title}</td>
								<td>${book.author}</td>
								<td>${book.genre}</td>
								<td>${book.year}</td>
								<td>${book.quantity}</td>


								<!-- <td>
										<form method="get" action="Controller">
											<input type="hidden" name="command" value="lookbook" /> 
												<!--  type="hidden" name="user_id" value="${book.id}" />
											<input type="hidden" name="title" value="${book.title}" />
											<input type="hidden" name="author"
												value="${book.author}" /> <input type="hidden"
												name="year" value="${book.year}" /> <input
												type="hidden" name="genre" value="${book.genre}" />
											<button type="submit" class="btn btn-success">Details</button>
										</form> 
									</td>-->

								<td><c:url var="viewLink"
										value="Controller?command=showbook">
										<c:param name="id" value="${book.id}" />
									</c:url> <c:url var="editLink" value="Controller?command=editbook">
										<c:param name="id" value="${book.id}" />
									</c:url> <c:url var="delLink" value="Controller?command=delbook">
										<c:param name="id" value="${book.id}" />
									</c:url> <a href="${viewLink}">
										<button type="submit" class="btn btn-primary">
											<fmt:message key="label.details" />
										</button>
								</a> <c:if test="${sessionScope.userRole eq 'admin'}">
										<a href="${editLink}"><button type="submit"
												class="btn btn-success">
												<fmt:message key="label.editbook" />
											</button></a>
										<a href="${delLink}"
											onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false"><button
												type="submit" class="btn btn-error">
												<fmt:message key="label.del" />
											</button></a>
									</c:if></td>


							</tr>
						</c:forEach>
					</table>

				</c:if>
			</div>

			<div class="col-md-4"></div>
		</div>
	</div>



	<div class="navbar navbar-fixed-bottom">
		<div class="navbar navbar-inverse">
			<h5>Copyright Anjeymash 2017. All rights reserved</h5>
		</div>
	</div>



</body>

</html>

