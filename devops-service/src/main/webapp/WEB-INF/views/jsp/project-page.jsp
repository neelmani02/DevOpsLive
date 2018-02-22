<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="project-container">
		<h2>My Projects</h2>
		<div class="edit-buttons">
			<button class="add-project">Add Project</button>
			<a href="#" class="grid-view">
				<i class="glyphicon glyphicon-th"></i>
			</a>
			<a href="#" class="list-view">
				<i class="glyphicon glyphicon-th-list"></i>
			</a>
		</div>
		<table class="project-table">
		<thead>
			<tr>
				<th>#</th>
				<th>Name of Project</th>
				<th>Actions</th>						
		   </tr>
		</thead>
		<tbody>			
		    <tr>
		    	<td>1</td>
		    	<td>Devops Live</td>
		    	<td>
			    	<a href="/devops-service/getAllIssues" class="checkin-btn">
						<img src="/devops-service/static/assets/images/checkin.png"/>
						Check status
					</a>
					<a href="#" class="">
						<i class="glyphicon glyphicon-trash"></i>
					</a>
		    	</td>
		    </tr>
		     <tr>
		    	<td>2</td>
		    	<td>Under Armour</td>
		    	<td>
			    	<a href="#" class="checkin-btn">
						<img src="/devops-service/static/assets/images/checkin.png"/>
						Check status
					</a>
					<a href="#" class="">
						<i class="glyphicon glyphicon-trash"></i>
					</a>
		    	</td>
		    </tr>
		     <tr>
		    	<td>3</td>
		    	<td>Nike Inc.</td>
		    	<td>
			    	<a href="#" class="checkin-btn">
						<img src="/devops-service/static/assets/images/checkin.png"/>
						Check status
					</a>
					<a href="#" class="">
						<i class="glyphicon glyphicon-trash"></i>
					</a>
		    	</td>
		    </tr>
		     <tr>
		    	<td>4</td>
		    	<td>Coca Cola</td>
		    	<td>
			    	<a href="#" class="checkin-btn">
						<img src="/devops-service/static/assets/images/checkin.png"/>
						Check status
					</a>
					<a href="#" class="">
						<i class="glyphicon glyphicon-trash"></i>
					</a>
		    	</td>
		    </tr>
		     <tr>
		    	<td>5</td>
		    	<td>HPI</td>
		    	<td>
			    	<a href="#" class="checkin-btn">
						<img src="/devops-service/static/assets/images/checkin.png"/>
						Check status
					</a>
					<a href="#" class="">
						<i class="glyphicon glyphicon-trash"></i>
					</a>
		    	</td>
		    </tr>
		     <tr>
		    	<td>6</td>
		    	<td>Dell</td>
		    	<td>
			    	<a href="#" class="checkin-btn">
						<img src="/devops-service/static/assets/images/checkin.png"/>
						Check status
					</a>
					<a href="#" class="">
						<i class="glyphicon glyphicon-trash"></i>
					</a>
		    	</td>
		    </tr>
		 </tbody>
	</table>
		
	</div>