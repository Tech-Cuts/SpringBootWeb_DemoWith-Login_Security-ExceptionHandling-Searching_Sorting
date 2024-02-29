<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sign-up here</title>
		
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
	
			<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUYLbC5wBUKqdrluQP3nGTs_aJuiisFjCbF06O2b-tdg06w15Nj4ro9T-9XrrtUlV2F7o&usqp=CAU">
			<h1>Sign-up Page!</h1>
			<form action="signup" method="post">
	
				<div style="width: 40%;">
					<div class="form-group">
						<label>UserName</label> 
						<input type="text" name="username" class="form-control">
					</div>
				
					<div class="form-group">
						<label>Password</label> 
						<input type="password" name="password" class="form-control">
					</div>
	
					<div class="form-group">
						<label>Email</label> 
						<input type="email" name="email" class="form-control">
					</div>
					
					<div class="form-group">
						<label>Gender</label> 
						<select name="gender" class="form-control">
							<option>Male</option>
							<option>Female</option>
							
						</select>
					</div>
											
					<div class="form-group">
						<br />
						<button type="submit" class="btn btn-primary">Sign-Up Now</button>
						<button type="reset" class="btn btn-danger">Reset</button>
	
					</div>
				</div>
				<hr />
				<h2 style="color: red; font-weight: bold;">${message}!!!!!!!</h2>
			</form>
	
		</div>
	</body>
</html>