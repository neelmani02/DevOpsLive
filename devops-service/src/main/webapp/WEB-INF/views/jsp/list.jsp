<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 	
<div>
	<div class="top-heading">
	<h2>Timeline</h2>
	</div>
	<div class="status-wrapper row">
		<div class="col-sm-2">
		</div>
		<div class="col-sm-2 border-box">
			<div class="headline">
				<h3>Current Phase</h3>
			</div>
			<p>In Development</p>
		</div>
		<div class="col-sm-2 border-box">
			<div class="headline">
				<h3>Current Phase</h3>
			</div>
			<p>In Development</p>
		</div>
		<div class="col-sm-2 border-box">
			<div class="headline">
				<h3>Current Phase</h3>
			</div>
			<p>In Development</p>
		</div>
		<div class="col-sm-2">
		</div>	
	</div>
	<div class="top-heading">
	<h2>Status</h2>
	</div>
	<div class="overview-wrapper">
		<div class="tile-wrapper" id="sprints">
		    <p class="number">6</p>
		    <p class="name">Sprints</p>
		</div>
		<div class="tile-wrapper" id="userStories">
		    <p class="number">8</p>
		    <p class="name">User Stories</p>
		</div>
		<div class="tile-wrapper" id="defects">
		    <p class="number">12</p>
		    <p class="name">Defects</p>
		</div>
		<div class="tile-wrapper" id="releases">
		    <p class="number">2</p>
		    <p class="name">Releases</p>
		</div>
	</div>
	<table id="overviewTable" class="dataTable table">
		<thead>
			<tr>
				<th>UserStory Id</th>
				<!-- <th>UserStory Key</th> -->
				<th>Description</th>
				<th>Summary</th>
				<th>Current Status</th>
				<!--<th>Creator</th>-->			
		   </tr>
		</thead>
		<tbody>			
		    <c:forEach var="userStory" items="${list}"> 
				<tr class="">
					<td><a href="/devops-service/getIssue/${userStory.id}">${userStory.id}</a></td>
<%-- 					<td>${userStory.key}</td>
 --%>					<td>${userStory.fields.description}</td>
					<td>${userStory.fields.summary}</td>
					<td>${userStory.fields.status.name}</td>
<%-- 					<td>${userStory.fields.creator.displayName}</td>				    
 --%>				</tr>
			</c:forEach>
		 </tbody>
	</table>
</div>
	