<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="login-container">
	<div class="content">
		<h3>NEW LEAD/MANAGER</h3>
		<button type="submit" >CREATE ACCOUNT</button>
	</div>
	<div class="content">
		<h3>REGISTERED LEAD/MANAGER</h3>
		<form action="doLogin" method="post">  
			<div>E-MAIL</div><input type="text" name="email"/>  
			<div>PASSOWRD</div><input type="password" name="password"/>
			<div>
			<a>FORGOT YOUR PASSOWRD?</a>
			</div>
			<button type="submit" >LOGIN</button>
		</form>
	</div>
</div>