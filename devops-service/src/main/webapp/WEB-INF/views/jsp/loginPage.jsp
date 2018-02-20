<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar">
  
    <div class="navbar-header">
      <a class="navbar-brand" href="#">DAB</a>
    </div>
    
    <ul class="nav navbar-nav navbar-right nav-right-content">
      <li class="nav-text">Don't have an account?</li>
      <li><button type="submit" class="btn btn-outline-success">SIGN UP</button></li>
    </ul>
  
</nav>


<div class="login-container">
	<div class="login-image"><img src="/devops-service/static/assets/images/login.png"></div>
	<div class="content">
		<h4 class="font-type">Login to ease your life.</h4>
		<h6 class="green-color">Login to see your projects and their current status.</h6>
		<form action="doLogin" method="post">  
			<div><input type="text" name="userName" placeholder="Work email address"/> </div> 
			<div><input type="password" name="password" placeholder="Password"/></div>
			<div>
			
			</div>
			
			<input class="login-button" type="submit" value="LOGIN"/>
		</form>
	</div>
</div>