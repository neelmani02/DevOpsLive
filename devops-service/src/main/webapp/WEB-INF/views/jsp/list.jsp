<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<body>

	
	<div >

		<div >
			<h1>DevOps Live</h1>
			
		</div>
		<div>
		<table border="2" width="70%" cellpadding="2">
	<tr>
	<th>UserStory Id</th>
	<th>UserStory Key</th>
	<th>Description</th>
	<th>Summary</th>
	<th>Current Status</th>
	<th>Creator</th>
	</tr>
	
    <c:forEach var="userStory" items="${list}"> 
    <tr>
    <td><a href="/devops-service/getIssue/${userStory.id}">${userStory.id}</a></td>
    <td>${userStory.key}</td>
    <td>${userStory.fields.description}</td>
    <td>${userStory.fields.summary}</td>
    <td>${userStory.fields.status.name}</td>
    <td>${userStory.fields.creator.displayName}</td>
    
    </tr>
    </c:forEach>
    </table>
    </div>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>