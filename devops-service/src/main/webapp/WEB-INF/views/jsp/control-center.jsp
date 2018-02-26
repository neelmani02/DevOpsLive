<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div id="control-center">
		<a class="back-button" href="/devops-service/getAllIssues">
		<span class="glyphicon glyphicon-arrow-left"></span>	
	</a>
	<div class="item-wrapper">
		<table class="">
			<thead>
				<tr>
					<th>User Story ID</th>
					<th>Description</th>
					<th>Summary</th>
					<th>Current Status</th>						
			   </tr>
			</thead>
			<tbody>	
					
			    <tr>
			    	<td><a target="_blank" href="https://devopsbeacon.atlassian.net/projects/DPP/issues/${key}"><span>${id}</span></a></td>
			    	<td>${description}</td>
			    	<td>${summary}</td>
			    	<td>${status}</td>
			    </tr>
			    
			 </tbody>
	</table>
	</div>
	
	
	<c:if test ="${buildStatus == 'FAILURE' && box == 'Dev Box'}">
	<div class="item-wrapper">
         <div style="color:Red;">Last Build failed due to RESTRICTED Object i.e. CustomObject___Logs__c </div>
      </div>
       </c:if>
	<div class="top-heading">
	<h2>Devops Cycle</h2>
	</div>
	<div class="flow-chart">
		<div class="row">
			<div class="simple user-story col-sm-2">
				<button class="${map.box1}"> User Story Grooming</button>
			</div>
			<canvas class="horizontal-line col-sm-1" width="60" height="100">
            </canvas>
			<div class="simple test-scripts col-sm-2">
				<button class="${subMap.box7}"> Test Script Submitted </button>
			</div>
			<canvas class="horizontal-line col-sm-1" width="60" height="100">
            </canvas>
			<div class="simple test-scripts col-sm-2">
				<button class="${subMap.box8}"> Test Script Approved </button>
			</div>
           
            <div class="col-sm-1 flow-chart-legend">
            	<h3>Legend</h3>
                <div class="green">
                    <label></label>
                    <span>&nbsp;Done</span>
                </div>
                <div class="yellow">
                    <label></label>
                    <span>&nbsp;In Progress</span>
                </div>
                <div class="gray">
                    <label></label>
                    <span>&nbsp;Planned</span>
                </div>
            </div>  
		</div>

		<div class="row">
			<canvas class="vertical-line col-sm-2" width="200" height="60">
            </canvas>
		</div>
		<div class="row">
			<div class="simple approval col-sm-2">
				<button class="${map.box2}"> User Story Approved </button>
			</div>
		</div>
		<div class="row">
			<canvas class="vertical-line col-sm-2" width="200" height="60">
            </canvas>
		</div>
		<div class="row">
			<div class="task-box col-sm-2">
				<div class="border-full ${map.box3}">
					<h3 class="task-headline ${map.box3}"> Dev Box </h3>
					<div class="border-inside ${map.box3}">
						Activities:
						<ul>
							<li>Build</li>
							<li>Unit Test</li>
							<li>codeLint</li>
							<li>validation</li>
							<li>Peer/Lead</li>
							<li>Review</li>
						</ul>
					</div>
				</div>
			</div>
			<canvas class="horizontal-line col-sm-1" width="60" height="200">
                </canvas>
             <div class="task-box col-sm-2">
				<img class="no-img">
				<div class="border-full ${map.box4}">
					<h3 class="task-headline ${map.box4}"> Build Box </h3>
					<div class="border-inside ${map.box4}">
						Deploy to CI/Test:
						<ul>
							<li>Code</li>
							<li>Quality </li>
							<li>Coverage</li>
							<li>Check</li>
							<li>Best</li>
							<li>Practices</li>
						</ul>
					</div>
				</div>
			</div>
			<canvas class="horizontal-line col-sm-1" width="60" height="200">
                </canvas>
			<div class="task-box col-sm-2">
				<div class="border-full ${map.box5}">
					<h3 class="task-headline ${map.box5}"> CI/Test Box </h3>
					<div class="border-inside ${map.box5}">
						Test Activities:
						<ul>
							<li>Build</li>
							<li>Unit Test</li>
							<li>SIT</li>
							<li>UAT</li>
							<li>QA</li>
							<li>Review</li>
						</ul>
					</div>
				</div>
			</div>
			<canvas class="horizontal-line col-sm-1" width="60" height="200">
                </canvas>
			<div class="task-box col-sm-2">
				<div class="border-full ${map.box6}">
					<h3 class="task-headline ${map.box6}"> Pre Prod </h3>
					<div class=" ${map.box6}">
					<c:if test ="${map.box6 =='grey'}">
						<img class="database-icon" src="/devops-service/static/assets/images/grey-cy.png">
					</c:if>
					<c:if test ="${map.box6 =='yellow'}">
						<img class="database-icon" src="/devops-service/static/assets/images/orange-cy.png">
					</c:if>
					<c:if test ="${map.box6 =='green'}">
						<img class="database-icon" src="/devops-service/static/assets/images/green-cy.png">
					</c:if>
					</div>
				</div>
			</div>
			<canvas class="horizontal-line col-sm-1" width="60" height="200">
                </canvas>
			<div class="task-box col-sm-2">
				<img class="no-img">
				<div class="border-full ${map.box9}">
					<h3 class="task-headline ${map.box9}"> Production </h3>
					<div class=" ${map.box9}">
                     <c:if test ="${map.box9 =='grey'}">
						<img class="database-icon" src="/devops-service/static/assets/images/grey-cy.png">
					</c:if>
					<c:if test ="${map.box9 =='yellow'}">
						<img class="database-icon" src="/devops-service/static/assets/images/orange-cy.png">
					</c:if>
					<c:if test ="${map.box9 =='green'}">
						<img class="database-icon" src="/devops-service/static/assets/images/green-cy.png">
					</c:if>
					</div>
				</div>
			</div>
		</div>	
		
	</div>
		
	</div>	

