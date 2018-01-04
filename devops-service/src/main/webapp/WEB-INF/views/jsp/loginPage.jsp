<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<form action="doLogin" method="post">  
Name:<input type="text" name="userName"/><br/><br/>  
Password:<input type="password" name="password"/><br/><br/>  
<input type="submit" value="Login"/>  
</form>

</body>
</html>