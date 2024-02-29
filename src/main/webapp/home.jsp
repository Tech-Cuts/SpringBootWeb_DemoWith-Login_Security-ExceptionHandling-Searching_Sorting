<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
			rel="stylesheet">
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	
	<body>
	
		<header style="height: 40px; background-color: navy"></header>
	
		<div class="container">
			<img src="data:image/png;base64,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">
			<h2 style="color: red; font-weight: bold;">${message}</h2>
			<hr>
		</div>
		<div class="container">
			
			<div>
				<a href="auth">
					<button type="submit" class="btn btn-primary">Login</button>
				</a>
			</div>
			
			<div>
				<form action ="searchSignup">
					<table style="border:0px; width:100%">
						<tr>
							<th style="width:70%">All user info:</th>
							<th colspan="3"></th>
							<th width = "350"><input class="form-control" name="searchText" type="text" placeholder="search record by user-name (or email)"></th>
							<th><button type="submit" class="btn btn-primary">SEARCH</button></th>
						</tr>
					</table>
					<p/>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Username 
									<span style="float:right;">
										<a href="sortByUsername?sortbyU=${sortbyU}">
											<img width="15" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTE6h-wVeCjawxy3xOS8oddmBBeEvL6Y4Qzwg&usqp=CAU">
										</a>
									</span>
								</th>
								<th>Password</th>
								<th>Email 
									<span style="float:right;">
										<a href="sortByEmail?sortbyE=${sortbyE}">
											<img width="15" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTE6h-wVeCjawxy3xOS8oddmBBeEvL6Y4Qzwg&usqp=CAU">
										</a>
									</span>
								</th>
								<th>Gender</th>
								<th>Created Date</th>
								<th>Action</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach var="item" items="${signups}">		  
								<tr>
									<td>${item.username}</td>
									<td>${item.password}</td>
									<td>${item.email}</td>
									<td>${item.gender}</td>
									<td>${item.timestamp}</td>
									<td>
										<a href="deleteSignup?uname=${item.username}">
											<!--  deleteSignup? is a query parameter; "uname" is just a variable name -->
											<button type="button" class="btn btn-danger">Delete</button>
										</a>
										
										<a href="editSignup?uname=${item.username}">
											<!--  editSignup? is a query parameter; "uname" is just a variable name -->
											<button type="button" class="btn btn-primary">Edit</button>
										</a>
									</td>
								</tr>
							</c:forEach>  
						</tbody>
					</table>
				</form>
			</div>
		</div>
		
	</body>
</html>