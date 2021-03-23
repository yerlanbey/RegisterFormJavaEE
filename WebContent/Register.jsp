<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
<%@page import="java.util.List"%>
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    
    <%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h3 style="color:red"> <%= error %> </h3>
<%
			}
		}
%>	
<form action="Register" method="POST">
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="text" class="form-control" id="email" name="email">
  </div>
  <div class="form-group">
    <label for="Password">Password</label>
    <input type="password" class="form-control" id="password" name="password">
  </div>
  <div class="form-group">
    <label for="Confirmassword">Confirm Password</label>
    <input type="password" class="form-control" id="confirm_password" name="confirm_password">
  </div>
    <div class="form-group">
    <label for="exampleInputEmail1">First name</label>
    <input type="text" class="form-control" id="first_name" name="first_name" value="${form.first_name}" aria-describedby="emailHelp">
  </div>
    <div class="form-group">
    <label for="exampleInputEmail1">Last name</label>
    <input type="text" class="form-control" id="last_name" name="last_name" aria-describedby="emailHelp">
  </div>
  <br>
  <button type="submit" class="btn btn-primary mb-10" name="button" value="Register">Register</button>
</form>
</main>
</body>
</html>