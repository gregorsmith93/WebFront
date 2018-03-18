<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Gradle + Spring MVC</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Project Name</a>
			<a class="navbar-brand" href="user">User</a>
			<a class="navbar-brand" href="user/login">Login</a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h1>${title}</h1>
		<h1>User successfully created!</h1>
		<form:form modelAttribute="createdUser">
             <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:textarea path="name"/></td>
                </tr>
                <tr>
                    <td><form:label path="username">Username</form:label></td>
                    <td><form:textarea path="username"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:textarea path="password"/></td>
                </tr>
                <tr>
                    <td><form:label path="salt">Salt</form:label></td>
                    <td><form:textarea path="salt"/></td>
                </tr>
            </table>
        </form:form>
	</div>
</div>

<div class="container">

	<div class="row">
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
	</div>


	<hr>
	<footer>
		<p>&copy; Mkyong.com 2015</p>
	</footer>
</div>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>