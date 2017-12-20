<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Minified CSS -->
<!-- <link rel="stylesheet" href="assets/build/app.min.css"> -->
<link href="<c:url value='/static/css/app.min.css' />" rel="stylesheet"></link>
<title></title>
</head>
<body>
	<div class="item-wrapper">
		<div class="item item-id">
			<label>ITEM ID</label> <span>${id}</span>
		</div>
		<div class="item item-name">
			<label>ITEM NAME</label> <span>${summary}</span>
		</div>
		<div class="item item-decsription">
			<label>ITEM DESCRIPTION</label> <span>${description}</span>
		</div>
	</div>
	<div class="item-wrapper no-border">
		<div class="item stage">
			<label>CURRENT STAGE</label> <span>${box}</span>
			<%--             <span>${subBox}</span>
 --%>
		</div>
		<div class="item status">
			<label>CURRENT STATUS</label> <span>${status}</span>
		</div>
	</div>

	<div class="flow-chart">
		<div class="row">
            <div class="refresh-button col-sm-2">
                <a href="javascript:window.location.reload(true)">Refresh</a>
            </div>
            <div class="col-sm-2 flow-chart-legend">
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
			<div class="simple user-story col-sm-1">
				<img>
				<button class="${map.box1}"><b>User Story Grooming</b></button>
			</div>
			<canvas class="horizontal-line col-sm-1" width="200" height="100">
            </canvas>
			<div class="simple test-scripts col-sm-1">
				<img>
				<button class="${subMap.box7}"><b>Test Script Submitted</b></button>
			</div>
			<canvas class="horizontal-line col-sm-1" width="200" height="100">
            </canvas>
			<div class="simple test-scripts col-sm-1">
				<img>
				<button class="${subMap.box8}"><b>Test Script Approved</b></button>
			</div>
		</div>

		<div class="row">
			<canvas class="vertical-line col-sm-1" width="200" height="200">
            </canvas>
		</div>
		<div class="row">
			<div class="simple approval col-sm-1">
				<img>
				<button class="${map.box2}"><b>User Story Approved</b></button>
			</div>
		</div>
		<div class="row">
			<canvas class="vertical-line col-sm-1" width="200" height="200">
            </canvas>
		</div>
		<div class="row">
			<div class="task-box col-sm-2">
				<img>
				<div class="border-full ${map.box3}">
					<p class="task-headline ${map.box3}"><b>Dev Box</b></p>
					<div class="border-full ${map.box3}">
						Dev Activities
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
			<canvas class="horizontal-line col-sm-1" width="200" height="600">
                </canvas>
			<div class="task-box col-sm-2">
				<img>
				<div class="border-full ${map.box4}">
					<p class="task-headline ${map.box4}"><b>Test Box</b></p>
					<div class="border-full ${map.box4}">
						Test Activities
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
			<canvas class="horizontal-line col-sm-1" width="200" height="600">
                </canvas>
			<div class="task-box col-sm-2">
				<img>
				<div class="border-full ${map.box5}">
					<p class="task-headline ${map.box5}"><b>Pre Prod</b></p>
					<div class="border-full ${map.box5}">
						Pre-Prod Activities
						<ul>
							<li>Scipting</li>
							<li>Testing</li>
							<li>Pre Deploy</li>
							<li>validation</li>
							<li>Server Check</li>
							<li>Review</li>
						</ul>
					</div>
				</div>
			</div>
			<canvas class="horizontal-line col-sm-1" width="200" height="600">
                </canvas>
			<div class="task-box col-sm-2">
				<img>
				<div class="border-full ${map.box6}">
					<p class="task-headline ${map.box6}"><b>Production</b></p>
					<div class="border-full ${map.box6}">
						Production Activities
						<ul>
							<li>System access</li>
							<li>Accuracy</li>
							<li>End Users</li>
							<li>Reports</li>
							<li>Regular Check</li>
							<li>Go Live</li>
						</ul>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Minified JS -->
	<!-- <script type="text/javascript" src="assets/build/app.min.js"></script> -->
	<script type="text/javascript"
		src="<c:url value='/static/js/app.min.js' />"></script>

	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>